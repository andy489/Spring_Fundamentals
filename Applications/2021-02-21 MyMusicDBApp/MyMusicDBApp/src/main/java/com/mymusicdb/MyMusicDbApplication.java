package com.mymusicdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class MyMusicDbApplication
//        implements CommandLineRunner
{
//    private final PasswordEncoder encoder;
//
//    @Autowired
//    public MyMusicDbApplication(PasswordEncoder encoder) {
//        this.encoder = encoder;
//    }
    public static void main(String[] args) {
        SpringApplication.run(MyMusicDbApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        String[] passwords = {"1234"};
//        Arrays.stream(passwords).map(encoder::encode).forEach(System.out::println);
//    }

}
