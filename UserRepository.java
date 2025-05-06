import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<Integer, User> users = new HashMap<>(); // Stored by userID as key

    // Save a new user by userID
    public void saveUser(User user) {
        if (getUserByUsername(user.getUsername()) != null) {
            System.out.println("Username already exists. Registration failed.");
            return;
        }
        users.put(user.getUserID(), user);
    }

    // Retrieve a user by their userID
    public User getUserById(int userID) {
        return users.get(userID);
    }

    // Retrieve a user by their username
    public User getUserByUsername(String username) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public int getUserCount() {
    return users.size();
    }

    // Update an existing user by userID
    public void updateUser(User user) {
        users.put(user.getUserID(), user);
    }

    // Delete a user by userID
    public void deleteUser(int userID) {
        users.remove(userID);
    }
}
