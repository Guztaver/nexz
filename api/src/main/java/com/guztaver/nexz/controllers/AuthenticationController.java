package com.guztaver.nexz.controllers;

import com.guztaver.nexz.config.JwtUtil;
import com.guztaver.nexz.models.AppUser;
import com.guztaver.nexz.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private AppUserRepository userRepository;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.CREATED)
  public String signup(@RequestBody AppUser user) {

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return "Usuário criado com sucesso!";
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody @NotNull AppUser request) {
    final var user = userRepository.findByUsernameIs(request.getUsername());
    if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      return ResponseEntity.ok().body(jwtUtil.generateToken(user));
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A error have ocurred!");
    }
  }
}
