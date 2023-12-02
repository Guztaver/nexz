package com.guztaver.nexz.restservice.controllers;

import com.guztaver.nexz.restservice.models.RpgCharacter;
import com.guztaver.nexz.restservice.models.RpgExpertise;
import com.guztaver.nexz.restservice.repositories.RpgCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v3/character")
public class RpgExpertiseController {

    @Autowired
    private RpgCharacterRepository rpgCharacterRepository;

    @PostMapping("/{id}/expertise")
    public ResponseEntity<String> addExpertiseToCharacter(@PathVariable("id") Integer id,
                                                          @RequestBody RpgExpertise expertise) {

        Optional<RpgCharacter> optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgCharacter character = optionalCharacter.get();
            character.setExpertise(expertise);
            rpgCharacterRepository.save(character);

            return ResponseEntity.ok("Expertise added to Character with ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID " + id + " não encontrado. Não foi possível adicionar expertise.");
        }
    }

    @GetMapping("/{id}/expertise")
    public ResponseEntity<?> getExpertiseOfCharacter(@PathVariable("id") Integer id) {
        Optional<RpgCharacter> optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgExpertise expertise = optionalCharacter.get().getExpertise();
            return ResponseEntity.ok(expertise != null ? expertise : "Nenhuma expertise encontrada para o Personagem com ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID " + id + " não encontrado. Não foi possível obter expertise.");
        }
    }

    @PutMapping("/{id}/expertise")
    public ResponseEntity<String> updateExpertiseOfCharacter(@PathVariable("id") Integer id,
                                                             @RequestBody RpgExpertise expertise) {

        Optional<RpgCharacter> optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgCharacter character = optionalCharacter.get();
            character.setExpertise(expertise);
            rpgCharacterRepository.save(character);

            return ResponseEntity.ok("Expertise updated for Character with ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID " + id + " não encontrado. Não foi possível atualizar expertise.");
        }
    }

    @DeleteMapping("/{id}/expertise")
    public ResponseEntity<String> deleteExpertiseOfCharacter(@PathVariable("id") Integer id) {
        Optional<RpgCharacter> optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgCharacter character = optionalCharacter.get();
            character.setExpertise(null);
            rpgCharacterRepository.save(character);

            return ResponseEntity.ok("Expertise deleted for Character with ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID " + id + " não encontrado. Não foi possível deletar expertise.");
        }
    }
}