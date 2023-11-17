package com.guztaver.nexz.restservice.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Table(name = "rpg_character")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RpgCharacter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rpg_character_seq_generator")
    @SequenceGenerator(name = "rpg_character_seq_generator", sequenceName = "rpg_character_id_seq", allocationSize = 1)
    @Column(name = "ID", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "CHARACTER_NAME")
    @Setter
    private String name;

    @Column(name = "AGE")
    @Setter
    private int age;
}