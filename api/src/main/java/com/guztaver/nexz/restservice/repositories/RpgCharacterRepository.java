package com.guztaver.nexz.restservice.repositories;

import com.guztaver.nexz.restservice.entities.RpgCharacter;
import org.springframework.data.repository.CrudRepository;

public interface RpgCharacterRepository extends CrudRepository<RpgCharacter, Integer> {}