package com.br.challenge.ubuntu.helpers;


import com.br.challenge.ubuntu.services.RandomlySearchForPeople;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;

import java.time.Instant;

@ApplicationScoped
public class InitialProcess {

    @Getter
    private Instant startTime;

    @Inject
    RandomlySearchForPeople randomlySearchForPeople;

    //  cron = "0 0 */3 * * ?"
    @Scheduled(cron = "*/10 0 * * * ?")
    public void execute() {

        boolean condition = !(Instant.now().isAfter(startTime.plusSeconds(24 * 3600)));

        if (condition)
            randomlySearchForPeople.execute();
        else
            System.out.println("Processing completed after 24 hours.");
    }
}
