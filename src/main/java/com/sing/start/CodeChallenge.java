package com.sing.start;

import com.sing.services.BalanceCalculator;
import com.sing.utils.CommonUtils;
import com.sing.utils.DateUtils;
import com.sing.utils.ValidatorUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Start of the process
 */
public class CodeChallenge {

    public static String MIME_TYPE = "text/csv";
    public static String CSV = ".csv";

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {

        // args[0] - input file path supplied
        System.out.println(startExecution(args[0]));

    }

    /**
     * Start of execution
     *
     * @param filePath
     * @return result
     */
    static String startExecution(String filePath) {
        try {
            if (getValidatorUtils().isValidFile(filePath, MIME_TYPE, CSV)) {

                BalanceCalculator balanceCalculator = new BalanceCalculator(new CommonUtils().getTransactionList(filePath));

                return userCalculator(scanner, balanceCalculator);

            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        return "Invalid data supplied";
    }


    /**
     * Gets the user input, process the data and returns the result
     *
     * @param scanner
     * @param balanceCalculator
     * @return display result
     */
    static String userCalculator(final Scanner scanner, final BalanceCalculator balanceCalculator) {

        //Account Number request from customer
        System.out.println("Please enter the Account ID to calculate balance: ");
        System.out.println("Example: ACC334455");
        final String accountID = scanner.nextLine();

        //From date request from customer
        System.out.println(String.format("please enter the From Date to calculate the balance for the AccountID - %s: ", accountID));
        System.out.println("Example: 20/10/2018 12:00:00");
        LocalDateTime fromDate = DateUtils.getLocalDateTimeFromString(scanner.nextLine());

        //To date request from customer
        System.out.println(String.format("please enter the To Date to calculate the balance From Date - %s for the AccountID - %s: ", fromDate, accountID));
        System.out.println("Example: 20/10/2018 19:00:00");
        LocalDateTime toDate = DateUtils.getLocalDateTimeFromString(scanner.nextLine());

        return getValidatorUtils().isValidDateSupplied(toDate, fromDate) ?
                balanceCalculator.calculateRelativeBalance(accountID, fromDate, toDate) :
                String.format("Please enter a valid date, To Date %s should not be after From Date %s", toDate, fromDate);

    }

    private static ValidatorUtils getValidatorUtils() {
        return new ValidatorUtils();
    }


}
