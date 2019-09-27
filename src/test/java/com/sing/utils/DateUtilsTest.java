package com.sing.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("DateUtils Testing:")
class DateUtilsTest {

    @DisplayName("Should be a valid LocalDateTime object")
    @Test
    void getLocalDateTimeFromString() {
        assertSame(LocalDateTime.class, DateUtils.getLocalDateTimeFromString("23/06/2019 19:45:00").getClass());
    }
}