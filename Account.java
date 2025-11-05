import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class Account {
    // Simple account model with validation and file persistence
    private static int accountCounter = 2023100000;

    private final Credentials credentials;

    // Account fields (private)
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
        validateName(firstName, "First Name");
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setMiddleName(String middleName) throws IOException {
        validateName(middleName, "Middle Name");
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setLastName(String lastName) throws IOException {
        validateName(lastName, "Last Name");
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
        validateName(fatherName, "Father's Name");
        this.fatherName = fatherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setMotherName(String motherName) throws IOException {
        validateName(motherName, "Mother's Name");
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

    // Get current balance
    public double getBalance() {
        return balance;
    }

    // Save account to file
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
            System.out.println("Account saved to NewAccount.txt!");
        } catch (IOException e) {
            System.out.println(" Error saving account to file: " + e.getMessage());
        }
    }

    // Validation methods
    // Validate 4-digit numeric PIN
    private void validatePin(String pin) throws IllegalArgumentException {
        if (pin == null || pin.isEmpty()) {
            throw new IllegalArgumentException("Pin code cannot be empty.");
        }

        if (pin.length() != 4) {
            throw new IllegalArgumentException("Pin code must be exactly 4 digits.");
        }

        if (!pin.matches("\\d+")) {
            throw new IllegalArgumentException("Pin code must contain only digits (0-9). Letters and special characters are not allowed.");
        }
    }

    // Validate name (letters and spaces only)
    private static void validateName(String input, String fieldName) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException(fieldName + " cannot be blank.");
        }
        if (!input.matches("[A-Za-z ]+")) {
            throw new IOException(fieldName + " cannot contain special characters.");
        }
    }

    // Validate address (allow letters, digits, space, #, comma, dot)
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
                throw new IOException("Address contains invalid character.");
            }
        }
    }

    // Validate contact number (11 digits)
    private void contactValidation(String num) throws IllegalArgumentException {
        if (num == null || num.isEmpty()) {
            throw new IllegalArgumentException("Contact Number cannot be blank.");
        }

        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i))) {
                throw new IllegalArgumentException("Contact Number must contain only digits.");
            }
        }

        if (num.length() != 11) {
            throw new IllegalArgumentException("Contact Number must be exactly 11 digits.");
        }
    }

    // Validate birthdate (YYYY-MM-DD, no future dates)
    private void validateBirthdate(String inputDate) throws IllegalArgumentException {
        if (!inputDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid format. Use YYYY-MM-DD.");
        }

        try {
            LocalDate date = LocalDate.parse(inputDate);
            if (date.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Birthdate cannot be in the future.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date components.");
        }
    }

    // Validate gender input and normalize
    private String genderValidation(String genderInput) throws IOException {
        if (genderInput == null || genderInput.isEmpty()) {
            throw new IOException("Gender cannot be blank.");
        }

        if (genderInput.equalsIgnoreCase("M") || genderInput.equalsIgnoreCase("Male")) {
            return "Male";
        } else if (genderInput.equalsIgnoreCase("F") || genderInput.equalsIgnoreCase("Female")) {
            return "Female";
        } else {
            throw new IOException("Enter 'M' or 'F' (or 'Male'/'Female').");
        }
    }

    // Guide user to register an account
    public void register(Scanner sc) {
        System.out.println("\n=== New Bank Account (Account No: " + accountNo + ") ===");

        // Credentials (validated by Credentials class)
        while (true) {
            try {
                System.out.print("Enter Username: ");
                credentials.setUsername(sc.nextLine());
                break;
            } catch (IOException e) {
                System.out.println("Validation error: " + e.getMessage());
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
    // End credentials

        // Remaining account details (each input is validated)
        while (true) {
            try {
                System.out.print("Enter Pin code (4 digits): ");
                String pinInput = sc.nextLine();
                setPin(pinInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Validation error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter First Name: ");
                String firstName = sc.nextLine();
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
                String middleName = sc.nextLine();
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
                String lastName = sc.nextLine();
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
                String address = sc.nextLine();
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
                String fatherName = sc.nextLine();
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
                String motherName = sc.nextLine();
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
    System.out.println("Registration successful. Account Number: " + accountNo);

        saveToFile();
    }
}