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
    RpgCharacterRepository rpgCharacterRepository;

    @PostMapping
    ResponseEntity<String> addNewUser(@RequestParam String name, @RequestParam int age) {
        var character = new RpgCharacter();

        character.setName(name); character.setAge(age);

        rpgCharacterRepository.save(character);

        return ResponseEntity.ok().body("CREATED");
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUserById(@PathVariable("id") int id) {
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
    ResponseEntity<String> updateUserById(@PathVariable("id") int id,
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
    ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        var character = rpgCharacterRepository.findById(id);

        if (character.isPresent()) {
            return ResponseEntity.ok().body(character);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem do ID " + id + " não encontrado");
        }
    }

    @GetMapping("/{id}/lore")
    ResponseEntity<String> getUserLore(@PathVariable("id") int id) {
        var character = rpgCharacterRepository.findById(id);

        return character.map(rpgCharacter
                -> ResponseEntity.ok().body(rpgCharacter.getLore())).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persoangem do ID " + id + " não encontrado"));
    }

    @PostMapping("/{id}/lore")
    ResponseEntity<String> addUserLore(@PathVariable("id") int id,
                                       @RequestParam("lore") String lore) {
        var character = new RpgCharacter();

        character.setLore(lore);

        rpgCharacterRepository.save(character);
        return ResponseEntity.ok().body("CREATED");
    }

    @GetMapping("/all")
    ResponseEntity<Iterable<RpgCharacter>> getAllUsers() {
        return ResponseEntity.ok().body(rpgCharacterRepository.findAll());
    }
}