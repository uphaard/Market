package com.example.market;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class greetingController {
    public greetingController(@Value("${greeting}") String greetingMessage){
        this.greetingMessage = greetingMessage;
        this.greeting();
    }
    @Value("${greeting}")
    private String greetingMessage;

    public void greeting() {
        System.out.println("!!! "+ this.greetingMessage);
    }
}
