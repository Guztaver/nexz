package com.guztaver.nexz.sqlhandler;

import com.guztaver.nexz.restservice.Character;
import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<Character, Integer> {}