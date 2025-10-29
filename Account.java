import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class Account {
    // Static counter for the Account Number sequence
    private static int accountCounter = 2023100000;

    private final Credentials credentials;

    // Better Encapsulation: Make fields private and use public getters/setters if
    // needed
    private int accountNo;
    private String pin;
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private String birthdate;
    private String gender;
    private String address = "";
    private String fatherName = "";
    private String motherName = "";
    private String contactNo;

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setPin(String pin) {
        validatePin(pin);
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setFirstName(String firstName) throws IOException {
        firstNameValidation(firstName);
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setMiddleName(String middleName) throws IOException {
        middleNameValidation(middleName);
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setLastName(String lastName) throws IOException {
        lastNameValidation(lastName);
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthdate(String birthdate) {
        validateBirthdate(birthdate);
        this.birthdate = birthdate;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setGender(String gender) throws IOException {
        String genderValidated = genderValidation(gender);
        this.gender = genderValidated;
    }

    public String getGender() {
        return gender;
    }

    public void setAddress(String address) throws IOException {
        addressValidation(address);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setFatherName(String fatherName) throws IOException {
        fatherNameValidation(fatherName);
        this.fatherName = fatherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setMotherName(String motherName) throws IOException {
        motherNameValidation(motherName);
        this.motherName = motherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setContactNo(String contactNo) {
        contactValidation(contactNo);
        this.contactNo = contactNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    private double balance = 500.0; // fixed initial deposit (constant value 500)

    // Constructor
    public Account() {
        this.accountNo = accountCounter++;
        // Initialize the composed object
        this.credentials = new Credentials();
    }

    // Getter for Balance for Balance Inquiry feature
    public double getBalance() {
        return balance;
    }

    // Feature: Save Account Data
    private void saveToFile() {
        // Use relative path for portability. It will save in the program's working
        // directory.
        try (FileWriter writer = new FileWriter("NewAccount.txt", true)) {
            writer.write("=====================================\n");
            writer.write("Account No: " + accountNo + "\n");
            // Access through the Credentials object's getter (respecting encapsulation)
            writer.write("Username: " + credentials.getUsername() + "\n");
            writer.write("Password: " + credentials.getPassword() + "\n");
            writer.write("Pin code: " + pin + "\n");
            writer.write("Full Name: " + firstName + " " + middleName + " " + lastName + "\n");
            writer.write("Birthdate: " + birthdate + "\n");
            writer.write("Gender: " + gender + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Father's Name: " + fatherName + "\n");
            writer.write("Mother's Name: " + motherName + "\n");
            writer.write("Contact No: " + contactNo + "\n");
            writer.write("Balance: " + String.format("%.2f", balance) + "\n");
            writer.write("=====================================\n\n");
            System.out.println(" Account successfully saved to NewAccount.txt!");
        } catch (IOException e) {
            System.out.println(" Error saving account to file: " + e.getMessage());
        }
    }

    // validation Start
    // Pin code validation method
    private void validatePin(String pin) throws IllegalArgumentException {
        if (pin == null || pin.isEmpty()) {
            throw new IllegalArgumentException("Pin code cannot be empty.");
        }

        if (!pin.matches("\\d{4}")) {
            throw new IllegalArgumentException("Pin code must contain only digits (0-9).");
        }

        if (pin.length() != 4) {
            throw new IllegalArgumentException("Pin code must be exactly 4 digits.");
        }
    }

    private static void firstNameValidation(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("First Name cannot be blank.");
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetter(ch)) {
                continue;
            } else if (ch == ' ') {
                continue;
            } else {
                throw new IOException("First Name cannot contain any special character.");
            }
        }
    }

    private static void middleNameValidation(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Middle Name cannot be blank.");
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetter(ch)) {
                continue;
            } else if (ch == ' ') {
                continue;
            } else {
                throw new IOException("Middle Name cannot contain any special character.");
            }
        }
    }

    private static void lastNameValidation(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Last Name cannot be blank.");
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetter(ch)) {
                continue;
            } else if (ch == ' ') {
                continue;
            } else {
                throw new IOException("Last Name cannot contain any special character.");
            }
        }
    }

    private static void fatherNameValidation(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Father's Name cannot be blank.");
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetter(ch)) {
                continue;
            } else if (ch == ' ') {
                continue;
            } else {
                throw new IOException("Father's Name cannot contain any special character.");
            }
        }
    }

    private static void motherNameValidation(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Mother's Name cannot be blank.");
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetter(ch)) {
                continue;
            } else if (ch == ' ') {
                continue;
            } else {
                throw new IOException("Mother's Name cannot contain any special character.");
            }
        }
    }

    private static void addressValidation(String input) throws IOException {
        if (input == null || input.trim().isEmpty()) {
            throw new IOException("Address cannot be blank.");
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                continue;
            } else if (ch == ' ' || ch == '#' || ch == ',' || ch == '.') {
                continue;
            } else {
                throw new IOException("Address cannot contain some special character.");
            }
        }
    }

    private void contactValidation(String num) throws IllegalArgumentException {
        if (num == null || num.isEmpty()) {
            throw new IllegalArgumentException("Contact Number cannot be blank.");
        }

        // Check if all characters are digits
        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i))) {
                throw new IllegalArgumentException("Contact Number must only contain numbers.");
            }
        }

        // Check if the length is exactly 11 digits
        if (num.length() != 11) {
            throw new IllegalArgumentException("Contact Number must be exactly 11 digits.");
        }
    }

    private void validateBirthdate(String inputDate) throws IllegalArgumentException {
        // 1. Basic Format Check
        if (!inputDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid format! Please use YYYY-MM-DD.");
        }

        // 2. Full Date Logic Check (Handles leap years, month boundaries, etc.)
        try {
            LocalDate date = LocalDate.parse(inputDate);

            // 3. Future Date Check
            if (date.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Birthdate cannot be in the future.");
            }
        } catch (DateTimeParseException e) {
            // Catches errors like '2023-02-30' or '2023-13-01'
            throw new IllegalArgumentException("Invalid date components (e.g., invalid day/month for the given year).");
        }
    }

    private String genderValidation(String genderInput) throws IOException {
        if (genderInput == null || genderInput.isBlank()) {
            throw new IOException("Gender cannot be blank.");
        }

        if (genderInput.equalsIgnoreCase("M") || genderInput.equalsIgnoreCase("Male")) {
            return "Male";
        } else if (genderInput.equalsIgnoreCase("F") || genderInput.equalsIgnoreCase("Female")) {
            return "Female";
        } else {
            throw new IOException("Invalid gender input. Please enter only 'M', 'F', 'Male', or 'Female'.");
        }
    }

    // Feature: Register New Account
    public void register(Scanner sc) {
        System.out.println("\n=== Personal New Bank Account Registration (Account No: " + accountNo + ") ===");

        // --- Credential Setup (Delegation) ---
        while (true) {
            try {
                System.out.print("Enter Username: ");
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
                credentials.setPassword(sc.nextLine());
                break;
            } catch (IOException e) {
                System.out.println(" Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
        // --- End Credential Setup ---

        // Remaining Account Details
        while (true) {
            try {
                System.out.print("Enter Pin code (4 digits): ");
                String pinInput = sc.nextLine();
                setPin(pinInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(" Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter First Name: ");
                firstName = sc.nextLine();
                setFirstName(firstName);
                break;
            } catch (IOException e) {
                System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Middle Name: ");
                middleName = sc.nextLine();
                setMiddleName(middleName);
                break;
            } catch (IOException e) {
                System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Last Name: ");
                lastName = sc.nextLine();
                setLastName(lastName);
                break;
            } catch (IOException e) {
                System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        while (true) {
            System.out.print("Enter Birthdate (YYYY-MM-DD): ");
            String inputDate = sc.nextLine();
            try {
                // Call the separate validation method
                setBirthdate(inputDate);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(" Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Gender (Male/Female): ");
                String genderInput = sc.nextLine();
                setGender(genderInput);
                break;
            } catch (IOException e) {
                System.out.println(" Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Address: ");
                address = sc.nextLine();
                setAddress(address);
                break;
            } catch (IOException e) {
                System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Father's Name: ");
                fatherName = sc.nextLine();
                setFatherName(fatherName);
                break;
            } catch (IOException e) {
                System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Mother's Name: ");
                motherName = sc.nextLine();
                setMotherName(motherName);
                break;
            } catch (IOException e) {
                System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Contact No (11 digits): ");
                String contactNo = sc.nextLine();
                setContactNo(contactNo);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(" Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        System.out.println("\nInitial Deposit: ₱" + String.format("%.2f", balance));
        System.out.println("🎉 Registration Successful! Your Account Number is: " + accountNo);

        saveToFile();
    }
}