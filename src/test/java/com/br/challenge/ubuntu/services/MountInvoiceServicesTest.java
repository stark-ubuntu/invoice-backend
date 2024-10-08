package com.br.challenge.ubuntu.services;

import com.br.challenge.ubuntu.entities.Person;
import com.starkbank.Invoice;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@QuarkusTest
class MountInvoiceServicesTest {

    @Inject
    MountInvoiceServices mountInvoiceServices;

    @Test
    void shouldMountInvoice() throws Exception {
        List<Person> persons = List.of(new Person(
                UUID.fromString("0917aa8a-076c-4e1f-ac81-503e1c4bfb2e"),
                "John",
                "85666113062"
        ));

        List<Invoice> invoices = mountInvoiceServices.execute(persons);

        assertNotNull(invoices);
    }

}