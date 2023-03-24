package com.tousime_alternative.OAuth2;

import com.tousime_alternative.dto.auth.AuthenticationResponse;
import com.tousime_alternative.model.AuthenticationProvider;
import com.tousime_alternative.model.User;
import com.tousime_alternative.model.enumr.Role;
import com.tousime_alternative.repository.UserRepository;
import com.tousime_alternative.service.auth.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OAuthUserService {
    private final UserRepository repository;
    private static String tokenFromOauth2;
    private final JwtService jwtService;

    public  String getTokenFromOauth2() {
        return tokenFromOauth2;
    }



    public AuthenticationResponse processOAuthPostLogin(String email, String fisrtname, String lastname, String image, String client , String name, String facebookpicture, Date birthday) {
        var user = repository.findByEmail(email);
        System.out.println("**************** user " + user);
        if(fisrtname==null){
            String[] splitedname=name.split("\\s+");
            fisrtname=splitedname[0];
            lastname=splitedname[1];
            image=facebookpicture;
        }
        if (!user.isPresent()) {
            System.out.println("+++++ creer un nv user");
            var newuser = User.builder()
                    .firstname(fisrtname)
                    .lastname(lastname)
                    .email(email)
                    .photo(image)
                    .role(Role.Client)
                    .birthday(birthday)
                    .authProvider(AuthenticationProvider.valueOf(client.toUpperCase()))
                    .build();
            newuser.setCreationDate(Instant.now());
            repository.save(newuser);
            var jwtToken = jwtService.generateToken(newuser);
            this.tokenFromOauth2=jwtToken;
            System.out.println("++++++++++++ token : * " +jwtToken);
            return AuthenticationResponse.builder().token(jwtToken).build();
        }

        else {
            System.out.println("+++++ user exist deja");
            var user2 = repository.findByEmail(email)
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user2);
            this.tokenFromOauth2=jwtToken;
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
    }

}
