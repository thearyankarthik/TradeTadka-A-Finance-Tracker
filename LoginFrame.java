import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserRepository userRepository;

    public LoginFrame(UserRepository userRepository) {
        this.userRepository = userRepository;

        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Paper Stock Trading", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(inputPanel, BorderLayout.CENTER);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());
        buttonPanel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterButtonListener());
        buttonPanel.add(registerButton);

        setVisible(true);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Please enter both username and password.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            User user = userRepository.getUserByUsername(username);

            if (user == null) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Username does not exist. Please register first.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            } else if (!user.login(password)) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Incorrect password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, "Login successful! Welcome, " + username + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
                new PortfolioFrame(user);
                dispose();
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = JOptionPane.showInputDialog(LoginFrame.this, "Enter a new username:");
            if (username == null || username.trim().isEmpty()) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Username cannot be empty.", "Registration Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String password = JOptionPane.showInputDialog(LoginFrame.this, "Enter a new password:");
            if (password == null || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Password cannot be empty.", "Registration Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String email = JOptionPane.showInputDialog(LoginFrame.this, "Enter your email:");
            if (email == null || email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Email cannot be empty.", "Registration Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (userRepository.getUserByUsername(username) != null) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Username already exists. Please choose another.", "Registration Error", JOptionPane.WARNING_MESSAGE);
            } else {
                int newUserID = userRepository.getUserCount() + 1;
                User newUser = new User(newUserID, username, password, email, 100000.0);
                userRepository.saveUser(newUser);
                JOptionPane.showMessageDialog(LoginFrame.this, "Registration successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
