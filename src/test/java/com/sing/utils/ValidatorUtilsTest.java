package com.sing.utils;

import com.sing.start.CodeChallenge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ValidatorUtils Testing:")
class ValidatorUtilsTest {

    @DisplayName("Should return true When valid file extension is supplied")
    @Test
    void validFile() throws IOException {

        ValidatorUtils validatorUtils = new ValidatorUtils();
        assertTrue(validatorUtils.isValidFile("input.csv", CodeChallenge.MIME_TYPE, CodeChallenge.CSV));
    }

    @DisplayName("Should return false When invalid file extension is supplied")
    @Test
    void invalidFile() throws IOException {

        ValidatorUtils validatorUtils = new ValidatorUtils();
        assertFalse(validatorUtils.isValidFile("input.ABCD", CodeChallenge.MIME_TYPE, CodeChallenge.CSV));
    }

}