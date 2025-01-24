package com.example.ups.poo.controllers;

import com.example.ups.poo.dto.Person;
import com.example.ups.poo.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/get-all-people")
    public List<Person> getAllPeople(){
        return personService.getAllPeople();

    }
}
