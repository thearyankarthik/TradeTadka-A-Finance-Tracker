import java.util.List;

public class User {
    private int userID;
    private String username;
    private String passwordHash;
    private String email;
    private double cashBalance;

    public User(int userID, String username, String passwordHash, String email, double cashBalance) {
        this.userID = userID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.cashBalance = cashBalance;
    }

    public boolean register(UserRepository userRepository) {
        // Check if the username already exists in the repository
        if (userRepository.getUserByUsername(username) != null) {
            System.out.println("Username already taken. Choose another username.");
            return false;
        }
        // Save the user to the repository
        userRepository.saveUser(this);
        System.out.println("Registration successful.");
        return true;
    }

    public boolean login(String password) {
        return passwordHash.equals(password);
    }

    public void logout() {
        System.out.println("User logged out.");
    }

    public void viewPortfolio(Portfolio portfolio) {
        portfolio.viewHoldings();
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void updateCashBalance(double amount) {
        cashBalance += amount;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }
}
