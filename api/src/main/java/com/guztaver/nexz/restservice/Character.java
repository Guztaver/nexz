package com.guztaver.nexz.restservice;

import jakarta.persistence.Entity;

@Entity
public record Character(long id, String name, int age, String bornAt) {
}
