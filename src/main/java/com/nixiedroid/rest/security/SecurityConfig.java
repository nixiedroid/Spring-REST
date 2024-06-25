package com.nixiedroid.rest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringJoiner;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class SecurityConfig   {
    // https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
                .cors(c->c.disable())
                .authorizeHttpRequests(r ->
                        r.requestMatchers("/actuator/**").hasRole("ADMIN")
                                .requestMatchers("/info/**").hasRole("USER")
                                .requestMatchers("/**").permitAll()
                )
                .formLogin(withDefaults());
        return http. build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User. builder()
                .username("user")
                .password(encodedPassword().encode("password"))
                .roles("USER")
                .build();
        UserDetails admin = User. builder()
                .username("admin")
                .password(encodedPassword().encode("password"))
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder encodedPassword() {
//        MessageDigest digest;
//        try {
//            digest = MessageDigest.getInstance("SHA-256");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                StringJoiner hexString = new StringJoiner("");
//                for (byte b : digest.digest(((String)rawPassword).getBytes(StandardCharsets.UTF_8))) {
//                    hexString.add(String.format("%02X", b & 0xFF));
//                }
//                return hexString.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return encodedPassword.equals(encode(rawPassword));
//            }
//        };
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
        //  PasswordEncoderFactories.createDelegatingPasswordEncoder();


    }