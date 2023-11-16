package com.guztaver.nexz.restservice.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Table(name = "characters")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Character implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_seq_generator")
    @SequenceGenerator(name = "character_seq_generator", sequenceName = "character_id_seq", allocationSize = 1)
    @Column(name = "ID", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    @Setter
    private String name;

    @Column(name = "AGE")
    @Setter
    private int age;
}