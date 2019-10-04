package com.example.springbootdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liang Zhou
 */
@RestController
public class HelloController
{
    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Greetings from Spring Boot! " + name;
    }
}
