package com.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BeansApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BeansApplication.class, args);
        context.close();
    }

    @Bean(destroyMethod = "destroy", initMethod = "init")
    public Animal getDog() {
        return new Dog();
    }

}

class Dog implements Animal {
    public Dog() {
        System.out.println("Instantiation...");
    }

    public void init() {
        System.out.println("Initializing...");
    }

    public void destroy() {
        System.out.println("Destroying...");
    }
}

interface Animal {

    void init();

    void destroy();
}
