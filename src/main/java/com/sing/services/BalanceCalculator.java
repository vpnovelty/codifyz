package com.sing.services;

import com.sing.utils.CommonUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Balance Calculator
 */
public class BalanceCalculator {
    private final List<Transaction> transactionList;

    public BalanceCalculator(final List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    /**
     * Calculators the Relative balance
     *
     * @param accountID
     * @param fromDateTime
     * @param toDateTime
     * @return Display data
     */
    public String calculateRelativeBalance(final String accountID, final LocalDateTime fromDateTime, final LocalDateTime toDateTime) {

        long relativeBalance = 0;
        long reversalBalance = 0;
        int transactionNumber = 0;
        for (Transaction transaction : transactionList) {

            if (transaction.getCreatedAt().isAfter(fromDateTime) &&
                    transaction.getCreatedAt().isBefore(toDateTime) &&
                    transaction.getTransactionType().equals(TransactionType.PAYMENT)) {

                if (transaction.getFromAccountID().equalsIgnoreCase(accountID)) {
                    relativeBalance -= transaction.getAmountInCents();
                    transactionNumber++;
                } else if (transaction.getToAccountID().equalsIgnoreCase(accountID)) {
                    relativeBalance += transaction.getAmountInCents();
                    transactionNumber++;
                }

            } else if (transaction.getTransactionType().equals(TransactionType.REVERSAL)) {

                if (transaction.getFromAccountID().equalsIgnoreCase(accountID)) {
                    reversalBalance += transaction.getAmountInCents();
                    transactionNumber--;
                } else if (transaction.getToAccountID().equalsIgnoreCase(accountID)) {
                    reversalBalance -= transaction.getAmountInCents();
                    transactionNumber--;
                }
            }
        }
        relativeBalance += reversalBalance;
        return String.format("\nHere is the requested details:\n" +
                        "Relative balance for the period is: %s \n" +
                        "The Number of Transactions included is: %s",
                CommonUtils.ForDisplayConvertCentsValueIntoDollar(relativeBalance),
                transactionNumber);
    }
}
