import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Account {
    // Static counter for the Account Number sequence
    private static int accountCounter = 2023100000; 

    // 🔑 COMPOSITION: Account HAS-A Credentials object (Excellent OOP design!)
    private final Credentials credentials;

    // Better Encapsulation: Make fields private and use public getters/setters if needed
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

    //  Feature: Save Account Data
    private void saveToFile() {
        // Use relative path for portability. It will save in the program's working directory.
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
            writer.write("Balance: ₱" + String.format("%.2f", balance) + "\n");
            writer.write("=====================================\n\n");
            System.out.println(" Account successfully saved to NewAccount.txt!");
        } catch (IOException e) {
            System.out.println(" Error saving account to file: " + e.getMessage());
        }
    }

    //validation Start
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

        public static void firstNameValidation (String input) throws IOException{
			if (input == null || input.isEmpty()) {
				throw new IOException("First Name cannot be blank.");
			}
			
			for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        
		        if (Character.isLetter(ch)) {
		        	continue;
		        }
		        else if(ch == ' ') {
		        	continue;
		        }
		        else {
		        	throw new IOException("First Name cannot contain any special character."); 
		        }
		    }
    	}
		
		public static void middleNameValidation (String input) throws IOException{
			if(input == null || input.isEmpty()) {
				throw new IOException("Middle Name cannot be blank.");
			}
			
			for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        
		        if (Character.isLetter(ch)) {
		        	continue;
		        }
		        else if(ch == ' ') {
		        	continue;
		        }
		        else {
		        	throw new IOException("Middle Name cannot contain any special character."); 
		        }
		    }
		}
		
		public static void lastNameValidation (String input) throws IOException{
			if(input == null || input.isEmpty()) {
				throw new IOException("Last Name cannot be blank.");
			}
			
			for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        
		        if (Character.isLetter(ch)) {
		        	continue;
		        }
		        else if(ch == ' ') {
		        	continue;
		        }
		        else {
		        	throw new IOException("Last Name cannot contain any special character."); 
		        }
		    }
		}
		
		public static void fatherNameValidation (String input) throws IOException{
			if(input == null || input.isEmpty()) {
				throw new IOException("Father's Name cannot be blank.");
			}
			
			for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        
		        if (Character.isLetter(ch)) {
		        	continue;
		        }
		        else if(ch == ' ') {
		        	continue;
		        }
		        else {
		        	throw new IOException("Father's Name cannot contain any special character."); 
		        }
		    }
		}
		
		public static void motherNameValidation (String input) throws IOException {
			if(input == null || input.isEmpty()) {
				throw new IOException("Mother's Name cannot be blank.");
			}
			
			for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        
		        if (Character.isLetter(ch)) {
		        	continue;
		        }
		        else if(ch == ' ') {
		        	continue;
		        }
		        else {
		        	throw new IOException("Mother's Name cannot contain any special character."); 
		        }
		    }		
		}
		
		public static void addressValidation (String input) throws IOException {
			if(input == null || input.trim().isEmpty()) {
				throw new IOException("Address cannot be blank.");
			}
			
			for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        
		        if (Character.isLetterOrDigit(ch)) {
		        	continue;
		        }
		        else if(ch == ' ' || ch == '#' || ch == ',' || ch == '.') {
		        	continue;
		        }
		        else {
		        	throw new IOException("Address cannot contain some special character."); 
		        }
		    }
		}

    private void validateContact(String num) throws IllegalArgumentException {
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

    public void validateGender(String genderInput) throws IOException {
        if (genderInput == null || genderInput.isBlank()) {
            throw new IOException("Gender cannot be blank.");
        }

        if (genderInput.equalsIgnoreCase("M") || genderInput.equalsIgnoreCase("Male")) {
            this.gender = "Male";
        } 
        else if (genderInput.equalsIgnoreCase("F") || genderInput.equalsIgnoreCase("Female")) {
            this.gender = "Female";
        } 
        else {
            throw new IOException("Invalid gender input. Please enter only 'M', 'F', 'Male', or 'Female'.");
        }
    }

    // 🖋️ Feature: Register New Account
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
                validatePin(pinInput);
                pin = pinInput;
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
				
				firstNameValidation(firstName);
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
				
				middleNameValidation(middleName);
				break;
			} catch (IOException e)
			{
				System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
			}
		}

		while (true) {
			try {
				System.out.print("Enter Last Name: ");
				lastName = sc.nextLine();
				
				lastNameValidation(lastName);
				break;
			} catch (IOException e)
			{
				System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
			}
		}

        System.out.print("Enter Birthdate (YYYY-MM-DD): ");
        birthdate = sc.nextLine();

        while (true) {
            try {
                System.out.print("Enter Gender: ");
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
				System.out.print("Enter Address: ");
				address = sc.nextLine();
				
			    addressValidation(address);
				break;
			} catch (IOException e)
			{
				System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
			}
		}

		while (true) {
			try {
				System.out.print("Enter Father's Name: ");
				fatherName = sc.nextLine();
				
				fatherNameValidation(fatherName);
				break;
			} catch (IOException e)
			{
				System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
			}
		}

		while (true) {
			try {
				System.out.print("Enter Mother's Name: ");
				motherName = sc.nextLine();
				
				motherNameValidation(motherName);
				break;
			} catch (IOException e)
			{
				System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
			}
		}

      while (true) {
            System.out.print("Enter Contact No (11 digits): ");
            String contactNo = sc.nextLine();
            try {
                validateContact(contactNo); 
                this.contactNo = contactNo; 
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