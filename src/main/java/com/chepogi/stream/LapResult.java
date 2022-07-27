package com.chepogi.stream;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class LapResult {
    private String pilot;
    private String car;
    private LocalDateTime lapStartTime;
    private LocalDateTime lapEndTime;

    public LapResult(String pilot, String car, LocalDateTime lapStartTime, LocalDateTime lapEndTime) {
        this.pilot = pilot;
        this.car = car;
        this.lapStartTime = lapStartTime;
        this.lapEndTime = lapEndTime;
    }

    public long countLapTime() {
        return Duration.between(lapStartTime, lapEndTime).toMillis();
    }

    public String getLapTime() {
        Duration dur = Duration.between(lapStartTime, lapEndTime);
        long millis = dur.toMillis();

        return String.format("%02d:%02d.%03d", 
                TimeUnit.MILLISECONDS.toMinutes(millis) - 
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - 
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)),
                TimeUnit.MILLISECONDS.toMillis(millis) -
                TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis)));
    }

    public String getPilot() {
        return pilot;
    }

    public String getCar() {
        return car;
    }

    public LocalDateTime getLapStartTime() {
        return lapStartTime;
    }

    public LocalDateTime getLapEndTime() {
        return lapEndTime;
    }
}
