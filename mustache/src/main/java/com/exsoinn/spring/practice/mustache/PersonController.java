package com.exsoinn.spring.practice.mustache;

import com.exsoinn.spring.practice.mustache.model.Person;
import com.exsoinn.spring.practice.mustache.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for handling person registration and listing.
 * Demonstrates form submission and data persistence with JPA.
 */
@Controller
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Displays the person registration form and list of all registered persons.
     *
     * @param model Spring MVC model for passing data to the view
     * @param editId optional ID of person being edited
     * @return the view name
     */
    @GetMapping("/persons")
    public String showPersonForm(Model model, @RequestParam(required = false) Long editId) {
        List<Person> persons = personRepository.findAll();
        model.addAttribute("persons", persons);

        if (editId != null) {
            Optional<Person> editPerson = personRepository.findById(editId);
            editPerson.ifPresent(person -> {
                model.addAttribute("editPerson", person);
                model.addAttribute("editId", editId);
            });
        }

        return "persons";
    }

    /**
     * Displays the dynamic person registration form (AJAX-based version).
     *
     * @param model Spring MVC model for passing data to the view
     * @return the view name
     */
    @GetMapping("/persons-dynamic")
    public String showDynamicPersonForm(Model model) {
        return "persons-dynamic";
    }

    /**
     * Handles form submission to create a new person record.
     *
     * @param name the person's name
     * @param address the person's address
     * @return redirect to the persons page
     */
    @PostMapping("/persons")
    public String createPerson(@RequestParam String name, @RequestParam String address) {
        Person person = new Person(name, address);
        personRepository.save(person);
        return "redirect:/persons";
    }

    /**
     * Handles form submission to update an existing person record.
     *
     * @param id the person's ID
     * @param name the person's name
     * @param address the person's address
     * @return redirect to the persons page
     */
    @PostMapping("/persons/update")
    public String updatePerson(@RequestParam Long id, @RequestParam String name, @RequestParam String address) {
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            person.setName(name);
            person.setAddress(address);
            personRepository.save(person);
        }
        return "redirect:/persons";
    }

    /**
     * Handles deletion of a person record.
     *
     * @param id the person's ID
     * @return redirect to the persons page
     */
    @PostMapping("/persons/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return "redirect:/persons";
    }
}
