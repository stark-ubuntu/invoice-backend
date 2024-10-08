package com.br.challenge.ubuntu.services;

import com.br.challenge.ubuntu.entities.Person;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Random;

@ApplicationScoped
public class RandomlySearchForPeopleServices {

    public List<Person> execute() {
        int bound = new Random().nextInt(8, 13);
        return Person.randomPersons(bound);
    }
}
