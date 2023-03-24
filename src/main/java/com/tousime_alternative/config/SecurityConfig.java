package com.tousime_alternative.config;

import com.tousime_alternative.OAuth2.CustomOAuth2User;
import com.tousime_alternative.OAuth2.CustomOAuth2UserService;
import com.tousime_alternative.OAuth2.OAuth2LoginSuccessHandler;
import com.tousime_alternative.OAuth2.OAuthUserService;
import com.tousime_alternative.service.auth.AuthenticationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final OAuthUserService oAuthUserService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                .requestMatchers("/oauth2/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .oauth2Login()
                .userInfoEndpoint().userService(oauthUserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        System.out.println("AuthenticationSuccessHandler invoked");
                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                        System.out.println("****************** user "+oauthUser.getAttributes());
                        oAuthUserService.processOAuthPostLogin(oauthUser.getEmail(),oauthUser.fisrtName(),oauthUser.LastName(),oauthUser.Image(),oauthUser.client(),oauthUser.getName(),oauthUser.getImageFromFacebook(),oauthUser.getbirthday());
                        response.sendRedirect("/api/v1/auth/oAuth2Token");
                    }
                })
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Autowired
    private CustomOAuth2UserService oauthUserService;
}
