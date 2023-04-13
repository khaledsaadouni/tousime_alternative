package com.tousime_alternative.OAuth2;

import com.tousime_alternative.dto.UserDto;
import com.tousime_alternative.dto.auth.AuthenticationResponse;
import com.tousime_alternative.model.AuthenticationProvider;
import com.tousime_alternative.model.User;
import com.tousime_alternative.model.enumr.Role;
import com.tousime_alternative.repository.UserRepository;
import com.tousime_alternative.service.UserService;
import com.tousime_alternative.service.auth.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OAuthUserService {
    private final UserRepository repo;

    private final JwtService jwtService;
    private final UserService userService;


    public void processOAuthPostLogin(String email, String fisrtname, String lastname, String image, String client, String name, String facebookpicture, Date birthday) {
        var user = repo.findByEmail(email);
        System.out.println("+++ user " + user);
        if (fisrtname == null) {
            String[] splitedname = name.split("\\s+");
            fisrtname = splitedname[0];
            lastname = splitedname[1];
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
            repo.save(newuser);
        }

        else {
            System.out.println("+++++ user exist deja");
        }
    }

    public AuthenticationResponse generateTokenAfterLogin(String email){
        System.out.println("Generate  token ... ");
        var user2 = repo.findByEmail(email)
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user2);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(UserDto.fromEntity(user2))
                .build();
    }

}
