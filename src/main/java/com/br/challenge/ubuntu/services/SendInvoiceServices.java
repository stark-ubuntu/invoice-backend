package com.br.challenge.ubuntu.services;

import com.br.challenge.ubuntu.entities.Person;
import io.quarkus.vertx.ConsumeEvent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SendInvoiceServices {


    @ConsumeEvent("send-invoice")
    public void execute(Person person) {

    }

}
