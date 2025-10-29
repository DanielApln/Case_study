import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Account is now a standalone class (NO "extends Credentials")
class Account {
    private static int accountCounter = 2023100000; // default starting account number

    // 🔑 COMPOSITION: Account HAS-A Credentials object
    private final Credentials credentials;

    protected int accountNo;
    protected String pin;
    protected String firstName;
    protected String middleName;
    protected String lastName;
    protected String birthdate;
    protected String gender;
    protected String address;
    protected String fatherName;
    protected String motherName;
    protected String contactNo;
    protected double balance = 500.0; // fixed initial deposit

    // Constructor
    public Account() {
        this.accountNo = accountCounter++;
        // Initialize the composed object
        this.credentials = new Credentials();
    }

    protected void saveToFile() {
        // We now use the public getter methods from the 'credentials' object
        String usernameToSave = credentials.getUsername();
        String passwordToSave = credentials.getPassword();

        // NOTE: Path must be updated for real-world use outside a specific PC setup
        try (FileWriter writer = new FileWriter("C:\\Users\\acer\\Desktop\\Account\\NewAccount.txt", true)) {
            writer.write("=====================================\n");
            writer.write("Account No: " + accountNo + "\n");
            // Access through the Credentials object's getter
            writer.write("Username: " + usernameToSave + "\n");
            writer.write("Password: " + passwordToSave + "\n");
            writer.write("Pin code: " + pin + "\n");
            writer.write("Full Name: " + firstName + " " + middleName + " " + lastName + "\n");
            writer.write("Birthdate: " + birthdate + "\n");
            writer.write("Gender: " + gender + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Father's Name: " + fatherName + "\n");
            writer.write("Mother's Name: " + motherName + "\n");
            writer.write("Contact No: " + contactNo + "\n");
            writer.write("Balance: ₱" + balance + "\n");
            writer.write("=====================================\n\n");
        } catch (IOException e) {
            System.out.println("Error saving account: " + e.getMessage());
        }
    }

    public void register(Scanner sc) {
        System.out.println("=== Personal New Bank Account Registration ===");

        // --- Credential Setup (Delegation) ---
        while (true) {
            try {
                System.out.print("Enter Username: ");
                // Delegate the responsibility to the Credentials object
                credentials.setUsername(sc.nextLine());
                break;
            } catch (IOException e) {
                System.out.println(" Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Password: ");
                // Delegate the responsibility to the Credentials object
                credentials.setPassword(sc.nextLine());
                break;
            } catch (IOException e) {
                System.out.println(" Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
        // --- End Credential Setup ---


      
      //gagawin ko toh 

        System.out.print("Enter Pin code: ");
        pin = sc.nextLine();

        System.out.print("Enter First Name: ");
        firstName = sc.nextLine();

        System.out.print("Enter Middle Name: ");
        middleName = sc.nextLine();

        System.out.print("Enter Last Name: ");
        lastName = sc.nextLine();

        System.out.print("Enter Birthdate (YYYY-MM-DD): ");
        birthdate = sc.nextLine();

        System.out.print("Enter Gender: ");
        gender = sc.nextLine();

        System.out.print("Enter Address: ");
        address = sc.nextLine();

        System.out.print("Enter Father's Name: ");
        fatherName = sc.nextLine();

        System.out.print("Enter Mother's Name: ");
        motherName = sc.nextLine();

        System.out.print("Enter Contact No: ");
        contactNo = sc.nextLine();

        System.out.println("\nInitial Deposit: ₱" + balance);
        System.out.println("\nRegistration Successful! Your Account Number is: " + accountNo);

        saveToFile();
    }
}
