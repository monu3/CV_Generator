package com.cvgenerator.cvg.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Slf4j
@Component
public class LocalDateUtils {
//    public LocalDate convertStringToDate(String date) {
//        return LocalDate.parse(date);
//    }

    public  LocalDate convertStringToDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString, e);
        }
    }
}
