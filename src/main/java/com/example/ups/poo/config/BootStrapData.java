package com.example.ups.poo.config;

import com.example.ups.poo.entity.Person;
import com.example.ups.poo.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PersonRepository personRepository;

    public BootStrapData(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Person p1 = new Person();
        p1.setName("Rauw");
        p1.setLastname("Alejandro");
        p1.setAge(25);
        p1.setPersonId("0943803188");

        Person p2 = new Person();
        p2.setName("Benito");
        p2.setLastname("Ocasio");
        p2.setAge(25);
        p2.setPersonId("1204234130");

        personRepository.save(p1);
        personRepository.save(p2);

        System.out.println("-------------Started BootStrapData");
        System.out.println("Number of Person: " + personRepository.count());
    }
}
