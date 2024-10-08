package com.br.challenge.ubuntu.services;

import com.br.challenge.ubuntu.entities.Person;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@QuarkusTest
class RandomlySearchForPeopleServicesTest {

    @Inject
    RandomlySearchForPeopleServices randomlySearchForPeopleServices;

    @Test
    void shouldSearchForPeopleAtRandom() {
        PanacheMock.mock(Person.class);
        when(Person.randomPersons(anyInt())).thenReturn(List.of(new Person(
                UUID.fromString("0917aa8a-076c-4e1f-ac81-503e1c4bfb2e"),
                "John",
                "12345678901"
        )));

        Person person = randomlySearchForPeopleServices.execute().get(0);
        assertEquals("John", person.getName());
    }

}