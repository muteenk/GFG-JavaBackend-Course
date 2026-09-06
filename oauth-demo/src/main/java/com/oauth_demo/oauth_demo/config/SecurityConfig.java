package com.oauth_demo.oauth_demo.config;

import com.oauth_demo.oauth_demo.services.CustomOAuth2UserService;
import com.oauth_demo.oauth_demo.services.CustomOAuthUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CustomOAuthUserService customOAuthUserService,
            CustomOAuth2UserService customOAuth2UserService
    ) {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                    auth
                            .requestMatchers("/auth/login-uri/**", "/auth/error").permitAll()
                            .anyRequest().authenticated()
                )
                .oauth2Login(oauth ->
                    oauth
                            .userInfoEndpoint(userInfo ->
                                    userInfo
                                            .oidcUserService(customOAuthUserService)
                                            .userService(customOAuth2UserService)
                            )
                            .defaultSuccessUrl("/auth/profile", true)
                            .failureUrl("/auth/error")
                );
        return http.build();
    }
}
