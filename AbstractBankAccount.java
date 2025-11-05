import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Base class for all bank account types providing core banking functionality.
 * Implements common features like registration and data validation.
 * Subclasses must implement deposit, withdrawal, and balance inquiry.
 * 
 * Features:
 * - Unique account number generation
 * - Secure credential management
 * - Personal information validation
 * - File-based persistence
 */
public abstract class AbstractBankAccount {

    /** Starting account number for sequential generation */
    protected static int accountCounter = 2023100000;

    /** Security credentials for login */
    protected final Credentials credentials;

    /** Unique account identifier */
    protected int accountNo;

    /** 4-digit security PIN for transactions */
    protected String pin;
    protected String firstName = "";
    protected String middleName = "";
    protected String lastName = "";
    protected String birthdate;
    protected String gender;
    protected String address = "";
    protected String fatherName = "";
    protected String motherName = "";
    protected String contactNo;
    protected double balance;

    // --- CONSTRUCTORS ---

    // Constructor 1: For NEW account registration (Initial Deposit is 500)
    public AbstractBankAccount() {
        this.accountNo = accountCounter++;
        this.balance = 500.0; // Initial Deposit: 500
        this.credentials = new Credentials();
    }

    // Constructor 2: For LOADING an existing account from file (Used after Login)
    public AbstractBankAccount(int accountNo, String pin, String username, String password, String firstName,
            String middleName, String lastName, String birthdate, String gender, String address, String fatherName,
            String motherName, String contactNo, double balance) throws IOException {
        this.accountNo = accountNo;
        this.pin = pin;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.contactNo = contactNo;
        this.balance = balance;

        // Initialize the composed object (Credentials) with loaded data
        this.credentials = new Credentials();
        this.credentials.setUsernameForLoad(username); // Using a helper method to bypass validation on load
        this.credentials.setPasswordForLoad(password);
    }

    /**
     * Process a deposit transaction.
     * 
     * @param sc Scanner for reading amount input
     */
    public abstract void deposit(Scanner sc);

    /**
     * Process a withdrawal with security checks.
     * 
     * @param sc Scanner for reading amount and PIN
     */
    public abstract void withdraw(Scanner sc);

    /**
     * Display current account balance.
     */
    public abstract void balanceInquiry();

    // Concrete methods that all account types inherit

