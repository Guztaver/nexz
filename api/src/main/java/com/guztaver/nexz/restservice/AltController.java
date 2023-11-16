package com.guztaver.nexz.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/alt")
public class AltController {

    private static final String template = "Hello, %s";

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/character/test")
    public Character character(@RequestParam(value = "name", defaultValue = "User") String name, @RequestParam(value = "age", defaultValue = "1") int age) {
        var character = new Character();

        character.setName(name); character.setAge(age);

        return character;
    }


    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "User") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}