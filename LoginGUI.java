import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel, passwordLabel, homeLabel;

    public LoginGUI() {
        setTitle("User Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setVisible(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);


        // Username Label and Text Field
        JLabel usernameLabel = new JLabel("Enter your username: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);

        // Password Label and Password Field
        JLabel passwordLabel = new JLabel("Enter your password: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        // Login Button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(loginButton, gbc);

        // Action Listener for the Login Button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check the username and password (you can replace this with your authentication logic)
                if (authenticateUser(username, password)) {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Login Successful!");
                    setVisible(false);
                    new ShoppingCentreGUI();

                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Invalid username or password. Try again.");
                }
            }
        });

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private boolean authenticateUser(String username, String password) {
        // Replace this with your authentication logic
        // For simplicity, let's consider any non-empty username and password as valid
        return !username.isEmpty() && !password.isEmpty();
    }
}

