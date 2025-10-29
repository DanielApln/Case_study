import java.util.Scanner;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        

        // Menu
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║   PERSONAL NEW BANK ACCOUNT SYSTEM        ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        boolean running = true;

        try { // Outer try-catch to manage the entire application run
            while (running) {
                System.out.println("\n========== MAIN MENU ==========");
                System.out.println("1. Register New Account");
                System.out.println("2. Login to Existing Account");
                System.out.println("3. Exit");
                System.out.print("Select option: ");

                try { // Inner try-catch for input validation
                    if (input.hasNextInt()) {
                        int option = input.nextInt();
                        input.nextLine(); // Consume newline

                        switch (option) {
                            case 1:
                                Account newAccount = new Account();
                                newAccount.register(input);
                                break;
                            case 2:
                                // To be implemented: Login and access account features
                                System.out.println("\n--- Login Feature (TO BE IMPLEMENTED) ---");
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
                    System.err.println("\nAn unexpected error occurred: " + e.getMessage());
                }
            }
        } finally {
            // CRITICAL FIX: Close the scanner ONLY once the program is completely finished.
            if (input != null) {
                input.close();
                System.out.println("Scanner closed.");
            }
        }
    }
}