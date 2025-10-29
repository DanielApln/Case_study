import java.util.Scanner;
import java.io.IOException;



public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Account newAccount = new Account();

        // Register user
        //newAccount.register(sc);


        // Menu
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║   PERSONAL NEW BANK ACCOUNT SYSTEM       ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        boolean running = true;

        while (running) {
            try {
                System.out.println("\n========== MAIN MENU ==========");
                System.out.println("1. Register New Account");
                System.out.println("2. Login to Existing Account");
                System.out.println("3. Exit");
                System.out.print("Select option: ");

                int option = input.nextInt();
                input.nextLine();

                switch (option) {
                    case 1:
                        newAccount.register(input);
                        break;
                    case 2:
                        System.out.print("Login");
                        break;
                    case 3:
                        System.out.print("Exit");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid Input!, Please try again");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            } finally {
                if (input != null) {
                    input.close();
                }
            }


        }
    }
}