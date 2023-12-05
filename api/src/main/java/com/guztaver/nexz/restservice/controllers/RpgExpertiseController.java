package com.guztaver.nexz.restservice.controllers;

import com.guztaver.nexz.restservice.models.*;
import com.guztaver.nexz.restservice.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/character")
public class RpgExpertiseController {

    @Autowired
    private RpgCharacterRepository rpgCharacterRepository;

    @PostMapping("/{id}/expertise")
    ResponseEntity<String> addExpertiseToCharacter(@PathVariable("id") Integer id,
                                                   @RequestBody RpgExpertises expertise) {

        var optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgCharacter character = optionalCharacter.get();
            character.setExpertises(expertise);
            rpgCharacterRepository.save(character);

            return ResponseEntity.ok("Expertise added to Character with ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID " + id + " não encontrado. Não foi possível adicionar expertise.");
        }
    }

    @GetMapping("/{id}/expertise")
    ResponseEntity<?> getExpertiseOfCharacter(@PathVariable("id") Integer id) {
        var optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgExpertises expertise = optionalCharacter.get().getExpertises();
            return ResponseEntity.ok(expertise != null ? expertise : "Nenhuma expertise encontrada para o Personagem com ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID " + id + " não encontrado. Não foi possível obter expertise.");
        }
    }

    @PutMapping("/{id}/expertise")
    ResponseEntity<String> updateExpertiseOfCharacter(@PathVariable("id") Integer id,
                                                      @RequestBody RpgExpertises expertise) {

        var optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgCharacter character = optionalCharacter.get();
            character.setExpertises(expertise);
            rpgCharacterRepository.save(character);

            return ResponseEntity.ok("Expertise updated for Character with ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID " + id + " não encontrado. Não foi possível atualizar expertise.");
        }
    }

    @DeleteMapping("/{id}/expertise")
    ResponseEntity<String> deleteExpertiseOfCharacter(@PathVariable("id") Integer id) {
        var optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgCharacter character = optionalCharacter.get();
            character.setExpertises(null);
            rpgCharacterRepository.save(character);

            return ResponseEntity.ok("Expertise deleted for Character with ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID " + id + " não encontrado. Não foi possível deletar expertise.");
        }
    }
}