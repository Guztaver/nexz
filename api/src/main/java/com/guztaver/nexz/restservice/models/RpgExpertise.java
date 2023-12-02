package com.guztaver.nexz.restservice.models;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RpgExpertise {

    @ElementCollection
    @Setter
    private Map<String, Integer> expertises = new HashMap<>();
}