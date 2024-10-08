package com.br.challenge.ubuntu.services;

import com.br.challenge.ubuntu.entities.Person;
import com.starkbank.Invoice;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@ApplicationScoped
public class RandomlySearchForPeople {

    public void execute() {
        try {
            int random = new Random().nextInt(8, 13);
            List<Person> persons = Person.find("ORDER BY RANDOM()").page(0, random).list();
            List<Invoice> invoices = persons.stream().map(this::createInvoice)
                    .filter(Objects::nonNull)
                    .toList();

            Invoice.create(invoices);
        } catch (Exception e) {
            System.out.printf("There was an error creating the invoice. Message: %s",
                    e.getMessage());
        }

    }

    private Invoice createInvoice(Person person) {
        try {
            int amount = new Random().nextInt(100, Integer.MAX_VALUE);
            HashMap<String, Object> data = new HashMap<>();
            data.put("amount", amount);
            data.put("name", person.getName());
            data.put("taxId", person.getTaxId());
            return new Invoice(data);
        } catch (Exception e) {
            System.out.printf("There was an error creating the invoice. Message: %s",
                    e.getMessage());
        }
        return null;
    }
}
