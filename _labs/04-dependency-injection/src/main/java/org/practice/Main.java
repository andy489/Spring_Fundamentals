package org.practice;

import org.practice.config.AppConfig;
import org.practice.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {


//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.practice");

        UserService userService1 = context.getBean(UserService.class);
        UserService userService2 = context.getBean(UserService.class);

        System.out.println(userService1.findYoungestUser().orElseThrow());

        System.out.println(userService1.hashCode());
        System.out.println(userService2.hashCode());
    }
}