package com.tousime_alternative.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.jconsole.JConsoleContext;
import com.tousime_alternative.OAuth2.CustomOAuth2User;
import com.tousime_alternative.OAuth2.CustomOAuth2UserService;
import com.tousime_alternative.OAuth2.OAuthUserService;
import com.tousime_alternative.dto.auth.AuthenticationResponse;
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

import javax.xml.bind.SchemaOutputResolver;
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
                .requestMatchers("/api/v1/accomodation/all")
                .permitAll()
                .requestMatchers("/api/v1/accomodation/**")
                .permitAll()
                .requestMatchers("/api/v1/event/all")
                .permitAll()
                .requestMatchers("/api/v1/event/**")
                .permitAll()
                .requestMatchers("/api/v1/restoration/all")
                .permitAll()
                .requestMatchers("/api/v1/restoration/**")
                .permitAll()
                .requestMatchers("/api/v1/artisan/all")
                .permitAll()
                .requestMatchers("/api/v1/artisan/**")
                .permitAll()
                .requestMatchers("/api/v1/offer/all")
                .permitAll()
                .requestMatchers("/api/v1/offer/recent")
                .permitAll()
                .requestMatchers("/api/v1/offer/mostliked")
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
                        oAuthUserService.processOAuthPostLogin(oauthUser.getEmail(),oauthUser.fisrtName(),oauthUser.LastName(),oauthUser.Image(),oauthUser.client(),oauthUser.getName(),oauthUser.getImageFromFacebook(),oauthUser.getbirthday());
                        AuthenticationResponse authResponse = oAuthUserService.generateTokenAfterLogin(oauthUser.getEmail());
                        ObjectMapper objectMapper = new ObjectMapper();
                        String jsonResponse = objectMapper.writeValueAsString(authResponse);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(jsonResponse);
                        response.sendRedirect("http://localhost:3000/sign/in/"+"?token=" + authResponse.getToken()+"&user="+authResponse.getUser());


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
