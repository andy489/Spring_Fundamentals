package com.coffeeshop.configuration;

import com.coffeeshop.session.CurrentUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder encode() {
        return new Pbkdf2PasswordEncoder();
    }


    @SessionScope
    @Bean("currentUser")
    public CurrentUser currentUser() {
        return new CurrentUser();
    }

}
