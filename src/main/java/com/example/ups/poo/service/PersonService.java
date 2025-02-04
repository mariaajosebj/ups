package com.example.ups.poo.service;

import com.example.ups.poo.dto.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    List<PersonDTO> personDTOList = new ArrayList<>();

    public ResponseEntity getAllPeople() {
        if (personDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List is empty");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personDTOList);
    }

    public ResponseEntity getPersonById(String id) {
        for (PersonDTO personDTO : personDTOList) {
            if (id.equalsIgnoreCase(personDTO.getId())) {
                return ResponseEntity.status(HttpStatus.OK).body(personDTO);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id; " + id + " not found");
    }

    public ResponseEntity createPerson(PersonDTO personDTO) {
        if (personDTO.getId() == null || personDTO.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person ID is required");
        }
        if (personDTO.getName() == null || personDTO.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person Name is required");
        }
        if (personDTO.getLastname() == null || personDTO.getLastname().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person Lastname is required");
        }
        if (personDTO.getAge() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person Age is required");
        }

        for (PersonDTO existingPersonDTO : personDTOList) {
            if (existingPersonDTO.getId().equalsIgnoreCase(personDTO.getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person with this ID already exists");
            }
        }
        personDTOList.add(personDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Person successfully registered");
    }

    public ResponseEntity updatePerson(PersonDTO personDTO) {
        for (PersonDTO per : personDTOList) {
            if (per.getId().equalsIgnoreCase(personDTO.getId())) {
                if (personDTO.getName() != null) {
                    per.setName(personDTO.getName());
                }
                if (personDTO.getLastname() != null) {
                    per.setLastname(personDTO.getLastname());
                }
                if (personDTO.getAge() != 0) {
                    per.setAge(personDTO.getAge());
                }
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Person with id: " + personDTO.getId() + " was successfully updated");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Person with id: " + personDTO.getId() + " not found");
    }

    public ResponseEntity deletePersonById(String id) {
        if (id != null && id.length() < 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id: " + id + "does not have the required length (10 chars min.)");
        }
        for (PersonDTO personDTO : personDTOList) {
            if (id.equalsIgnoreCase(personDTO.getId())) {
                personDTOList.remove(personDTO);
                return ResponseEntity.status(HttpStatus.OK).body("Person with id; " + "was successfully deleted");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id: " + id + "was not found");
    }
}


