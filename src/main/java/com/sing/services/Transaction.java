package com.sing.services;

import com.sing.utils.CommonUtils;
import com.sing.utils.DateUtils;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Transaction {

    private String transactionID;
    private String fromAccountID;
    private String toAccountID;
    private LocalDateTime createdAt;
    private long amountInCents;
    private TransactionType transactionType;
    private String relatedPayment;

    public Transaction(final String... transactionData) {
        this.transactionID = transactionData[0].strip();
        this.fromAccountID = transactionData[1].strip();
        this.toAccountID = transactionData[2].strip();
        this.createdAt = DateUtils.getLocalDateTimeFromString(transactionData[3].strip());
        this.amountInCents = CommonUtils.convertStringCurrencyToLongInCents(transactionData[4].strip());
        this.transactionType = TransactionType.valueOf(transactionData[5].strip());
        if (this.transactionType.equals(TransactionType.REVERSAL)) {
            this.relatedPayment = transactionData[6].strip();
        }
    }

}


