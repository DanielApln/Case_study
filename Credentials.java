import java.io.IOException;

public class Credentials {
    // Simple credentials holder with validation
    private String username;
    private String password;

    public Credentials() {
        this.username = "";
        this.password = "";
    }

    // Setters/getters with validation
    public void setUsername(String username) throws IOException {
        validateUsername(username);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) throws IOException {
        validatePassword(password);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    // Validation (private)
    private void validateUsername(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Username cannot be blank.");
        }

        if (!input.matches("^[A-Za-z0-9_]{3,15}$")) {
            throw new IOException("Username must be 3-15 chars and use letters, numbers, or underscore.");
        }
    }

    private void validatePassword(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Password cannot be blank.");
        }

        if (!input.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,20}$")) {
            throw new IOException("Password must be 8-20 chars and include upper, lower, digit, and special char.");
        }
    }
}