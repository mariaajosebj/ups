package com.example.ups.poo.service;

import com.example.ups.poo.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    List<Person> personList = new ArrayList<>();

    public ResponseEntity getAllPeople() {
        if (personList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List is empty");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }

    public ResponseEntity getPersonById(String id) {
        for (Person person : personList) {
            if (id.equalsIgnoreCase(person.getId())) {
                return ResponseEntity.status(HttpStatus.OK).body(person);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id; " + id + " not found");
    }

    public ResponseEntity createPerson(Person person) {
        if (person.getId() == null || person.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person ID is required");
        }
        if (person.getName() == null || person.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person Name is required");
        }
        if (person.getLastname() == null || person.getLastname().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person Lastname is required");
        }
        if (person.getAge() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person Age is required");
        }

        for (Person existingPerson : personList) {
            if (existingPerson.getId().equalsIgnoreCase(person.getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person with this ID already exists");
            }
        }
        personList.add(person);
        return ResponseEntity.status(HttpStatus.OK).body("Person successfully registered");
    }

    public ResponseEntity updatePerson(Person person) {
        for (Person per : personList) {
            if (per.getId().equalsIgnoreCase(person.getId())) {
                if (person.getName() != null) {
                    per.setName(person.getName());
                }
                if (person.getLastname() != null) {
                    per.setLastname(person.getLastname());
                }
                if (person.getAge() != 0) {
                    per.setAge(person.getAge());
                }
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Person with id: " + person.getId() + " was successfully updated");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Person with id: " + person.getId() + " not found");
    }

    public ResponseEntity deletePersonById(String id) {
        if (id != null && id.length() < 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id: " + id + "does not have the required length (10 chars min.)");
        }
        for (Person person : personList) {
            if (id.equalsIgnoreCase(person.getId())) {
                personList.remove(person);
                return ResponseEntity.status(HttpStatus.OK).body("Person with id; " + "was successfully deleted");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id: " + id + "was not found");
    }
}


