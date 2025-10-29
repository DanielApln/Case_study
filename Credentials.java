import java.io.IOException;

public class Credentials {

    //  Encapsulation: fields are private and only accessible via methods
    private String username;
    private String password;

    // Constructor
    public Credentials() {
        // Initialize to safe, non-null values
        this.username = "";
        this.password = "";
    }

    // Setter for username with validation
    public void setUsername(String username) throws IOException {
        // The validation logic handles throwing the exception
        validateUsername(username);
        this.username = username;
    }

    // Getter for username (read-only access to the private field)
    public String getUsername() {
        return username;
    }

    // Setter for password with validation
    public void setPassword(String password) throws IOException {
        // The validation logic handles throwing the exception
        validatePassword(password);
        this.password = password;
    }

    // Getter for password (read-only access to the private field)
    public String getPassword() {
        return password;
    }

    // --- Validation Methods (Can be private as they are only used internally) ---
    
    private void validateUsername(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Username cannot be blank.");
        }

        // Only allow letters, digits, and underscores (3–15 chars)
        if (!input.matches("^[A-Za-z0-9_]{3,15}$")) {
            throw new IOException("Username must be 3–15 characters and contain only letters, numbers, or underscores.");
        }
    }

    private void validatePassword(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Password cannot be blank.");
        }

        // At least one lowercase, one uppercase, one digit, one special char, and 8–20 chars long
        if (!input.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")) {
            throw new IOException("Password must be 8–20 characters and include uppercase, lowercase, number, and special character.");
        }
    }
}