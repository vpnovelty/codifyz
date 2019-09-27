package com.sing.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * Validators, validation is done here.
 */
public class ValidatorUtils {

    /**
     * Validates if the given file type is valid
     *
     * @param filePath
     * @param mimeType
     * @param CSV
     * @return Boolean
     * @throws IOException
     */
    public Boolean isValidFile(final String filePath, final String mimeType, final String CSV) throws IOException {
        return filePath.endsWith(CSV) && mimeType.equals(Files.probeContentType(Paths.get(filePath)));
    }

    /**
     * Check if from date is before to date
     *
     * @param toDate
     * @param fromDate
     * @return Boolean
     */
    public Boolean isValidDateSupplied(final LocalDateTime toDate, final LocalDateTime fromDate) {
        return fromDate.isBefore(toDate);
    }
}



