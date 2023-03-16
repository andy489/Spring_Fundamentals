package com.battleships;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BattleshipsApplication /* implements CommandLineRunner */{

//    private final PasswordEncoder encoder;
//
//    @Autowired
//    public BattleshipsApplication(PasswordEncoder encoder) {
//        this.encoder = encoder;
//    }

    public static void main(String[] args) {
        SpringApplication.run(BattleshipsApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(encoder.encode("terran"));
//        System.out.println(encoder.encode("protoss"));
//        System.out.println(encoder.encode("zerg"));
//    }

}
