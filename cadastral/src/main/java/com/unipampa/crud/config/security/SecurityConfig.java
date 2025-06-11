package com.unipampa.crud.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private static final String HOST = "HOST";
    private static final String ADMIN = "ADMINISTRATOR";
    private static final String GUEST = "GUEST";
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final String[] PUBLIC_MATCHERS = {
            "/users/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS).permitAll()
                        .requestMatchers(HttpMethod.POST, "/accommodations/**").hasAnyRole(HOST, ADMIN)
                        .requestMatchers(HttpMethod.GET, "/accommodations/**").hasAnyRole(HOST, GUEST, ADMIN)
                        .requestMatchers(HttpMethod.GET, "/accommodations/{id}").hasAnyRole(HOST, GUEST, ADMIN)
                        .requestMatchers(HttpMethod.PUT, "/accommodations/{id}").hasAnyRole(HOST, ADMIN)
                        .requestMatchers(HttpMethod.DELETE, "/accommodations/{id}").hasAnyRole(HOST, ADMIN)

                        .requestMatchers(HttpMethod.GET, "/users").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/users/email/**").hasAnyRole(ADMIN, HOST, GUEST)
                        .requestMatchers(HttpMethod.GET, "/users/{id}").hasAnyRole(ADMIN, HOST, GUEST)
                        .requestMatchers(HttpMethod.PUT, "/users/{id}").hasAnyRole(ADMIN, HOST, GUEST)
                        .requestMatchers(HttpMethod.DELETE, "/users/{id}").hasAnyRole(ADMIN, HOST, GUEST)

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return builder.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}