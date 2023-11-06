package com.guztaver.nexz.restservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record Character(@Id long id, String name, int age, String bornAt) {
}
