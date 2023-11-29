package com.guztaver.nexz.restservice.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Table(name = "characters")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class RpgCharacter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_id_seq")
    @SequenceGenerator(name = "character_seq_generator", sequenceName = "character_id_seq")
    @Column(name = "ID", nullable = false, updatable = false)
    private Integer id;

    @Size(max = 60)
    @NotBlank
    @Column(name = "NAME", nullable = false)
    @Setter
    private String name;

    @PositiveOrZero
    @Column(name = "AGE", nullable = false)
    @Setter
    private int age;

    @Embedded
    private RpgAttributes attributes;
}
