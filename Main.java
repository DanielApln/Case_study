import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

/**
 * Personal Bank Account System
 * Provides a console interface for:
 * - New account registration
 * - Secure login
 * - Banking operations (deposit, withdraw, balance)
 */
public class Main {

    /**
     * Entry point of the banking application.
     * Displays main menu and handles user interaction.
     *
     * @param args Command line arguments (not used)
     * @throws IOException If file operations fail
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        // Display welcome banner
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║   PERSONAL NEW BANK ACCOUNT SYSTEM        ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        boolean running = true;

        try {
            while (running) {
                System.out.println("\n========== MAIN MENU ==========");
                System.out.println("1. Register New Account");
                System.out.println("2. Login to Existing Account");
                System.out.println("3. Exit");
                System.out.print("Select option: ");

                try { // Read and handle menu choice (Exception handling Requirement 5)
                    if (input.hasNextInt()) {
                        int option = input.nextInt();
                        input.nextLine();

                        switch (option) {
                            case 1:
                                // Uses the subclass PersonalAccount
                                AbstractBankAccount newAccount = new PersonalAccount();
                                newAccount.register(input);
                                break;
                            case 2:
                                // Login returns AbstractBankAccount (Polymorphic return type)
                                AbstractBankAccount loggedInAccount = login(input);
                                if (loggedInAccount != null) {
                                    bankOperationsMenu(input, loggedInAccount);
                                }
                                break;
                            case 3:
                                System.out.println("Thank you for using the Bank Account System. Goodbye!");
                                running = false;
                                break;
                            default:
                                System.out.println("\n Invalid Input! Please select 1, 2, or 3.");
                        }
                    } else {
                        System.out.println("\n Invalid Input! Please enter a number.");
                        input.nextLine();
                    }
                } catch (Exception e) {
                    System.err.println("\nAn unexpected error occurred in Main Menu: " + e.getMessage());
                }
            }
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    /**
     * Displays and handles the account operations menu.
     * Provides access to:
     * - Deposit funds
     * - Withdraw funds (with 60s timer)
     * - Check balance
     * - Secure logout
     *
     * Uses polymorphic operations through AbstractBankAccount.
     * 
     * @param sc Scanner for reading user input
     * @param account The logged-in bank account
     */
    private static void bankOperationsMenu(Scanner sc, AbstractBankAccount account) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n========== ACCOUNT MENU (Account: " + account.getAccountNo() + ") ==========");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw (60s Timer)");
            System.out.println("3. Balance Inquiry");
            System.out.println("4. Logout");
            System.out.print("Select option: ");

            try {
                if (sc.hasNextInt()) {
                    int option = sc.nextInt();
                    sc.nextLine();

                    switch (option) {
                        case 1:
                            account.deposit(sc); // Polymorphic method call
                            break;
                        case 2:
                            account.withdraw(sc); // Polymorphic method call
                            break;
                        case 3:
                            account.balanceInquiry(); // Polymorphic method call
                            break;
                        case 4:
                            System.out.println("\nSuccessfully logged out from Account No: " + account.getAccountNo());
                            loggedIn = false;
                            break;
                        default:
                            System.out.println("\n Invalid Input! Please select 1, 2, 3, or 4.");
                    }
                } else {
                    System.out.println("\n Invalid Input! Please enter a number.");
                    sc.nextLine();
                }
            } catch (Exception e) {
                System.err.println("\nAn unexpected error occurred in Account Menu: " + e.getMessage());
            }
        }
    }

    /**
     * Authenticates user and loads their account data.
     * Searches credentials in NewAccount.txt and reconstructs
     * the account object with all saved information.
     *
     * @param sc Scanner for reading login credentials
     * @return Loaded bank account if login successful, null otherwise
     */
    private static AbstractBankAccount login(Scanner sc) {
        System.out.println("\n--- Login ---");
        System.out.print("Enter Username: ");
        String username = sc.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = sc.nextLine().trim();

        File file = new File("NewAccount.txt");
        if (!file.exists()) {
            System.out.println("No accounts found. Please register first.");
            return null;
        }

        // Variables to store ALL fields
        String currentPin = null;
        String currentUsername = null;
        String currentPassword = null;
        String currentAccNoStr = null;
        String currentBalanceStr = null;
        String firstName = null;
        String middleName = null;
        String lastName = null;
        String birthdate = null;
        String gender = null;
        String address = null;
        String fatherName = null;
        String motherName = null;
        String contactNo = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                // Read all fields (your original data structure)
                if (line.startsWith("Account No:")) {
                    currentAccNoStr = line.substring("Account No:".length()).trim();
                } else if (line.startsWith("Username:")) {
                    currentUsername = line.substring("Username:".length()).trim();
                } else if (line.startsWith("Password:")) {
                    currentPassword = line.substring("Password:".length()).trim();
                } else if (line.startsWith("Pin code:")) {
                    currentPin = line.substring("Pin code:".length()).trim();
                } else if (line.startsWith("Full Name:")) {
                    String fullName = line.substring("Full Name:".length()).trim();
                    String[] parts = fullName.split(" ");
                    if (parts.length >= 3) {
                        firstName = parts[0];
                        middleName = parts[1];
                        lastName = parts[2];
                    }
                } else if (line.startsWith("Birthdate:")) {
                    birthdate = line.substring("Birthdate:".length()).trim();
                } else if (line.startsWith("Gender:")) {
                    gender = line.substring("Gender:".length()).trim();
                } else if (line.startsWith("Address:")) {
                    address = line.substring("Address:".length()).trim();
                } else if (line.startsWith("Father's Name:")) {
                    fatherName = line.substring("Father's Name:".length()).trim();
                } else if (line.startsWith("Mother's Name:")) {
                    motherName = line.substring("Mother's Name:".length()).trim();
                } else if (line.startsWith("Contact No:")) {
                    contactNo = line.substring("Contact No:".length()).trim();
                } else if (line.startsWith("Balance:")) {
                    currentBalanceStr = line.substring("Balance:".length()).trim();
                } else if (line.startsWith("=====================================")) {
                    // Check credentials at the end of the block
                    if (currentUsername != null && currentPassword != null &&
                            currentUsername.equals(username) && currentPassword.equals(password)) {

                        try {
                            int accNo = Integer.parseInt(currentAccNoStr);
                            double balance = Double.parseDouble(currentBalanceStr.replace("₱", "").trim());

                            // Return a new PersonalAccount object (as the AbstractBankAccount type)
                            AbstractBankAccount loggedInAccount = new PersonalAccount(accNo, currentPin,
                                    currentUsername, currentPassword, firstName, middleName, lastName, birthdate,
                                    gender, address, fatherName, motherName, contactNo, balance);
                            System.out.println("\nLogin successful. Welcome, " + firstName + "!");
                            return loggedInAccount;

                        } catch (NumberFormatException e) {
                            System.out.println("Error: Account data in file is corrupted (Account No/Balance).");
                            return null;
                        }
                    }
                    // Reset variables for the next block
                    currentAccNoStr = null;
                    currentPin = null;
                    currentUsername = null;
                    currentPassword = null;
                    currentBalanceStr = null;
                    firstName = null;
                    middleName = null;
                    lastName = null;
                    birthdate = null;
                    gender = null;
                    address = null;
                    fatherName = null;
                    motherName = null;
                    contactNo = null;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: The account file was not found (NewAccount.txt).");
        } catch (IOException e) {
            System.out.println("Error reading accounts file: " + e.getMessage());
        }

        System.out.println("\nLogin failed. Username or password is incorrect.");
        return null;
    }
}