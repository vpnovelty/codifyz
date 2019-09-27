package com.sing.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Date utility
 */
public class DateUtils {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Converts the date to dd/MM/yyyy HH:mm:ss format
     *
     * @param input
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeFromString(final String input) {
        return LocalDateTime.parse(input, DATE_TIME_FORMATTER);
    }

}
