package tomato;


import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
/**
 * A simple login application with Swing GUI.
 */
public class login {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createLoginGUI());
    }
    /**
     * Create the login GUI with Swing components.
     */
    public static void createLoginGUI() {
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
        	 // Get the entered username and password
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
         // Authenticate the user
            if (authenticateUser(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                System.out.println("LOGIN successful");
              
            } else {
                JOptionPane.showMessageDialog(frame, "Login failed. Please try again.");
            }
         // Clear the password field for security
            passwordField.setText("");
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }
    /**
     * Authenticate user against the database.
     *
     * @param username The entered user name
     * @param password The entered password
     * @return true if authentication is successful, false otherwise
     */
    private static boolean authenticateUser(String username, String password) {
    	// JDBC database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/user";
        String dbUser = "";
        String dbPassword = "";

        try {
        	 // Establish a database connection
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            System.out.println("Connection successful");
            // Prepare and execute the SQL query
            String query = "SELECT * FROM signup WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                // If authentication is successful, create and initialize GameGUI
                GameGUI gm = new GameGUI();
                gm.initGame(null);
                return true; // return true after successful login
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false; // return false for unsuccessful login
    }
}
