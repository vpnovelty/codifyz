package com.sing.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.sing.utils.DateUtils.getLocalDateTimeFromString;

@DisplayName("BalanceCalculator: Tests that verify the calculator logic")
public class BalanceCalculatorTest {
    private static List<Transaction> transactionList = new ArrayList<>();
    private static List<Transaction> transactionListWithReversal = new ArrayList<>();

    @BeforeAll
    static void setupTransactionList(){
        transactionList.add(new Transaction("TX10001", "ACC282828", "ACC181818", "20/10/2019 18:00:00", "1.50", "PAYMENT"));
        transactionList.add(new Transaction("TX10002", "ACC282828", "ACC181818", "20/10/2019 18:15:00", "2.75", "PAYMENT"));
        transactionList.add(new Transaction("TX10003", "ACC181818", "ACC282828", "20/10/2019 19:45:00", "6.00", "PAYMENT"));
        transactionList.add(new Transaction("TX10004", "ACC181818", "ACC282828", "20/10/2019 20:45:00", "10.00", "PAYMENT"));

        transactionListWithReversal.add(new Transaction("TX10001", "ACC282828", "ACC181818", "20/10/2019 18:00:00", "16.50", "PAYMENT"));
        transactionListWithReversal.add(new Transaction("TX10002", "ACC282828", "ACC181818", "20/10/2019 18:30:00", "24.75", "PAYMENT"));
        transactionListWithReversal.add(new Transaction("TX10003", "ACC181818", "ACC282828", "20/10/2019 19:00:00", "12.25", "PAYMENT"));
        transactionListWithReversal.add(new Transaction("TX10004", "ACC282828", "ACC181818", "20/10/2019 19:30:00", "24.75", "REVERSAL", "TX10002"));
        transactionListWithReversal.add(new Transaction("TX10005", "ACC2424242", "ACC020202", "20/10/2018 19:30:00", "24.75", "PAYMENT"));
        transactionListWithReversal.add(new Transaction("TX10006", "ACC2424242", "ACC020202", "20/10/2018 19:35:00", "24.75", "PAYMENT"));
        transactionListWithReversal.add(new Transaction("TX10007", "ACC2424242", "ACC020202", "20/10/2018 19:45:00", "24.75", "REVERSAL", "TX10006"));
    }

    @DisplayName("Calculator should be negative the Debit Calculations")
    @Test
    void testCalculatorForDebits(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionList);
        String value = balanceCalculator.calculateRelativeBalance("ACC181818", getLocalDateTimeFromString("20/10/2019 20:35:00"), getLocalDateTimeFromString("20/10/2019 20:55:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: -$10.00 \n" +
                "The Number of Transactions included is: 1", value);
    }

    @DisplayName("Calculator should be Negative on calculate mix of Credit & Debit Calculations")
    @Test
    void testCalculatorForCreditAndDebits(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionList);
        String value = balanceCalculator.calculateRelativeBalance("ACC181818", getLocalDateTimeFromString("20/10/2018 18:25:00"), getLocalDateTimeFromString("20/10/2019 19:55:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: -$1.75 \n" +
                "The Number of Transactions included is: 3", value);
    }

    @DisplayName("Calculator should be Negative on calculate mix of Credit & Debit Calculations")
    @Test
    void testCalculatorForCreditAndDebit(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionList);
        String value = balanceCalculator.calculateRelativeBalance("ACC181818", getLocalDateTimeFromString("20/10/2019 18:10:00"), getLocalDateTimeFromString("20/10/2019 19:50:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: -$3.25 \n" +
                "The Number of Transactions included is: 2", value);
    }

    @DisplayName("Calculator should be positive the Credit Calculations")
    @Test
    void testCalculatorForCredits(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionList);
        String value = balanceCalculator.calculateRelativeBalance("ACC282828", getLocalDateTimeFromString("20/10/2019 20:35:00"), getLocalDateTimeFromString("20/10/2019 20:55:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: $10.00 \n" +
                "The Number of Transactions included is: 1", value);
    }

