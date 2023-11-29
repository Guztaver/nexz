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

        if (character.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID "
                    + id + " não encontrado. Não foi possível deletar.");
        } else {
            rpgCharacterRepository.deleteById(id); return ResponseEntity.ok("DELETED");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable("id") int id,
                                                 @RequestParam String name,
                                                 @RequestParam int age) {
        Optional<RpgCharacter> optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID " + id +
                    " não encontrado. Não foi possível atualizar.");
        } else {
            RpgCharacter character = optionalCharacter.get(); character.setName(name); character.setAge(age);
            rpgCharacterRepository.save(character);

            return ResponseEntity.ok("UPDATED");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@PathVariable("id") int id) {
        var character = rpgCharacterRepository.findById(id);

        return character.map(rpgCharacter
                -> ResponseEntity.ok(rpgCharacter.toString())).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem com ID " + id + " não encontrado."));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<RpgCharacter>> getAllUsers() {
        return ResponseEntity.ok().body(rpgCharacterRepository.findAll());
    }

    // Attributes
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
    public ResponseEntity<String> getAttributesOfCharacter(@PathVariable("id") Integer id) {
        Optional<RpgCharacter> optionalCharacter = rpgCharacterRepository.findById(id);

        if (optionalCharacter.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Personagem com ID " + id + " não encontrado. Não foi possível obter atributos.");
        } else {
            RpgAttributes attributes = optionalCharacter.get().getAttributes();
            return ResponseEntity.ok(attributes != null ? attributes.toString()
                    : "Nenhum atributo encontrado para o Personagem com ID: " + id);
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

            return ResponseEntity.ok("Attributes updated for Character with ID: " + id);
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

            return ResponseEntity.ok("Additional attributes added to Character with ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Personagem com ID " + id +
                            " não encontrado. Não foi possível adicionar atributos adicionais.");
        }
    }
}