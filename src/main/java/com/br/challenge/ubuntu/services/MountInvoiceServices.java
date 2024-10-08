package com.br.challenge.ubuntu.services;

import com.br.challenge.ubuntu.entities.Person;
import com.starkbank.Invoice;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class MountInvoiceServices {

    public List<Invoice> execute(List<Person> persons) {
        return persons.stream().map(this::invoiceInformation).toList();
    }

    private Invoice invoiceInformation(Person person) {
        try {
            int amount = new Random().nextInt(100, 1000000);
            HashMap<String, Object> data = new HashMap<>();
            data.put("amount", amount);
            data.put("name", person.getName());
            data.put("taxId", person.getTaxId());
            return new Invoice(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