    @DisplayName("Calculator should be positive on calculate mix of Debit & Credit Calculations")
    @Test
    void testCalculatorForDebitsAndCredits(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionList);
        String value = balanceCalculator.calculateRelativeBalance("ACC282828", getLocalDateTimeFromString("20/10/2018 18:25:00"), getLocalDateTimeFromString("20/10/2019 19:55:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: $1.75 \n" +
                "The Number of Transactions included is: 3", value);
    }

    @DisplayName("Calculator should be Positive on calculate mix of Credit & Debit Calculations")
    @Test
    void testCalculatorForDebitAndCredit(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionList);
        String value = balanceCalculator.calculateRelativeBalance("ACC282828", getLocalDateTimeFromString("20/10/2019 18:10:00"), getLocalDateTimeFromString("20/10/2019 19:50:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: $3.25 \n" +
                "The Number of Transactions included is: 2", value);
    }

    @DisplayName("Calculator should be positive on the Credit Calculations with Reversals")
    @Test
    void testCalculatorForCreditsWithReversals(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionListWithReversal);
        String value = balanceCalculator.calculateRelativeBalance("ACC181818", getLocalDateTimeFromString("20/10/2019 17:50:00"), getLocalDateTimeFromString("20/10/2019 18:35:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: $16.50 \n" +
                "The Number of Transactions included is: 1", value);
    }

    @DisplayName("Calculator should be positive on calculate mix of Debit & Credit Calculations with Reversals")
    @Test
    void testCalculatorForDebitsAndCreditsWithReverSals(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionListWithReversal);
        String value = balanceCalculator.calculateRelativeBalance("ACC282828", getLocalDateTimeFromString("20/10/2019 18:25:00"), getLocalDateTimeFromString("20/10/2019 19:35:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: $12.25 \n" +
                "The Number of Transactions included is: 1", value);
    }

    @DisplayName("Calculator should be positive on calculate all transactions with Reversals in Credit")
    @Test
    void testCalculatorForAllWithReversalsCredit(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionListWithReversal);
        String value = balanceCalculator.calculateRelativeBalance("ACC181818", getLocalDateTimeFromString("20/10/2019 17:55:00"), getLocalDateTimeFromString("20/10/2019 19:35:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: $4.25 \n" +
                "The Number of Transactions included is: 2", value);
    }

    @DisplayName("Calculator should be positive on calculate all transactions with Reversals in debit")
    @Test
    void testCalculatorForAllWithReversalsDebits(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionListWithReversal);
        String value = balanceCalculator.calculateRelativeBalance("ACC020202", getLocalDateTimeFromString("20/10/2018 19:25:00"), getLocalDateTimeFromString("20/10/2018 19:55:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: $24.75 \n" +
                "The Number of Transactions included is: 1", value);
    }

    @DisplayName("Calculator should be negative on the Debit Calculations With Reversals")
    @Test
    void testCalculatorForDebitsWithReversals(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionListWithReversal);
        String value = balanceCalculator.calculateRelativeBalance("ACC282828", getLocalDateTimeFromString("20/10/2019 17:50:00"), getLocalDateTimeFromString("20/10/2019 18:35:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: -$16.50 \n" +
                "The Number of Transactions included is: 1", value);
    }

    @DisplayName("Calculator should be negative on calculate mix of Credit & Debit Calculations with Reversals")
    @Test
    void testCalculatorForCreditAndDebitsWithReversals(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionListWithReversal);
        String value = balanceCalculator.calculateRelativeBalance("ACC181818", getLocalDateTimeFromString("20/10/2019 18:25:00"), getLocalDateTimeFromString("20/10/2019 19:35:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: -$12.25 \n" +
                "The Number of Transactions included is: 1", value);
    }

    @DisplayName("Calculator should be negative on calculate all transactions with Reversals in Debit")
    @Test
    void testCalculatorForAllWithReversalsDebit(){
        BalanceCalculator balanceCalculator = new BalanceCalculator(transactionListWithReversal);
        String value = balanceCalculator.calculateRelativeBalance("ACC282828", getLocalDateTimeFromString("20/10/2019 17:55:00"), getLocalDateTimeFromString("20/10/2019 19:35:00"));
        Assertions.assertEquals("\n" +
                "Here is the requested details:\n" +
                "Relative balance for the period is: -$4.25 \n" +
                "The Number of Transactions included is: 2", value);
    }

}
