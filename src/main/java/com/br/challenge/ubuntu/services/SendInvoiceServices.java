package com.br.challenge.ubuntu.services;

import com.starkbank.Invoice;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class SendInvoiceServices {


    public List<Invoice> execute(List<Invoice> invoices) throws Exception {
        return Invoice.create(invoices);
    }

}
