package com.tousime_alternative.OAuth2;


import java.sql.Date;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {

    private OAuth2User oauth2User;
    private  String clientName;
    private java.sql.Date myBirthday;
    private String profilePictureUrl;

    public CustomOAuth2User(OAuth2User oauth2User,String clientName,java.sql.Date myBirthday,String profilePictureUrl) {
        this.oauth2User = oauth2User;
        this.clientName=clientName;
        this.myBirthday=myBirthday;
        this.profilePictureUrl=profilePictureUrl;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }

    public String getEmail() {
        return oauth2User.<String>getAttribute("email");
    }
    public String fisrtName() {
        return oauth2User.getAttribute("given_name");
    }
    public String LastName() {
        return oauth2User.getAttribute("family_name");
    }
    public String Image() {
        return oauth2User.getAttribute("picture");
    }
    public String client(){
        return this.clientName;
    }
    public String getImageFromFacebook(){
        return this.profilePictureUrl;
    }
    public Date getbirthday(){
        return this.myBirthday;
    }

}
