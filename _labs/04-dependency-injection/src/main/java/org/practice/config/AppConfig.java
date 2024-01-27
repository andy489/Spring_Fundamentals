package org.practice.config;

import org.practice.repository.FileBasedUserRepository;
import org.practice.repository.InMemoryUserRepository;
import org.practice.repository.UserRepository;
import org.practice.service.UserService;
import org.practice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean(name = "in-memory")
    public UserRepository inMemoryUserRepository(){
        return new InMemoryUserRepository();
    }

    @Bean(name="file")
    public UserRepository fileBasedUserRepository(){
        return new FileBasedUserRepository();
    }

//    @Bean(name="myUserService")
//    public UserService userService(@Qualifier(value = "file") UserRepository userRepository){
//        return new UserServiceImpl(userRepository);
//    }

    @Bean(name="myUserServiceSelectedFromAllAvailableBeans")
//    @Scope(value = "prototype")
    public UserService userService(List<UserRepository> userRepositories){
        return new UserServiceImpl(userRepositories.get(0));
    }

//    <bean id="inMemoryUserRepository"
//    class="org.practice.repository.InMemoryUserRepository"/>
//
//    <bean id="fileBasedUserRepository"
//    class="org.practice.repository.FileBasedUserRepository"/>
//
//    <bean id="userService"
//    class="org.practice.service.UserServiceImpl">
//        <constructor-arg type="org.practice.repository.UserRepository"
//    ref="fileBasedUserRepository"/>
//    </bean>

}