    /**
     * Guides user through complete account registration process.
     * Collects and validates:
     * - Login credentials (username/password)
     * - Security PIN
     * - Personal information
     * - Contact details
     * 
     * Sets initial balance to 500 and saves to file on success.
     * 
     * @param sc Scanner for reading user input
     */
    public void register(Scanner sc) {
        System.out.println("\n========== NEW ACCOUNT REGISTRATION ==========");

        try {
            // Get username and password with validation
            this.credentials.inputCredentials(sc);

            // Personal Details input (using your existing validation loops)
            while (true) {
                try {
                    System.out.print("Enter First Name: ");
                    setFirstName(sc.nextLine());
                    break;
                } catch (IOException e) {
                    System.out.println("Validation Error: " + e.getMessage());
                }
            }
            while (true) {
                try {
                    System.out.print("Enter Middle Name: ");
                    setMiddleName(sc.nextLine());
                    break;
                } catch (IOException e) {
                    System.out.println("Validation Error: " + e.getMessage());
                }
            }
            while (true) {
                try {
                    System.out.print("Enter Last Name: ");
                    setLastName(sc.nextLine());
                    break;
                } catch (IOException e) {
                    System.out.println("Validation Error: " + e.getMessage());
                }
            }
            while (true) {
                try {
                    System.out.print("Enter 4-digit Pin code: ");
                    setPin(sc.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Validation Error: " + e.getMessage());
                }
            }
            while (true) {
                try {
                    System.out.print("Enter Birthdate (YYYY-MM-DD): ");
                    setBirthdate(sc.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Validation Error: " + e.getMessage());
                }
            }
            while (true) {
                try {
                    System.out.print("Enter Gender (Male/Female): ");
                    setGender(sc.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Validation Error: " + e.getMessage());
                }
            }
            while (true) {
                try {
                    System.out.print("Enter Address: ");
                    setAddress(sc.nextLine());
                    break;
                } catch (IOException e) {
                    System.out.println("Validation Error: " + e.getMessage());
                }
            }
            while (true) {
                try {
                    System.out.print("Enter Father's Name: ");
                    setFatherName(sc.nextLine());
                    break;
                } catch (IOException e) {
                    System.out.println("Validation Error: " + e.getMessage());
                }
            }
            while (true) {
                try {
                    System.out.print("Enter Mother's Name: ");
                    setMotherName(sc.nextLine());
                    break;
                } catch (IOException e) {
                    System.out.println("Validation Error: " + e.getMessage());
                }
            }
            while (true) {
                try {
                    System.out.print("Enter Contact No (11 digits): ");
                    setContactNo(sc.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Validation Error: " + e.getMessage());
                }
            }

            // Finalizing registration
            saveToFile();
            System.out.println("\nInitial Deposit: ₱" + String.format("%.2f", balance));
            System.out.println("Registration successful. Account Number: " + accountNo);

        } catch (Exception e) { // Exception handling (Requirement 5)
            System.out.println("\nREGISTRATION FAILED: " + e.getMessage());
        }
    }

    /**
     * Saves account details to persistent storage.
     * Appends all account information to NewAccount.txt in a structured format.
     * Called automatically after successful registration.
     * 
     * @throws IOException If file writing fails
     */
    protected void saveToFile() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter("NewAccount.txt", true))) {
            pw.println("Account No: " + accountNo);
            pw.println("Username: " + credentials.getUsername());
            pw.println("Password: " + credentials.getPassword());
            pw.println("Pin code: " + pin);
            pw.println("Full Name: " + firstName + " " + middleName + " " + lastName);
            pw.println("Birthdate: " + birthdate);
            pw.println("Gender: " + gender);
            pw.println("Address: " + address);
            pw.println("Father's Name: " + fatherName);
            pw.println("Mother's Name: " + motherName);
            pw.println("Contact No: " + contactNo);
            pw.println("Balance: " + String.format("%.2f", balance));
            pw.println("=====================================");
        }
    }

    // --- GETTERS, SETTERS, and VALIDATION (Omitting original validation code for
    // brevity, but they should be in this class) ---

    // Getters for Main.java login to access data
    public int getAccountNo() {
        return accountNo;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername() {
        return credentials.getUsername();
    }

    public String getPassword() {
        return credentials.getPassword();
    }

    // Setters with validation (place your original validation code in private
    // methods)
    public void setPin(String pin) throws IllegalArgumentException {
        validatePin(pin);
        this.pin = pin;
    }

    public void setFirstName(String firstName) throws IOException {
        validateName(firstName, "First Name");
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) throws IOException {
        validateName(middleName, "Middle Name");
        this.middleName = middleName;
    }

    public void setLastName(String lastName) throws IOException {
        validateName(lastName, "Last Name");
        this.lastName = lastName;
    }

    public void setBirthdate(String birthdate) throws IllegalArgumentException {
        validateBirthdate(birthdate);
        this.birthdate = birthdate;
    }

    public void setGender(String gender) throws IllegalArgumentException {
        validateGender(gender);
        this.gender = gender;
    }

    public void setAddress(String address) throws IOException {
        validateAddress(address);
        this.address = address;
    }

    public void setFatherName(String fatherName) throws IOException {
        validateName(fatherName, "Father's Name");
        this.fatherName = fatherName;
    }

    public void setMotherName(String motherName) throws IOException {
        validateName(motherName, "Mother's Name");
        this.motherName = motherName;
    }

    public void setContactNo(String contactNo) throws IllegalArgumentException {
        validateContactNo(contactNo);
        this.contactNo = contactNo;
    }

    // Place all your original private validation methods here:

    private void validatePin(String input) throws IllegalArgumentException {
        if (!input.matches("\\d{4}")) {
            throw new IllegalArgumentException("Pin code must be exactly 4 digits.");
        }
    }

    private void validateName(String input, String fieldName) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException(fieldName + " cannot be blank.");
        }
    }

    private void validateBirthdate(String input) throws IllegalArgumentException {
        try {
            LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use YYYY-MM-DD.");
        }
    }

    private void validateGender(String input) throws IllegalArgumentException {
        if (!input.equalsIgnoreCase("Male") && !input.equalsIgnoreCase("Female")) {
            throw new IllegalArgumentException("Gender must be Male or Female.");
        }
    }

    private void validateAddress(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Address cannot be blank.");
        }
    }

    private void validateContactNo(String input) throws IllegalArgumentException {
        if (!input.matches("\\d{11}")) {
            throw new IllegalArgumentException("Contact No must be exactly 11 digits.");
        }
    }
}