package com.br.challenge.ubuntu.services;

import com.br.challenge.ubuntu.entities.Person;
import com.br.challenge.ubuntu.helpers.LoggingResource;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Random;

@ApplicationScoped
public class RandomlySearchForPeopleServices extends LoggingResource {

    public List<Person> execute() {
        info("Searching for people at random");
        int bound = new Random().nextInt(8, 13);
        return Person.randomPersons(bound);
    }
}
