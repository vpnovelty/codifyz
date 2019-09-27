package com.sing.utils;

import com.sing.services.Transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * Common utility methods
 */
public class CommonUtils {

    /**
     * Converts cents value to dollars
     *
     * @param centValue
     * @return display value
     */
    public static String ForDisplayConvertCentsValueIntoDollar(final long centValue) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return numberFormat.format(centValue / 100.0);
    }

    /**
     * Makes the transaction List
     *
     * @param filePath
     * @return list of transactions
     * @throws IOException
     */
    public static List<Transaction> getTransactionList(final String filePath) throws IOException {
        Stream<String> transactions = getStreamFile(filePath);
        List<Transaction> transactionList = new ArrayList<>();
        transactions.forEach(transaction -> transactionList.add(createTransactionRecordFromInput(transaction)));
        return transactionList;
    }

    /**
     * Reads the file to stream
     *
     * @param filePath
     * @return input data as stream
     * @throws IOException
     */
    private static Stream<String> getStreamFile(final String filePath) throws IOException {
        return Files.lines(Paths.get(filePath));
    }

    /**
     * Converts line value to transaction record
     *
     * @param inputLine
     * @return Transaction
     */
    static Transaction createTransactionRecordFromInput(final String inputLine) {
        return new Transaction(inputLine.split(","));
    }

    /**
     * converts String Currency ToLong InCents
     *
     * @param inputNumber
     * @return
     */
    public static long convertStringCurrencyToLongInCents(final String inputNumber) {
        //Assumption considering only two decimals
        //BigDecimal will be a better option if more precision is needed.
        double interimVal = Double.parseDouble(inputNumber);
        interimVal *= 100;
        return (long) interimVal;
    }
}
