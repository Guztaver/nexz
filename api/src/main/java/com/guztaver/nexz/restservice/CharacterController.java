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
public Character character(@RequestParam(value = "name", defaultValue = "User") String name) {
    return new Character(counter.incrementAndGet(), String.format(template, name), 16, "21/06/2006");
}
}