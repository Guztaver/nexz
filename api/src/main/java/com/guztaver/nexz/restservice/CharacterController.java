package com.guztaver.nexz.restservice;

import com.guztaver.nexz.sqlhandler.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/character")
public class CharacterController {
    @Autowired
    private CharacterRepository characterRepository;

    @PostMapping
    public HttpStatus addNewUser(@RequestParam String name, @RequestParam int age) {
        var character = new Character();

        character.setName(name); character.setAge(age);

        characterRepository.save(character);

        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        Optional<Character> character = characterRepository.findById(id);

        if (character.isPresent()) {
            return ResponseEntity.ok(character.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID " + id + " não encontrado.");
        }
    }

    @GetMapping("/all")
    public Iterable<Character> getAllUsers() {
        return characterRepository.findAll();
    }
}