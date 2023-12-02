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
    private Integer life;

    @PositiveOrZero
    @Column(name = "MANA")
    private Integer mana;

    @PositiveOrZero
    @Column(name = "SANITY")
    private Integer sanity;

    @PositiveOrZero
    @Column(name = "STRENGTH")
    private Integer strength;

    @PositiveOrZero
    @Column(name = "DEXTERITY")
    private Integer dexterity;

    @PositiveOrZero
    @Column(name = "INTELLIGENCE")
    private Integer intelligence;

    @ElementCollection
    private Map<String, Integer> additionalAttributes = new HashMap<>();
}
