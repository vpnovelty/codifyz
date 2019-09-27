package com.sing.start;

import com.sing.services.BalanceCalculator;
import com.sing.services.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CodeChallenge testing:")
class CodeChallengeTest {


    @DisplayName("Should accept User input and give a valid result")
    @Test
    void userCalculator() {
        List<Transaction> transactionListWithReturns = new ArrayList<>();
        transactionListWithReturns.add(new Transaction("TX10001", "ACC334455", "ACC778899", "20/10/2018 12:47:55", "25.00", "PAYMENT"));
        transactionListWithReturns.add(new Transaction("TX10002", "ACC334455", "ACC998877", "20/10/2018 17:33:43", "10.50", "PAYMENT"));
        transactionListWithReturns.add(new Transaction("TX10003", "ACC998877", "ACC778899", "20/10/2018 18:00:00", "5.00", "PAYMENT"));
        transactionListWithReturns.add(new Transaction("TX10004", "ACC334455", "ACC998877", "20/10/2018 19:45:00", "10.50", "REVERSAL", "TX10002"));
        transactionListWithReturns.add(new Transaction("TX10004", "ACC334455", "ACC778899", "21/10/2018 09:30:00", "7.25", "PAYMENT"));

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("ACC334455\n20/10/2018 12:00:00\n20/10/2018 19:00:00".getBytes());
        Scanner consoleInput = new Scanner(byteArrayInputStream);
        BalanceCalculator rbc = new BalanceCalculator(transactionListWithReturns);
        assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: -$25.00 \n" +
                "The Number of Transactions included is: 1", CodeChallenge.userCalculator(consoleInput, rbc));
    }

    @DisplayName("Should display a appropriate message when From date is after To date")
    @Test
    void userCalculatorDataInvalid() {
        List<Transaction> transactionListWithReturns = new ArrayList<>();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("ACC778899\n21/10/2018 17:55:00\n20/10/2018 19:35:00".getBytes());
        Scanner consoleInput = new Scanner(byteArrayInputStream);
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionListWithReturns);
        assertEquals("Please enter a valid date, To Date 2018-10-20T19:35 should not be after From Date 2018-10-21T17:55",
                CodeChallenge.userCalculator(consoleInput, balanceCalculator));
    }

}