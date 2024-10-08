package com.br.challenge.ubuntu.helpers;


import com.br.challenge.ubuntu.entities.Invoicing;
import com.br.challenge.ubuntu.entities.Person;
import com.br.challenge.ubuntu.services.MountInvoiceServices;
import com.br.challenge.ubuntu.services.RandomlySearchForPeopleServices;
import com.br.challenge.ubuntu.services.SendInvoiceServices;
import com.starkbank.Invoice;
import com.starkbank.utils.Generator;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class InitialProcess {

    @Getter
    private static Instant startTime = Instant.now();

    @Inject
    RandomlySearchForPeopleServices randomlySearchForPeopleServices;

    @Inject
    MountInvoiceServices mountInvoiceServices;

    @Inject
    SendInvoiceServices sendInvoiceServices;

    @Scheduled(cron = "0 0 */3 * * ?")
    @Transactional
    public void execute() throws Exception {

        boolean condition = !(Instant.now().isAfter(startTime.plusSeconds(24 * 3600)));

        if (condition) {
            List<Person> persons = randomlySearchForPeopleServices.execute();
            List<Invoice> invoices = mountInvoiceServices.execute(persons);
            sendInvoiceServices.execute(invoices);
            invoices.forEach(invoice -> {
                Invoicing invoicing = new Invoicing();
                invoicing.setTaxId(invoice.taxId);
                invoicing.setAmount(invoice.amount);
                invoicing.persist();
            });
        } else
            System.out.println("Processing completed after 24 hours.");

        System.out.println("Send invoices");
    }

    private void reduceAmount() throws Exception {
        Generator<Invoice> invoices = Invoice.query();
        invoices.forEach(invoice -> {
            if (invoice.amount.intValue() > 100) {
                try {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("amount", 86);
                    Invoice.update(invoice.id, data);
                    System.out.printf("Reduce amount to %s", invoice.amount);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
