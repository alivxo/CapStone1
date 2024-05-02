package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class cliApp {
    static final LocalDateTime today = LocalDateTime.now();
    static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    static final String formattedDate = today.format(dateFormat);
    static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    static final String formattedTime = today.format(timeFormat);
    static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        homeScreen();

    }

    public static void homeScreen() {
        // boolean statement to keep running application if true,
        // if not application will stop when false
        boolean runningApp = true;

        while (runningApp) {
            String homeScreen = """ 
                    Welcome to LishaTech Financials
                    A. Add Deposit
                    P. Make a Payment (Debit)
                    L. Report Status
                    X. Exit Application
                    """;
            // printing out home screen
            System.out.println(homeScreen);
            // allowing user to pick an option
            System.out.print("Please choose from the following: ");
            String choice = scan.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    addDeposit(scan);
                    break;
                case "P":
                    makeAPayment(scan);
                    break;
                case "L":
                    showingLedger();
                    break;
                case "X":
                    runningApp = false;
                default:
                    System.out.println("Invalid choice. Please try again");
            }

        }
    }

    private static void addDeposit(Scanner scan) {
        // Add Deposit-prompt user for the debit information
        // and save it to the CSV file


        // using try-catch statement to handle exceptions that my occur when executing code
        try {
            /* creating new file */
            FileWriter fileWriter = new FileWriter("transaction.csv", true);

            // Writes text to a character-output stream, buffering characters so as
            // to provide for the efficient writing of single characters, arrays, and strings
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Learned from internet -  PrintWriter simplifies the process of writing formatted text to a file,
            // enhancing readability and efficiency.
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            boolean runningApp = true;

            while(runningApp){

            System.out.println("Please Enter Deposit Information");
            System.out.println("Date: " + formattedDate);
            System.out.println("Time: " + formattedTime);
            System.out.print("Description: ");
            String description = cliApp.scan.nextLine();
            System.out.print("Vendor: ");
            String vendor = cliApp.scan.nextLine();
            float amount = 0;
            try {
                System.out.print("Amount: $ ");
                amount = Float.parseFloat(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount entered. Please try again");
            }

            printWriter.write(formattedDate + " | " + formattedTime + " " + " | " +
                    description + " | " + vendor + " | $ " + amount);
            bufferedWriter.newLine();

            // asking user if they would like to make another deposit
                System.out.println("Would you like to make another Deposit? (Y/N)");
                String anotherDeposit = scan.nextLine();
                if (!anotherDeposit.equalsIgnoreCase("Y")) {
                    runningApp = false;
                }
            }

            printWriter.close();
            System.out.println("Transaction added successfully");
            homeScreen(); // returning to homescreen after transaction was completed

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error adding deposit" + e.getMessage());

        }

    }

    private static void makeAPayment(Scanner scan) {
        // Make Payment(Debit)- prompt user for the debt information and save it to the csv file
        try {
            FileWriter fileWriter = new FileWriter("transaction.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            boolean runningApp = true;
            while (runningApp) {

                System.out.println("Make a Payment (Debit)");
                System.out.println("Date: " + formattedDate);
                System.out.println("Time: " + formattedTime);
                System.out.print("Description: ");
                String description = scan.nextLine();
                System.out.print("Vendor: ");
                String vendor = scan.nextLine();
                float amount = 0;
                try {
                    System.out.print("Amount: $ ");
                    amount = Float.parseFloat(scan.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount entered. Please try again");
                }


                printWriter.write(formattedDate + " | " + formattedTime + " " + " | " +
                        description + " | " + vendor + " | $ " + amount);
                bufferedWriter.newLine();

                System.out.println("Would you like to make another Payment? (Y/N)");
                String anotherPayment = scan.nextLine();
                if (!anotherPayment.equalsIgnoreCase("Y")) {

                    runningApp = false;
                }
            }

            System.out.println("Payment added successfully");
            homeScreen();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static List<String> displayLedger() {
        // this method is going to return an array list of all transactions
        // Ledger - display the ledger Screen

        List<String> transaction = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("transaction.csv");
            // we are using FileReader to locate file/ object
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // using BufferedReader to read char
            String line;
            // declaring a variable

            while ((line = bufferedReader.readLine()) != null) {
                //using while loop to read each line of the file
                // continues to loop until end of file

                transaction.add(line);
                // add line to the transaction list

            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading transaction.csv" + e.getMessage());
        }

        return transaction;
    }

    private static void showingLedger() {
        // this method is allowing us to see the ledger of transactions that were made
        List<String> transaction = displayLedger();
        // an array of transactions
        System.out.println("Welcome to LishaTech Billing Report Application");
        // this is just displaying the screen
        String ledgerMenu = """
                A. All Entries
                D. AlL Deposits
                P. Payments Made
                R. Reports
                H. Home Screen
                """;
        while (true) {
            // user input
            System.out.println("Please choose from the following: ");
            System.out.print(ledgerMenu);
            String choice = scan.nextLine();

            // output choice
            switch (choice) {
                case "A":
                    for (String transactions : transaction) {
                        try {
                            // this for loop will go over each transaction in the transaction list
                            // it is also declaring variable name and type

                            System.out.println(transactions);
                            // printing out transactions
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid amount entered. Please try again" + e.getMessage());
                        }
                    }
                    break;
                case "P":
                    System.out.println("Payments Made: ");
                    for (String transactions : transaction) {
                        try {
                            // this for loop will go over each transaction in the transaction list
                            // it is also declaring variable name and type
                            if (isDeposit(transactions)) {
                                System.out.println(transactions);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid amount entered. Please try again" + e.getMessage());
                        }
                    }
                    break;
                case "D":
                    System.out.println("Deposits Made: ");
                    for (String transactions : transaction) {
                        try {
                            if (isPayments(transactions)) {
                                System.out.println(transactions);

                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid amount entered. Please try again" + e.getMessage());
                        }
                    }
                    break;
                case "R":
                    reportPage();
                case "H":
                    homeScreen();
                default:
                    System.out.println("Invalid choice. Please try again");

            }

            System.out.println("Would you like to exit? (Y/N)");
            String answer = scan.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                break;
            }

        }
    }

    private static void reportPage() {
        boolean keepReport = true;

        while (keepReport) {

            System.out.println("Report Menu");
            System.out.println("Please choose from the following: ");
            String reportMenu = """
                    1. Month to Date
                    2. Previous Month
                    3. Year to Date
                    4. Previous Year
                    5. Search Vendor
                    """;
            System.out.println(reportMenu);
            System.out.print("Please choose from the following: ");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    // Month to Date Report
                    monthToDateReport();
                    break;
                case "2":
                    //Previous month report
                    previousMonthReport();
                    break;
                case "3":
                    // Year to Date Report
                    yearToDateReport();
                    break;
                case "4":
                    //Previous Year Report
                    previousYearReport();
                    break;
                case "5":
                    searchByVendor();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");


            }
            System.out.println("Would you like to exit LisTech Billing Report Application? (Y/N)");
            String answer = scan.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                keepReport = false;

            }
        }
    }


    private static void searchByVendor() {
        System.out.println("Welcome to Search Vendor");
        // we are asking for user input
        System.out.print("Search Vendor: ");
        String vendorName = scan.nextLine();
        // using Ledger class to receive all transactions that were made
        List<String> transaction = displayLedger();
        //Looping through all transactions
        boolean found = false;
        for (String transactions : transaction) {
            // checking tos ee if the transaction contains vendors name
            if (transactions.contains(vendorName)) {
                System.out.println(transactions);
                // using if to print the transaction if statement is true
                found = true;
            }
        }
        if (!found) {
            // if the transaction is not found then below will print
            System.out.println("Vendor not found: " + vendorName);
        }
    }


    private static void previousYearReport() {
        // Calculating the start and end date of previous year
        LocalDate startPreviousYear = LocalDate.now().minusYears(1).withDayOfYear(1);
        LocalDate endPreviousYear = LocalDate.now().minusYears(1).withDayOfYear(365);
        // outputting transactions made within the previous years
        showAllEntriesBetweenDates(startPreviousYear, endPreviousYear);
    }

    private static void yearToDateReport() {
        //Calculating the beginning of the current year
        LocalDate beginDate = LocalDate.now().minusYears(1).withDayOfYear(1);
        // Displaying transactions of that current year
        showAllEntriesAfterDate(beginDate);
    }

    private static void previousMonthReport() {
        // Calculating start and end of the previous month
        LocalDate startPreviousMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate endPreviousMonth = LocalDate.now().minusMonths(1).withDayOfMonth(startPreviousMonth.lengthOfMonth());
        // Displaying those transactions as well
        showAllEntriesBetweenDates(startPreviousMonth, endPreviousMonth);
    }

    private static void monthToDateReport() {
        // Calculating current month
        LocalDate startMonth = LocalDate.now().withDayOfMonth(1);
        // Displaying current month
        showAllEntriesAfterDate(startMonth);
        scan.nextLine();
    }

    private static void showAllEntriesBetweenDates(LocalDate startDate, LocalDate endDate) {
        // getting all transactions from ledger class
        List<String> transaction = displayLedger();

        // Outputting the transactions between the dates
        System.out.println("Transaction Dates Between " + startDate + " and " + endDate + ":");

        boolean found = false;
        for (String transactions : transaction) {
            // turning location into a string
            String [] parts = transactions.split("\\| ");
            LocalDate transactionDate = LocalDate.parse(parts[0].trim(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));

            // checking id statement is true between start date and end date
            if(!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                System.out.println(transactions);
                // Printing all transactions that are true
                found = true;

            }
        }
        // if statement is false then the message below will print below
        if (!found) {
            System.out.println("Transaction not found between " + startDate + " and " + endDate);
        }

    }

    private static void showAllEntriesAfterDate(LocalDate startDate) {
        // getting all transactions from ledger class
        List<String> transaction = displayLedger();

        // Printing transactions between the start and end dates
        System.out.println("Transaction After " + startDate + ":");
        boolean found = false;
        for (String transactions : transaction) {
            String [] parts = transactions.split("\\|");
            LocalDate transactionDate = LocalDate.parse(parts[0].trim(),DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            //// Checking to see if the transaction date is between the start and end dates
            if(!transactionDate.isBefore(startDate)) {
                //printing transaction if true
                System.out.println(transactions);
                found = true;
            }
        }
        // if statement is false then the message below will print below
        if(!found){
            System.out.println("Transaction not found after: " + startDate );
        }
    }

    private static boolean isPayments(String transactions) {

        // Splitting the transaction string by "|" and extracting the amount substring
        String amount = transactions.split("\\|")[4];

        // this is removing all white spaces
        amount = amount.replaceAll("[\\$\\s]", "");

        // Parsing amount as float
        float Stringamount = Float.parseFloat(amount);

        return Stringamount > 0;
        // we are returning zero because the transaction would be negative
    }

    private static boolean isDeposit(String transactions) {
        // Splitting the transaction string by "|" and extracting the amount substring
        String amount = transactions.split("\\|")[4];

        // this is removing all white spaces
        amount = amount.replaceAll("[\\$\\s]", "");

        // Parsing amount as float
        float Stringamount = Float.parseFloat(amount);

        // returning value if less than zero
        return Stringamount < 0;
        // we are returning anything greater than zero because depositing is an increment
    }

}





