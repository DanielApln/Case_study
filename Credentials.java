import java.io.IOException;
import java.util.Scanner;

/**
 * Manages and validates user credentials for bank accounts.
 * Enforces security rules for usernames and passwords:
 * - Username: 3-15 characters, alphanumeric with underscore
 * - Password: 8+ characters with mixed case, numbers, and special chars
 */
public class Credentials {
    private String username;
    private String password;

    /**
     * Creates a new credentials object with empty username and password.
     */
    public Credentials() {
        this.username = "";
        this.password = "";
    }

    /**
     * Sets username with validation for new accounts.
     * 
     * @param username New username to set
     * @throws IOException If username fails validation rules
     */
    public void setUsername(String username) throws IOException {
        validateUsername(username);
        this.username = username;
    }

    /**
     * Gets the current username.
     * 
     * @return Current username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets password with validation for new accounts.
     * 
     * @param password New password to set
     * @throws IOException If password fails security requirements
     */
    public void setPassword(String password) throws IOException {
        validatePassword(password);
        this.password = password;
    }

    /**
     * Gets the current password.
     * 
     * @return Current password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets username without validation (for loading existing accounts).
     * 
     * @param username Username to set directly
     */
    public void setUsernameForLoad(String username) {
        this.username = username;
    }

    /**
     * Sets password without validation (for loading existing accounts).
     * 
     * @param password Password to set directly
     */
    public void setPasswordForLoad(String password) {
        this.password = password;
    }

    /**
     * Interactive method to collect and validate credentials from user.
     * Repeatedly prompts for input until valid credentials are provided.
     * 
     * @param sc Scanner for reading user input
     * @throws IOException If validation ultimately fails
     */
    public void inputCredentials(Scanner sc) throws IOException {
        while (true) {
            try {
                System.out.print("Enter Username: ");
                setUsername(sc.nextLine());
                break;
            } catch (IOException e) {
                System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Password: ");
                setPassword(sc.nextLine());
                break;
            } catch (IOException e) {
                System.out.println("Validation Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
    }

    /**
     * Validates username against security rules:
     * - Not null or empty
     * - 3-15 characters long
     * - Only letters, numbers, and underscores allowed
     *
     * @param input Username to validate
     * @throws IOException If validation fails
     */
    private void validateUsername(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Username cannot be blank.");
        }
        if (!input.matches("^[A-Za-z0-9_]{3,15}$")) {
            throw new IOException("Username must be 3-15 chars and use letters, numbers, or underscore.");
        }
    }

    /**
     * Validates password against security requirements:
     * - Not null or empty
     * - Minimum 8 characters
     * - At least one: uppercase, lowercase, digit, special character
     *
     * @param input Password to validate
     * @throws IOException If validation fails
     */
    private void validatePassword(String input) throws IOException {
        if (input == null || input.isEmpty()) {
            throw new IOException("Password cannot be blank.");
        }
        if (!input.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$")) {
            throw new IOException(
                    "Password must be 8+ chars and contain: 1 uppercase, 1 lowercase, 1 digit, 1 special character.");
        }
    }
}