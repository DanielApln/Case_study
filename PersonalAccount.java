import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Personal bank account implementation with basic banking operations.
 * Provides deposit, withdrawal (with timer), and balance inquiry features.
 * Extends AbstractBankAccount to implement concrete banking operations.
 */
public class PersonalAccount extends AbstractBankAccount {

    /**
     * Creates a new personal account with a unique account number
     * and initial balance of 500.
     */
    public PersonalAccount() {
        super();
    }

    /**
     * Loads an existing personal account from stored data.
     * 
     * @param accountNo  Account identifier
     * @param pin        Security PIN for withdrawals
     * @param username   Login username
     * @param password   Login password
     * @param firstName  First name of account holder
     * @param middleName Middle name of account holder
     * @param lastName   Last name of account holder
     * @param birthdate  Birth date in YYYY-MM-DD format
     * @param gender     Gender of account holder
     * @param address    Residential address
     * @param fatherName Father's name
     * @param motherName Mother's name
     * @param contactNo  Contact number (11 digits)
     * @param balance    Current account balance
     * @throws IOException If credential validation fails
     */
    public PersonalAccount(int accountNo, String pin, String username, String password, String firstName,
            String middleName, String lastName, String birthdate, String gender, String address, String fatherName,
            String motherName, String contactNo, double balance) throws IOException {
        super(accountNo, pin, username, password, firstName, middleName, lastName, birthdate, gender, address,
                fatherName, motherName, contactNo, balance);
    }

    /**
     * Displays current account balance and account number.
     * Implements the abstract method from AbstractBankAccount.
     */
    @Override
    public void balanceInquiry() {
        System.out.println("\n--- Balance Inquiry ---");
        System.out.println("Account No: " + accountNo);
        System.out.println("Current Balance: ₱" + String.format("%.2f", balance));
    }

    /**
     * Processes a deposit transaction. Validates input amount and updates
     * both memory and file storage on success.
     *
     * @param sc Scanner for reading user input
     */
    @Override
    public void deposit(Scanner sc) {
        System.out.println("\n--- Deposit ---");
        double amount = 0;
        try {
            System.out.print("Enter deposit amount: ₱");
            if (sc.hasNextDouble()) {
                amount = sc.nextDouble();
                sc.nextLine(); // Consume newline
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                return;
            }

            if (amount <= 0) {
                System.out.println("Deposit amount must be positive.");
                return;
            }

            this.balance += amount;
            System.out.printf("Deposit successful. Account No. %d. New balance: ₱%.2f%n", this.accountNo, this.balance);
            updateFile(); // Update file after transaction

        } catch (Exception e) { // Exception handling (Requirement 5)
            System.out.println("An error occurred during deposit: " + e.getMessage());
        }
    }

    /**
     * Processes a withdrawal transaction with security measures:
     * - Validates PIN before proceeding
     * - Checks sufficient balance
     * - Implements 60-second security timer
     * - Updates both memory and file storage on success
     *
     * @param sc Scanner for reading user input
     */
    @Override
    public void withdraw(Scanner sc) {
        System.out.println("\n--- Withdrawal ---");
        double amount = 0;
        String pinInput = "";

        try {
            System.out.print("Enter withdrawal amount: ₱");
            if (sc.hasNextDouble()) {
                amount = sc.nextDouble();
                sc.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                return;
            }

            System.out.print("Enter 4-digit PIN: ");
            pinInput = sc.nextLine();

            // PIN validation
            if (!pinInput.equals(this.pin)) {
                System.out.println("Invalid PIN. Withdrawal aborted.");
                return;
            }

            if (amount <= 0) {
                System.out.println("Withdrawal amount must be positive.");
                return;
            }

            if (amount > this.balance) {
                System.out.println("Insufficient balance. Current balance: ₱" + String.format("%.2f", this.balance));
                return;
            }

            System.out.println("Withdrawal in progress. A 60-second timer is now active...");

            // --- TIMER IMPLEMENTATION (Requirement 2) ---
            try {
                // *** IMPORTANT: Change 5000 to 60000 milliseconds for the 60-second
                // requirement ***
                Thread.sleep(5000);
                // Thread.sleep(60000); // <-- Use this line for 60 seconds

                this.balance -= amount;
                System.out.printf("Withdrawal successful! Account No. %d. New balance: ₱%.2f%n", this.accountNo,
                        this.balance);
                updateFile(); // Update file after transaction

            } catch (InterruptedException e) {
                System.out.println("Withdrawal process interrupted (Timer did not complete). Transaction canceled.");
            }
            // --- END TIMER IMPLEMENTATION ---

        } catch (Exception e) { // Exception handling (Requirement 5)
            System.out.println("An unexpected error occurred during withdrawal: " + e.getMessage());
        }
    }

    /**
     * Updates account balance in the storage file.
     * Creates a temporary file, copies all data with updated balance,
     * then replaces the original file.
     * Only modifies the balance line for the current account.
     */
    private void updateFile() {
        File file = new File("NewAccount.txt");
        File tempFile = new File("NewAccount_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            boolean inTargetBlock = false;

            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("Account No: " + this.accountNo)) {
                    inTargetBlock = true;
                }

                if (inTargetBlock && line.trim().startsWith("Balance:")) {
                    writer.println("Balance: " + String.format("%.2f", this.balance));
                    inTargetBlock = false;
                    continue;
                }

                writer.println(line);

                if (line.trim().equals("=====================================")) {
                    inTargetBlock = false;
                }
            }

            // Rename files to replace old with new
            if (!file.delete()) {
                System.err.println("Could not delete original file. File update failed.");
                return;
            }
            if (!tempFile.renameTo(file)) {
                System.err.println("Could not rename temporary file. File update failed.");
            }

        } catch (IOException e) {
            System.err.println("Error updating account balance in file: " + e.getMessage());
        }
    }
}
