import java.io.IOException;

public class Credentials {

    // Change to private for better encapsulation
    private String username;
    private String password;

    // Use a constructor to initialize (optional, but good practice)
    public Credentials() {
        this.username = "";
        this.password = "";
    }

    public void setUsername(String username) throws IOException {
        validateUsername(username);
        this.username = username;
    }

    // Now uses private field
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) throws IOException {
        validatePassword(password);
        this.password = password;
    }

    // Now uses private field
    public String getPassword() {
        return password;
    }

    // Validation methods remain the same
    public String validateUsername(String input) throws IOException {
        if (input == null || input.isBlank()) {
            throw new IOException("Username cannot be blank.");
        }

        // Only allow letters, digits, and underscores (3–15 chars)
        if (!input.matches("^[A-Za-z0-9_]{3,15}$")) {
            throw new IOException("Username must be 3–15 characters and contain only letters, numbers, or underscores.");
        }

        return input;
    }

    public String validatePassword(String input) throws IOException {
        if (input == null || input.isBlank()) {
            throw new IOException("Password cannot be blank.");
        }

        // At least one lowercase, one uppercase, one digit, one special char, and 8–20 chars long
        if (!input.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")) {
            throw new IOException("Password must be 8–20 characters and include uppercase, lowercase, number, and special character.");
        }

        return input;
    }
}
