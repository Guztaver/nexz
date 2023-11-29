package com.guztaver.nexz.restservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RpgAttributes {
    @PositiveOrZero
    @Column(name = "LIFE")
    @Setter
    private Integer life;

    @PositiveOrZero
    @Column(name = "MANA")
    @Setter
    private Integer mana;

    @PositiveOrZero
    @Column(name = "SANITY")
    @Setter
    private Integer sanity;

    @PositiveOrZero
    @Column(name = "STRENGTH")
    @Setter
    private Integer strength;

    @PositiveOrZero
    @Column(name = "DEXTERITY")
    @Setter
    private Integer dexterity; // Use Integer instead of int

    @PositiveOrZero
    @Column(name = "INTELLIGENCE")
    @Setter
    private Integer intelligence;

    @ElementCollection
    @Getter
    @Setter
    private Map<String, Integer> additionalAttributes = new HashMap<>();
}
