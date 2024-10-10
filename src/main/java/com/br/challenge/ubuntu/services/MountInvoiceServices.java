package com.br.challenge.ubuntu.services;

import com.br.challenge.ubuntu.entities.Person;
import com.br.challenge.ubuntu.helpers.LoggingResource;
import com.starkbank.Invoice;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class MountInvoiceServices extends LoggingResource {

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
            Invoice invoice = new Invoice(data);
            info("invoice set up for " + person.getName());
            return invoice;
        } catch (Exception e) {
            error(e.getMessage(), e.getCause());
            throw new RuntimeException(e);
        }
    }

}
