package tomato;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Class for user registration (Sign Up) with Swing GUI.
 */
public class SignUp extends JFrame implements ActionListener {

	
	 Statement stmt;
     PreparedStatement preStat,updatestat;
    JLabel title, nameLbl, genderLbl, addressLbl, contactLbl, usernameLbl, passwordLbl;
    JTextField idFld, nameFld, addressFld, contactFld, usernameFld, passwordFld;
    JComboBox<String> genderComboBox;
    JButton submitBtn, resetBtn, exitBtn;
    JPanel panel;
    /**
     * Constructor to set up the registration form GUI.
     */
    public SignUp() {
        super("Registration Form");
        setSize(400, 400);
        setLayout(null);

        title = new JLabel("User Registration Form");
        title.setBounds(60, 7, 200, 30);

     // Components for user input
        nameLbl = new JLabel("Name");
        nameLbl.setBounds(30, 85, 60, 30);
        nameFld = new JTextField();
        nameFld.setBounds(95, 85, 130, 30);

        genderLbl = new JLabel("Gender");
        genderLbl.setBounds(30, 120, 60, 30);
        String[] genders = {"Male", "Female"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(95, 120, 130, 30);

        addressLbl = new JLabel("Address");
        addressLbl.setBounds(30, 155, 60, 30);
        addressFld = new JTextField();
        addressFld.setBounds(95, 155, 130, 30);

        contactLbl = new JLabel("Contact");
        contactLbl.setBounds(30, 190, 60, 30);
        contactFld = new JTextField();
        contactFld.setBounds(95, 190, 130, 30);

        usernameLbl = new JLabel("Username");
        usernameLbl.setBounds(30, 225, 60, 30);
        usernameFld = new JTextField();
        usernameFld.setBounds(95, 225, 130, 30);

        passwordLbl = new JLabel("Password");
        passwordLbl.setBounds(30, 260, 60, 30);
        passwordFld = new JTextField();
        passwordFld.setBounds(95, 260, 130, 30);
     // Add components to the frame
        add(title);
        add(nameLbl);
        add(nameFld);
        add(genderLbl);
        add(genderComboBox);
        add(addressLbl);
        add(addressFld);
        add(contactLbl);
        add(contactFld);
        add(usernameLbl);
        add(usernameFld);
        add(passwordLbl);
        add(passwordFld);
        // Buttons for submitting, resetting, and exiting
        submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 300, 100, 25);

        resetBtn = new JButton("Reset");
        resetBtn.setBounds(140, 300, 100, 25);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(250, 300, 100, 25);

        add(submitBtn);
        add(resetBtn);
        add(exitBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
     // Attach action listeners to buttons
        submitBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        exitBtn.addActionListener(this);
    }
    /**
     * Handle button clicks.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(submitBtn)) {
            try {
            	// Establish a database connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useSSL=false", "", "");
                String Name = nameFld.getText();
                String Gender = (String) genderComboBox.getSelectedItem();
                String Address = addressFld.getText();
                int Contact = Integer.parseInt(contactFld.getText());
                String Username = usernameFld.getText();
                String Password = new String(passwordFld.getText()); // Get password (assuming passwordFld is a JPasswordField)

                
               
                // Declare the variables for SQL statements
                Statement stmt = connect.createStatement();
                PreparedStatement preStat = connect.prepareStatement("insert into signup(Name, Gender, Address, Contact, Username, Password) values(?, ?, ?, ?,?,?)");
                
                preStat.setString(1, Name);
                preStat.setString(2, Gender);
                preStat.setString(3, Address);
                preStat.setInt(4, Contact);
                preStat.setString(5, Username);
                preStat.setString(6, Password);
                preStat.executeUpdate();
                
                System.out.println("Record is Inserted");
             // Close the database connection
                connect.close();
             // Display registration success message
                System.out.println("Finished");
                
                Component frame = null;
				JOptionPane.showMessageDialog(frame, "Registration successful!");
				
				   // Open the login GUI
               login.createLoginGUI();
                
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else if (e.getSource().equals(exitBtn)) {
            System.out.println("JFrame closed");
            System.exit(0);
        } else if (e.getSource().equals(resetBtn)) {
        	// Reset user input fields
            nameFld.setText(null);
            genderComboBox.setSelectedIndex(0);
            addressFld.setText(null);
            contactFld.setText(null);
            usernameFld.setText(null);
            passwordFld.setText(null);
        }
    }
    /**
     * Entry point to launch the SignUp class.
     */
    public static void main(String[] args) {
        new SignUp();
    }
}
