package com.guztaver.nexz.models;


import jakarta.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.*;

@Table(name = "rpg_character")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class RpgCharacter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rpg_character_seq_generator")
    @SequenceGenerator(name = "rpg_character_seq_generator", sequenceName = "rpg_character_id_seq", allocationSize = 1)
    @Column(name = "CHARACTER_ID", nullable = false, updatable = false)
    private Integer id;

    @Size(max = 60)
    @NotBlank
    @Setter
    @Column(name = "NAME", nullable = false)
    private String name;

    @PositiveOrZero
    @Setter
    @Nullable
    @Column(name = "AGE")
    private Integer age;

    @Setter
    @Nullable
    @Column(name = "LORE")
    private String lore;

    @Embedded
    @Nullable
    @Setter
    @Column(name = "ATTRIBUTES")
    private RpgAttributes attributes;

    @Embedded
    @Nullable
    @Setter
    @Column(name = "EXPERTISES")
    private RpgExpertises expertises;
}
