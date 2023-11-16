package com.guztaver.nexz.restservice.repositories;

import com.guztaver.nexz.restservice.entities.Character;
import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<Character, Integer> {}