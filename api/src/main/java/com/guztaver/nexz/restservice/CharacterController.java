package com.guztaver.nexz.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(method = GET)
public class CharacterController {
private static final String template = "Hello, %s";

private final java.util.concurrent.atomic.AtomicLong counter = new java.util.concurrent.atomic.AtomicLong();

@GetMapping("/character")
public Character character(@RequestParam(value = "name", defaultValue = "User") String name, @RequestParam(value = "age", defaultValue = "1") int age, @RequestParam(value = "bornAt", defaultValue = "01/01/2001") String bornAt) {
    return new Character(counter.incrementAndGet(), String.format(template, name), age, bornAt);
}
}