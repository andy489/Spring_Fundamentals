package com.example.mvcconf.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Map;

@Component
public class TestHandlers {

    public ServerResponse test(ServerRequest serverRequest){

        return ServerResponse.ok().render("test", Map.of("message", "Hello, Pesho!"));
    }

}
