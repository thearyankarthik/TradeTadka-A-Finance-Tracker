import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();  // Create UserRepository instance
        SwingUtilities.invokeLater(() -> new LoginFrame(userRepository));  // Pass to LoginFrame
    }
}
