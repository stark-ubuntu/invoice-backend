package com.br.challenge.ubuntu.helpers;

import com.br.challenge.ubuntu.entities.Invoicing;
import com.br.challenge.ubuntu.entities.Person;
import com.br.challenge.ubuntu.services.MountInvoiceServices;
import com.br.challenge.ubuntu.services.RandomlySearchForPeopleServices;
import com.br.challenge.ubuntu.services.SendInvoiceServices;
import com.starkbank.Invoice;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class InitialProcess extends LoggingResource {

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
            info("Processing invoices ...");

            List<Person> persons = randomlySearchForPeopleServices.execute();
            List<Invoice> invoices = mountInvoiceServices.execute(persons);

            sendInvoiceServices.execute(invoices);
            info("invoices processed and send to Stark Bank");

            invoices.forEach(invoice -> {
                Invoicing invoicing = new Invoicing();
                invoicing.setTaxId(invoice.taxId);
                invoicing.setAmount(invoice.amount.intValue());
                invoicing.persist();
            });
        } else
            info("Processing completed after 24 hours.");

        info("Send invoices");
    }
}
