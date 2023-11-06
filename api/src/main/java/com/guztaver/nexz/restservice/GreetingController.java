package com.guztaver.nexz.restservice;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(method = RequestMethod.GET)
public class GreetingController {
    private static final String template = "Hello, %s";

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "User") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}