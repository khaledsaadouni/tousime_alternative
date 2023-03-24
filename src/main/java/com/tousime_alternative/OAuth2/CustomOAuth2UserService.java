package com.tousime_alternative.OAuth2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @SneakyThrows
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String clientName =userRequest.getClientRegistration().getClientName();
        String accessToken=userRequest.getAccessToken().getTokenValue();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        if(clientName=="Facebook") {
            // Fetch profile picture
            ResponseEntity<byte[]> pictureResponse = restTemplate.exchange(
                    "https://graph.facebook.com/v12.0/me/picture?redirect=false&type=large",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    byte[].class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(pictureResponse.getBody());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String profilePictureUrl = jsonNode.get("data").get("url").asText();


            // Fetch birthday info
            ResponseEntity<String> infoResponse = restTemplate.exchange(
                    "https://graph.facebook.com/v12.0/me?fields=birthday",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class);
            String birthdayInfo = infoResponse.getBody();
            ObjectMapper objectMapper2 = new ObjectMapper();
            Map<String, Object> jsonMap = null;
            try {
                jsonMap = objectMapper.readValue(birthdayInfo, Map.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            String birthdaystring = (String) jsonMap.get("birthday");
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date utilDate = format.parse(birthdaystring);
            java.sql.Date myBirthday = new java.sql.Date(utilDate.getTime());
            OAuth2User user =  super.loadUser(userRequest);
            return new CustomOAuth2User(user,clientName,myBirthday,profilePictureUrl);
        }
        else{
            OAuth2User user =  super.loadUser(userRequest);
            return new CustomOAuth2User(user,clientName,null,null);
        }

    }

}