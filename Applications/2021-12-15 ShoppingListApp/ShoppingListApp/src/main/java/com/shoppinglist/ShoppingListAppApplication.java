package com.shoppinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingListAppApplication
//    implements CommandLineRunner
{
//    private final PasswordEncoder encoder;
//
//    @Autowired
//    public ShoppingListAppApplication(PasswordEncoder encoder) {
//        this.encoder = encoder;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ShoppingListAppApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        String[] passwords = {"1234"};
//        Arrays.stream(passwords).map(encoder::encode).forEach(System.out::println);
//    }
}
