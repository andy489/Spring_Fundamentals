package com.likebook.configuration;

import com.likebook.session.CurrentUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder encode() {

        final String SALT = "pepper";

        final int ITERATIONS = (int) 2e5;

        final int SALT_LENGTH = 10;

        return new Pbkdf2PasswordEncoder(
                SALT,
                SALT_LENGTH,
                ITERATIONS,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256
        );

    }

    @SessionScope
    @Bean
    public CurrentUser currentUser() {
        return new CurrentUser();
    }
}
