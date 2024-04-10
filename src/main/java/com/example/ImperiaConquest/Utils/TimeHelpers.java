package com.example.ImperiaConquest.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class TimeHelpers {
    public long calculateHoursDifference(LocalDateTime time) {
        long hoursDifference = ChronoUnit.HOURS.between(time, LocalDateTime.now());
        return Math.abs(hoursDifference);
    }

    public long calculateMinutesRemaining(LocalDateTime time) {
        long minutes = 60 - ChronoUnit.MINUTES.between(time, LocalDateTime.now());
        return Math.abs(minutes);
    }

    public boolean canCollect(LocalDateTime lastCollected) {
        return calculateHoursDifference(lastCollected) <= 1;
    }
}
