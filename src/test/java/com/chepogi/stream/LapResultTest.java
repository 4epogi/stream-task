package com.chepogi.stream;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LapResultTest {
    private static final String PILOT_NAME = "pilot";
    private static final String CAR_TITLE = "car";

    @Test
    void shouldCountLapTimeInMillis() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        LocalDateTime lapStartTime = LocalDateTime.parse("2019-06-16_18:00:01.100", formatter);
        LocalDateTime lapEndTime = LocalDateTime.parse("2019-06-16_18:01:02.200", formatter);
        LapResult lapResult = new LapResult(PILOT_NAME, CAR_TITLE, lapStartTime, lapEndTime);
        long expected = 61100;

        long actual = lapResult.countLapTime();

        assertEquals(expected, actual);
    }

    @Test
    void shouldCorrectFormatLapTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        LocalDateTime lapStartTime = LocalDateTime.parse("2019-06-16_18:00:01.100", formatter);
        LocalDateTime lapEndTime = LocalDateTime.parse("2019-06-16_18:01:02.200", formatter);
        LapResult lapResult = new LapResult(PILOT_NAME, CAR_TITLE, lapStartTime, lapEndTime);
        String expected = "01:01.100";

        String actual = lapResult.getLapTime();

        assertEquals(expected, actual);
    }
}
