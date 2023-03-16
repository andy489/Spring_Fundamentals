package com;

import com.shoppinglist.model.enumerated.CategoryEnum;
import com.shoppinglist.model.view.ProductsByCategoryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

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
