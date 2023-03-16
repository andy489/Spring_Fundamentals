package com.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.DecimalFormat;
import java.util.Arrays;

@SpringBootApplication
public class SpotifyAppApplication
//        implements CommandLineRunner
{

//    private final PasswordEncoder encoder;
//
//    @Autowired
//    public SpotifyAppApplication(PasswordEncoder encoder) {
//        this.encoder = encoder;
//    }

    public static void main(String[] args) {

        SpringApplication.run(SpotifyAppApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        String[] passwords = {"1234"};
//        Arrays.stream(passwords).map(encoder::encode).forEach(System.out::println);
//    }

}
