package com.br.challenge.ubuntu.services;

import com.starkbank.Invoice;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@QuarkusTest
class SendInvoiceServicesTest {

    @Inject
    SendInvoiceServices sendInvoiceServices;

    @Test
    void shouldCreateInvoices() throws Exception {
        SendInvoiceServices sendInvoiceServices2 = mock(SendInvoiceServices.class);

        int amount = new Random().nextInt(100, 1000000);
        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", amount);
        data.put("name", "John");
        data.put("taxId", "85666113062");
        List<Invoice> invoices = List.of(new Invoice(data));
        when(sendInvoiceServices2.execute(invoices)).thenReturn(invoices);

        List<Invoice> result = sendInvoiceServices.execute(invoices);
        assertNotNull(result);
        assertEquals(invoices.size(), result.size());
    }

}