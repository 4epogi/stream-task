package com.chepogi.stream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RacingTest {
    @Test
    void shouldReturnCorrectRacingResults() {
        Racing racing = new Racing();
        String expected = "Sebastian Vettel |FERRARI                  |01:04.415\n"
                        + "Daniel Ricciardo |RED BULL RACING TAG HEUER|01:12.013\n"
                        + "Valtteri Bottas  |MERCEDES                 |01:12.434\n"
                        + "Lewis Hamilton   |MERCEDES                 |01:12.460\n"
                        + "Stoffel Vandoorne|MCLAREN RENAULT          |01:12.463\n"
                        + "Kimi Raikkonen   |FERRARI                  |01:12.639\n"
                        + "Fernando Alonso  |MCLAREN RENAULT          |01:12.657\n"
                        + "Sergey Sirotkin  |WILLIAMS MERCEDES        |01:12.706\n"
                        + "Charles Leclerc  |SAUBER FERRARI           |01:12.829\n"
                        + "Sergio Perez     |FORCE INDIA MERCEDES     |01:12.848\n"
                        + "Romain Grosjean  |HAAS FERRARI             |01:12.930\n"
                        + "Pierre Gasly     |SCUDERIA TORO ROSSO HONDA|01:12.941\n"
                        + "Carlos Sainz     |RENAULT                  |01:12.950\n"
                        + "Esteban Ocon     |FORCE INDIA MERCEDES     |01:13.028\n"
                        + "Nico Hulkenberg  |RENAULT                  |01:13.065\n"
                        + "-----------------------------------------------------\n"
                        + "Brendon Hartley  |SCUDERIA TORO ROSSO HONDA|01:13.179\n"
                        + "Marcus Ericsson  |SAUBER FERRARI           |01:13.265\n"
                        + "Lance Stroll     |WILLIAMS MERCEDES        |01:13.323\n"
                        + "Kevin Magnussen  |HAAS FERRARI             |01:13.393\n";

        String actual = racing.showResults();

        assertEquals(expected, actual);
    }
}
