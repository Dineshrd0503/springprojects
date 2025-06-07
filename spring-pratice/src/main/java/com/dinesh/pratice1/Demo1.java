package com.dinesh.pratice1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@ComponentScan(basePackages = "com.dinesh.pratice1")
public class Demo1 {
    @Autowired
    private GreetingService greetingService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Demo1.class);
        Demo1 demo = context.getBean(Demo1.class);
        System.out.println(demo.getGreeting());
        context.close();
    }

    public String getGreeting() {
        return greetingService.greet();
    }
}

@Component
class GreetingService {
    public String greet() {
        return "Hello, this is a Spring illustration using Component and Autowired!";
    }
}