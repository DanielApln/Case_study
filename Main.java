import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

    // Header
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║   PERSONAL NEW BANK ACCOUNT SYSTEM        ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        boolean running = true;

        try { // Main menu loop
            while (running) {
                System.out.println("\n========== MAIN MENU ==========");
                System.out.println("1. Register New Account");
                System.out.println("2. Login to Existing Account");
                System.out.println("3. Exit");
                System.out.print("Select option: ");

                try { // Read user choice
                    if (input.hasNextInt()) {
                        int option = input.nextInt();
                        input.nextLine(); // Consume newline

                        switch (option) {
                            case 1:
                                Account newAccount = new Account();
                                newAccount.register(input);
                                break;
                            case 2:
                                login(input); // Implemented Login
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
                        input.nextLine(); // Consume the invalid input
                    }
                } catch (Exception e) {
                    System.err.println("\nUnexpected error: " + e.getMessage());
                }
            }
        } finally {
            // Cleanup scanner
            if (input != null) {
                input.close();
                System.out.println("Scanner closed.");
            }
        }
    }
    
    /**
     * Check credentials against saved accounts in NewAccount.txt.
     * Prints account number and balance on success.
     *
     * @param sc Scanner for reading user input
     */
    private static void login(Scanner sc) {
        System.out.println("\n--- Login ---");
        System.out.print("Enter Username: ");
        String username = sc.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = sc.nextLine().trim();

        File file = new File("NewAccount.txt");
        if (!file.exists()) {
            System.out.println("No accounts found. Please register first.");
            return;
        }

        boolean found = false;
        String currentAccNo = null;
        String currentUsername = null;
        String currentPassword = null;
        String currentBalance = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("Account No:")) {
                    currentAccNo = line.substring("Account No:".length()).trim();
                } else if (line.startsWith("Username:")) {
                    currentUsername = line.substring("Username:".length()).trim();
                } else if (line.startsWith("Password:")) {
                    currentPassword = line.substring("Password:".length()).trim();
                } else if (line.startsWith("Balance:")) {
                    currentBalance = line.substring("Balance:".length()).trim();
                } else if (line.startsWith("=====================================")) {
                    // end of a saved account block -> check credentials
                    if (currentUsername != null && currentPassword != null &&
                        currentUsername.equals(username) && currentPassword.equals(password)) {
                        System.out.println("\nLogin successful. Welcome, " + (currentUsername.isEmpty() ? "User" : currentUsername) + "!");
                        System.out.println("Account No: " + (currentAccNo != null ? currentAccNo : "N/A"));
                        System.out.println("Balance: " + (currentBalance != null ? currentBalance : "N/A"));
                        found = true;
                        break;
                    }
                    // reset temp vars for next block
                    currentAccNo = null;
                    currentUsername = null;
                    currentPassword = null;
                    currentBalance = null;
                }
            }

            // In case the file doesn't end with the separator, check last block
            if (!found && currentUsername != null && currentPassword != null &&
                    currentUsername.equals(username) && currentPassword.equals(password)) {
                System.out.println("\nLogin successful. Welcome, " + (currentUsername.isEmpty() ? "User" : currentUsername) + "!");
                System.out.println("Account No: " + (currentAccNo != null ? currentAccNo : "N/A"));
                System.out.println("Balance: " + (currentBalance != null ? currentBalance : "N/A"));
                found = true;
            }

            if (!found) {
                System.out.println("\nLogin failed. Username or password is incorrect.");
            }

        } catch (IOException e) {
            System.out.println("Error reading accounts file: " + e.getMessage());
        }
    }
}