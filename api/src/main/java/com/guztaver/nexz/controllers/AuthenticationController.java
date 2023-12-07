package com.guztaver.nexz.controllers;

import com.guztaver.nexz.config.*;
import com.guztaver.nexz.models.*;
import com.guztaver.nexz.repositories.*;
import lombok.*;
import org.jetbrains.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody @NotNull AppUser request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        final var user = userRepository.findByUsernameIs(request.getUsername());
        if (user != null) {
            return ResponseEntity.ok().body(jwtUtil.generateToken(user));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A error have ocurred!");
        }
    }
}
