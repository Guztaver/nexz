package com.guztaver.nexz.restservice.controllers;

import com.guztaver.nexz.restservice.models.RpgAttributes;
import com.guztaver.nexz.restservice.models.RpgCharacter;
import com.guztaver.nexz.restservice.repositories.RpgCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RequestMapping("/api/v3/character")
public class RpgAttributesController {

    @Autowired
    private RpgCharacterRepository rpgCharacterRepository;

    @PostMapping("/{id}/attributes")
    public ResponseEntity<String> addAttributesToCharacter(
            @PathVariable("id") Integer id,
            @RequestBody RpgAttributes attributes) {

        Optional<RpgCharacter> optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgCharacter character = optionalCharacter.get();
            character.setAttributes(attributes);
            rpgCharacterRepository.save(character);

            return ResponseEntity.ok("Attributes added to Character with ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Personagem com ID " + id + " não encontrado. Não foi possível adicionar atributos.");
        }
    }

    @GetMapping("/{id}/attributes")
    public ResponseEntity<?> getAttributesOfCharacter(@PathVariable("id") Integer id) {
        Optional<RpgCharacter> optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgAttributes attributes = optionalCharacter.get().getAttributes();
            return ResponseEntity.ok(attributes != null ? attributes
                    : "Nenhum atributo encontrado para o Personagem com ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Personagem com ID " + id + " não encontrado. Não foi possível obter atributos.");
        }
    }

    @PutMapping("/{id}/attributes")
    public ResponseEntity<String> updateAttributesOfCharacter(
            @PathVariable("id") Integer id,
            @RequestBody RpgAttributes attributes) {

        Optional<RpgCharacter> optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgCharacter character = optionalCharacter.get();
            character.setAttributes(attributes);
            rpgCharacterRepository.save(character);

            return ResponseEntity.ok("Atributos atualizados para o personagem ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Personagem com ID " + id + " não encontrado. Não foi possível atualizar atributos.");
        }
    }

    @DeleteMapping("/{id}/attributes")
    public ResponseEntity<String> deleteAttributesOfCharacter(@PathVariable("id") Integer id) {
        Optional<RpgCharacter> optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgCharacter character = optionalCharacter.get();
            character.setAttributes(null);
            rpgCharacterRepository.save(character);

            return ResponseEntity.ok("Attributes deleted for Character with ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Personagem com ID " + id + " não encontrado. Não foi possível deletar atributos.");
        }
    }

    @PostMapping("/{id}/attributes/additional")
    public ResponseEntity<String> addAdditionalAttributeToCharacter(
            @PathVariable("id") Integer id,
            @RequestBody Map<String, Integer> additionalAttributes) {

        Optional<RpgCharacter> optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            RpgCharacter character = optionalCharacter.get();

            RpgAttributes attributes = character.getAttributes();
            if (attributes == null) {
                attributes = new RpgAttributes();
                character.setAttributes(attributes);
            }

            attributes.getAdditionalAttributes().putAll(additionalAttributes);

            rpgCharacterRepository.save(character);

            return ResponseEntity.ok("Atributos adicionados ao personagem de ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Personagem com ID " + id +
                            " não encontrado. Não foi possível adicionar atributos adicionais.");
        }
    }
}
