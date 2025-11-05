package com.exsoinn.spring.practice.mustache;

import com.exsoinn.spring.practice.mustache.model.Person;
import com.exsoinn.spring.practice.mustache.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller for person CRUD operations.
 * Provides endpoints for dynamic AJAX-based operations.
 */
@RestController
@RequestMapping("/api/persons")
public class PersonRestController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonRestController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Get all persons.
     *
     * @return list of all persons
     */
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return ResponseEntity.ok(persons);
    }

    /**
     * Get a specific person by ID.
     *
     * @param id the person's ID
     * @return the person if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        return personRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new person.
     *
     * @param person the person to create
     * @return the created person
     */
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    /**
     * Update an existing person.
     *
     * @param id the person's ID
     * @param person the updated person data
     * @return the updated person
     */
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return personRepository
                .findById(id)
                .map(existingPerson -> {
                    existingPerson.setName(person.getName());
                    existingPerson.setAddress(person.getAddress());
                    Person updatedPerson = personRepository.save(existingPerson);
                    return ResponseEntity.ok(updatedPerson);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a person.
     *
     * @param id the person's ID
     * @return no content on success
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
