package com.guztaver.nexz.restservice.controllers;

import com.guztaver.nexz.restservice.models.RpgCharacter;
import com.guztaver.nexz.restservice.repositories.RpgCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v3/character")
public class RpgCharacterController {
    @Autowired
    private RpgCharacterRepository rpgCharacterRepository;

    @PostMapping
    public ResponseEntity<String> addNewUser(@RequestParam String name, @RequestParam int age) {
        var character = new RpgCharacter();

        character.setName(name); character.setAge(age);

        rpgCharacterRepository.save(character);

        return ResponseEntity.ok("CREATED");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") int id) {
        Optional<RpgCharacter> character = rpgCharacterRepository.findById(id);

        if (character.isPresent()) {
            rpgCharacterRepository.deleteById(id);
            return ResponseEntity.ok("DELETED");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID "
                    + id + " não encontrado. Não foi possível deletar.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable("id") int id,
                                                 @RequestParam String name,
                                                 @RequestParam int age) {
        Optional<RpgCharacter> optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgCharacter character = optionalCharacter.get(); character.setName(name); character.setAge(age);
            rpgCharacterRepository.save(character);

            return ResponseEntity.ok("UPDATED");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID " + id +
                    " não encontrado. Não foi possível atualizar.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        var character = rpgCharacterRepository.findById(id);

        if (character.isPresent()) {
            return ResponseEntity.ok().body(character);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem do ID " + id + " não encontrado");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<RpgCharacter>> getAllUsers() {
        return ResponseEntity.ok().body(rpgCharacterRepository.findAll());
    }
}