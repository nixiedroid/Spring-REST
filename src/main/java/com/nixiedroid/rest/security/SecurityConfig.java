package com.nixiedroid.rest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringJoiner;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig   {
    // https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(a -> a.anyRequest().authenticated());
        return http.build();
    }
    @Bean
    PasswordEncoder encodedPassword() {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                StringJoiner hexString = new StringJoiner("");
                for (byte b : digest.digest(((String)rawPassword).getBytes(StandardCharsets.UTF_8))) {
                    hexString.add(String.format("%02X", b & 0xFF));
                }
                return hexString.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(encode(rawPassword));
            }
        };
    }
        //  PasswordEncoderFactories.createDelegatingPasswordEncoder();

        @Bean UserDetailsService auth () {
            UserDetails peter = User.builder().username("peter").password(encodedPassword().encode("ppasword")).roles("USER").build();
            UserDetails jodie = User.builder().username("jodie").password(encodedPassword().encode("jpassword")).roles("USER", "ADMIN").build();
            System.out.println(" >>> Peter's password: " + peter.getPassword());
            System.out.println(" >>> Jodie's password: " + jodie.getPassword());
            return new InMemoryUserDetailsManager(peter, jodie);
        }
    }