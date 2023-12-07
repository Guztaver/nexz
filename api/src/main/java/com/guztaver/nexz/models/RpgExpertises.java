package com.guztaver.nexz.models;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RpgExpertises {
    @ElementCollection
    private Map<String, Integer> additionalExpertises = new HashMap<>();
}
