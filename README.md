CapStone 1

This application is used to track all financial transcations for a business or for personal use.

All the transactions that are saved to a file called transacation.csv, with the following format below : 
date|time|description|vendor|amount
2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00

The Application has the following: 
Home Screen: 
-- D. Add Deposit -> prompting user for deposit information
-- P. Make Payment -> prompt user for debit information 
-- L. Ledger -> Displaying the ledger screen
      A. All - Displays all enteries 
      D. Deposits -. Display only the entries that are deposits into the account
      P. Payment - Display only the negative entries (or payments)
      R. Report -> New screen that allows the user to run reports 
            1. Month to Date
            2. Previous Month 
            3. Year to Date 
            4. Previous Year
            5. Search by Vendor - prompting user for the vendor name and display all entries for that vendor
            X. go back to the report page 
      H. Home - go back to the home page       
-- X. Exit -> Exit the application 

___________________________________________________________________________________________________________________________________________________________________
---- Entry Class ----

![entryclass](https://github.com/alivxo/CapStone1/assets/121145501/1843a1e3-9bf6-4aea-a5c9-6e1552c65ea5)

- The Entry class represents individual entries in a financial or transactional system.
- It contains private attributes such as date, time, description, vendor, and amount.
- The constructor method Entry() initializes these attributes with values provided during object creation.
- Getter methods (getDate(), getTime(), getDescription(), getVendor(), getAmount()) allow external access to these attributes, providing read-only access to the class's data.

---- Ledger Class ----

 ![ledgerClass](https://github.com/alivxo/CapStone1/assets/121145501/271c9d61-90be-441b-9a5e-ac87d8c11cdd)

- It maintains an ArrayList named entries, which holds instances of the Entry class.
- The constructor initializes the entries list.
- The addEntry() method adds a new Entry object to the ledger.
- The displayAllEntries() method iterates over all entries in the ledger and prints each entry's description and amount to the console.


---- Main Class ----

-- Main and Home Screen--
![main homeScreen](https://github.com/alivxo/CapStone1/assets/121145501/339f1e45-7384-4e0e-b992-ea7315dedc59)

- The main () is the ebtry point ot the application which is calling the homeScreen();
- The homeScreen(); methoud presents options for the user to decide which of the following they would like to choose from
- The while loop will continously display the homeScreen until the user chooses to exit the application by typing in X
- The Switch cases contains methods that user chooses from depending on what they decide
___________________________________________________________________________________________________________________________________________________________________
-- addDeposit() -- 
![addDepositMethod](https://github.com/alivxo/CapStone1/assets/121145501/c05111bc-ac61-44e1-a9b9-996456a4ad05)

- FileWriter intializes to create a CSV file named "transaction.csv"
- BuffereedWrite and PrintWriter make it easier for the user to read the file
- In the while looop - it prompts the user to input the deposit information
- After each entry the user will be asked it if they would like to input another deposit or go back into the main screen
- Once the loop ends PrintWriter will indicate the additional transaction was scucessful
- If any error occurs the print stack trace message will print
- Lastly, the homeScreen(), method will continue if user prompts
___________________________________________________________________________________________________________________________________________________________________
-- makeaPayment() -- 

![makeaPaymentmethod](https://github.com/alivxo/CapStone1/assets/121145501/dce77890-31b8-421d-b132-b930dc3417ed)

- Intializes FileWriter to append data into the CSV file named transactions.csv
- In the while looop - it prompts the user to input information
- It handles potential exceptions, such as invalid input for the amount
- After each entry, it asks the user if they want to make another payment, continuing the loop if the user agrees.
- Once the loop ends, it prints a success message indicating the payment addition.
- Lastly, the homeScreen(), method will continue if user prompts
___________________________________________________________________________________________________________________________________________________________________
-- displayledger() --

![displayledger](https://github.com/alivxo/CapStone1/assets/121145501/edcb9f0d-4fee-4c69-8c42-1001744493cb)

- Initializes an ArrayList named transactoin to store transaction data read from the CSV file
- Uses try catch block to read data from the transaction file
- FileReader and BufferedReader to read the file line by line
- The while loop reads each line of the file until the end of the file has been reached
- BufferedReader.close(); will be exeuted once the file is read
- Print stack trace will read any errors
- Finally, it returns the transaction list containing all the transactions read from the file
___________________________________________________________________________________________________________________________________________________________________
-- showingledger -- 

![showingledger](https://github.com/alivxo/CapStone1/assets/121145501/3b7afa46-7feb-4def-bd3a-42a6f97a662a)

- showingledger() methos is calling the displayLedger(); method to retrieved transactiondtata fron the CSV file
- there is a menu that is displayed for the user to choose.
- In the while looop - it prompts the user to input information, until the user chooses to exit
- It uses a switch statement to handle user choices.
- Print stack trace will read any errors
___________________________________________________________________________________________________________________________________________________________________
-- reportPage() -- 

![reportPagemethod](https://github.com/alivxo/CapStone1/assets/121145501/dfb4f1b5-e499-4f0a-912e-423c71608962)

- Its intializing a boolean variable "keepReport" to control the loop to display the report menu
- In the while loop, it continuously prompts the user for input until they choose to exit.
- It displays a menu for selecting various types of reports, such as Month to Date, Previous Month, Year to Date, Previous Year, and Search by Vendor.
- After displaying the chosen report, it asks the user if they want to exit the report page.

___________________________________________________________________________________________________________________________________________________________________

-- showAllEntriesBetweenDates() && showAllEntriesAfterDate() --

![showAllEntriesBetweenDates()   showAllEntriesAfterDate()](https://github.com/alivxo/CapStone1/assets/121145501/a1cd4016-3402-4a6e-9087-7ef52af3e77d)

- showAllEntriesBetweenDates(startDate, endDate):

- It takes two LocalDate parameters: startDate and endDate, representing the range for filtering transactions.
- It retrieves all transactions from the ledger using the displayLedger() method.
- It iterates through each transaction.
- For each transaction, it parses the transaction date from the string and compares it with the provided startDate and endDate.
- If the transaction date falls within the specified range, it prints the transaction.
- If no transactions are found within the specified range, it prints a message indicating that no transactions were found.


- showAllEntriesAfterDate(startDate):

- It takes a single LocalDate parameter startDate, representing the starting date for filtering transactions.
- It retrieves all transactions from the ledger using the displayLedger() method.
- It iterates through each transaction.
- For each transaction, it parses the transaction date from the string and compares it with the provided startDate.
- If the transaction date is after the specified startDate, it prints the transaction.
- If no transactions are found after the specified startDate, it prints a message indicating that no transactions were found.

___________________________________________________________________________________________________________________________________________________________________

-- isPayments() && isDeposits() --

![isPayments  isDeposits](https://github.com/alivxo/CapStone1/assets/121145501/ad75b4d5-abd7-485f-95b5-0ac22266884b)

- isPayments(String transactions):

- It takes a transaction string as input.
- It extracts the amount substring from the transaction string by splitting it using the "|" delimiter and selecting the fourth element (index 3).
- It removes any whitespace and currency symbols from the extracted amount.
- It parses the extracted amount as a float.
- It returns true if the parsed amount is greater than 0, indicating that it's a payment.
- Otherwise, it returns false.


- isDeposit(String transactions):

- It follows the same process as isPayments() to extract and parse the amount from the transaction string.
- It returns true if the parsed amount is less than 0, indicating that it's a deposit.
- Otherwise, it returns false.

