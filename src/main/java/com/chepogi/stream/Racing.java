package com.chepogi.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Racing {
    private static final String HORIZONTAL_SEPARATOR = "-";
    private static final String VERTICAL_SEPARATOR = "|";
    private static final String WHITE_SPACE = " ";
    private static final String UNDER_LINE = "_";
    private static final int Q2_QUALIFIED_PILOTS = 15;
    private static final String NEW_LINE = "\n";

    private List<LapResult> results = new ArrayList<>();

    public String showResults() {
        readData();
        Collections.sort(results, Comparator.comparingLong(LapResult::countLapTime));
        return formLapResults();
    }

    private LocalDateTime parseStringToDate(String line) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        return LocalDateTime.parse(line.substring(3), formatter);
    }

    private List<String> readFile(String path){
        try(Stream<String> stream = Files.lines(Paths.get(path))){
            return stream.collect(Collectors.toList());
        }
        catch(IOException ex) {
            throw new IllegalArgumentException("Problem with " + path);
        }
    }

    private String parseFromString(String line, int placeInString) {
        return line.split(UNDER_LINE)[placeInString];
    }

    private String formLapResults() {
        int pilotNameMaxLength = results.stream()
               .map(LapResult::getPilot)
               .mapToInt(String::length)
               .max()
               .orElseThrow(() -> new IllegalArgumentException("Error counting max pilot name length"));

        int carTitleMaxLength = results.stream()
                .map(LapResult::getCar)
                .mapToInt(String::length)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("Error counting max car title length"));

        int lapTimeMaxLength = results.stream()
                .map(LapResult::getLapTime)
                .mapToInt(String::length)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("Error counting max time length"));

        int separatorLength = pilotNameMaxLength + VERTICAL_SEPARATOR.length()
                            + carTitleMaxLength + VERTICAL_SEPARATOR.length()
                            + lapTimeMaxLength;
        
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < results.size(); i++) {
            if(i == Q2_QUALIFIED_PILOTS) {
                output.append(addSeparator(separatorLength));
            }
            output.append(results.get(i).getPilot())
                  .append(addSpaces(results.get(i).getPilot(), pilotNameMaxLength))
                  .append(VERTICAL_SEPARATOR)
                  .append(results.get(i).getCar())
                  .append(addSpaces(results.get(i).getCar(), carTitleMaxLength))
                  .append(VERTICAL_SEPARATOR)
                  .append(results.get(i).getLapTime())
                  .append(NEW_LINE);
        }
        return output.toString();
    }

    private String addSeparator(int separatorLength) {
        StringBuilder separatorLine = new StringBuilder();
        for(int i = 0; i < separatorLength; i++) {
            separatorLine.append(HORIZONTAL_SEPARATOR);
        }
        return separatorLine.append(NEW_LINE).toString();
    }

    private String addSpaces(String actualString, int maxStringLength) {
        StringBuilder spaces = new StringBuilder();
        for(int i = 0; i < maxStringLength - actualString.length(); i++) {
            spaces.append(WHITE_SPACE);
        }
        return spaces.toString();
    }

    public void readData() {
        List<String> start = readFile("start.log");
        List<String> end = readFile("end.log");
        List<String> abbreviations = readFile("abbreviations.txt");

        for(String abbreviationLine : abbreviations) {
            String abbr = parseFromString(abbreviationLine, 0);
            String pilot = parseFromString(abbreviationLine, 1);
            String carTitle = parseFromString(abbreviationLine, 2);

            String startTimeLine = start.stream()
                                        .filter(line -> line.startsWith(abbr))
                                        .findFirst()
                                        .orElseThrow(() -> new IllegalArgumentException("Couldn't find " 
                                                                                        + abbr 
                                                                                        + " start time"));

            String endTimeLine = end.stream()
                                    .filter(line -> line.startsWith(abbr))
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalArgumentException("Couldn't find " 
                                                                                    + abbr 
                                                                                    + " end time"));

            LocalDateTime startLapTime = parseStringToDate(startTimeLine);
            LocalDateTime endLapTime = parseStringToDate(endTimeLine);
            results.add(new LapResult(pilot, carTitle, startLapTime, endLapTime));
        }
    }
}
