package com.spotifly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpotiflyApplication
//        implements CommandLineRunner
{

//    private final PasswordEncoder encoder;
//
//    @Autowired
//    public SpotiflyApplication(PasswordEncoder encoder) {
//        this.encoder = encoder;
//    }

    public static void main(String[] args) {

        SpringApplication.run(SpotiflyApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        String[] passwords = {"1234"};
//        Arrays.stream(passwords).map(encoder::encode).forEach(System.out::println);
//    }

}
