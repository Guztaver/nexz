package com.guztaver.nexz.restservice.models;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

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
    @Column(name = "ID", nullable = false, updatable = false)
    private Integer id;

    @Size(max = 60)
    @NotBlank
    @Column(name = "NAME", nullable = false)
    @Setter
    private String name;

    @PositiveOrZero
    @Column(name = "AGE")
    @Setter
    @Nullable
    private Integer age;

    @Embedded
    @Nullable
    private RpgAttributes attributes;
}
