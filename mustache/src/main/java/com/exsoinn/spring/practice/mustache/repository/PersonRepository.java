package com.exsoinn.spring.practice.mustache.repository;

import com.exsoinn.spring.practice.mustache.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for Person entities.
 * Provides CRUD operations out of the box.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
