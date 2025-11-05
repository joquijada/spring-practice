package com.exsoinn.spring.practice.mustache.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity representing a person with name and address.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    /**
     * Constructor for creating a person without an ID (for new records).
     *
     * @param name the person's name
     * @param address the person's address
     */
    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
