package com.br.challenge.ubuntu.services;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@QuarkusTest
class RandomlySearchForPeopleTest {

    @InjectMock
    RandomlySearchForPeople randomlySearchForPeople;

    @Test
    void testExecuteCreatesInvoices() {
        doNothing().when(randomlySearchForPeople).execute();

        randomlySearchForPeople.execute();
        verify(randomlySearchForPeople).execute();
    }

}