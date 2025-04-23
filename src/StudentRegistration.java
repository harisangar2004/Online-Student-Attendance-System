package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegistration extends JFrame implements ActionListener {

    JTextField nameField, idField, emailField, phoneField;
    JPasswordField passwordField, confirmPasswordField;
    JComboBox<String> departmentCombo, yearCombo;
    JButton register, clear;

    public StudentRegistration() {
        setTitle("STUDENT REGISTRATION");
        setLayout(null);

        // Title
        JLabel title = new JLabel("NEW STUDENT REGISTRATION");
        title.setFont(new Font("Courier New", Font.BOLD, 24));
        title.setBounds(150, 20, 500, 30);
        add(title);

        // Personal Details Section
        JLabel personalDetails = new JLabel("Personal Details");
        personalDetails.setFont(new Font("Courier New", Font.BOLD, 18));
        personalDetails.setBounds(50, 60, 200, 25);
        add(personalDetails);

        // Name
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
        nameLabel.setBounds(50, 100, 150, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("Courier New", Font.PLAIN, 14));
        nameField.setBounds(200, 100, 300, 25);
        add(nameField);

        // Student ID
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
        idLabel.setBounds(50, 140, 150, 20);
        add(idLabel);

        idField = new JTextField();
        idField.setFont(new Font("Courier New", Font.PLAIN, 14));
        idField.setBounds(200, 140, 300, 25);
        add(idField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
        emailLabel.setBounds(50, 180, 150, 20);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(new Font("Courier New", Font.PLAIN, 14));
        emailField.setBounds(200, 180, 300, 25);
        add(emailField);

        // Phone
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
        phoneLabel.setBounds(50, 220, 150, 20);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setFont(new Font("Courier New", Font.PLAIN, 14));
        phoneField.setBounds(200, 220, 300, 25);
        add(phoneField);

        // Academic Details Section
        JLabel academicDetails = new JLabel("Academic Details");
        academicDetails.setFont(new Font("Courier New", Font.BOLD, 18));
        academicDetails.setBounds(50, 260, 200, 25);
        add(academicDetails);

        // Department
        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
        deptLabel.setBounds(50, 300, 150, 20);
        add(deptLabel);

        String[] departments = {"Computer Science", "Mathematics", "Physics", "Chemistry", "Biology"};
        departmentCombo = new JComboBox<>(departments);
        departmentCombo.setFont(new Font("Courier New", Font.PLAIN, 14));
        departmentCombo.setBounds(200, 300, 300, 25);
        add(departmentCombo);

        // Year
        JLabel yearLabel = new JLabel("Academic Year:");
        yearLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
        yearLabel.setBounds(50, 340, 150, 20);
        add(yearLabel);

        String[] years = {"1st Year", "2nd Year", "3rd Year", "4th Year"};
        yearCombo = new JComboBox<>(years);
        yearCombo.setFont(new Font("Courier New", Font.PLAIN, 14));
        yearCombo.setBounds(200, 340, 300, 25);
        add(yearCombo);

        // Account Details Section
        JLabel accountDetails = new JLabel("Account Details");
        accountDetails.setFont(new Font("Courier New", Font.BOLD, 18));
        accountDetails.setBounds(50, 380, 200, 25);
        add(accountDetails);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
        passLabel.setBounds(50, 420, 150, 20);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Courier New", Font.PLAIN, 14));
        passwordField.setBounds(200, 420, 300, 25);
        add(passwordField);

        // Confirm Password
        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        confirmPassLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
        confirmPassLabel.setBounds(50, 460, 150, 20);
        add(confirmPassLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Courier New", Font.PLAIN, 14));
        confirmPasswordField.setBounds(200, 460, 300, 25);
        add(confirmPasswordField);

        // Buttons
        register = new JButton("Register");
        register.setFont(new Font("Courier New", Font.BOLD, 14));
        register.setBounds(200, 510, 120, 30);
        register.setBackground(Color.BLACK);
        register.setForeground(Color.WHITE);
        register.addActionListener(this);
        add(register);

        clear = new JButton("Clear");
        clear.setFont(new Font("Courier New", Font.BOLD, 14));
        clear.setBounds(350, 510, 120, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        getContentPane().setBackground(Color.WHITE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            // Clear all fields
            nameField.setText("");
            idField.setText("");
            emailField.setText("");
            phoneField.setText("");
            departmentCombo.setSelectedIndex(0);
            yearCombo.setSelectedIndex(0);
            passwordField.setText("");
            confirmPasswordField.setText("");
        } else if (ae.getSource() == register) {
            // Validate and register student
            String name = nameField.getText();
            String id = idField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String department = (String) departmentCombo.getSelectedItem();
            String year = (String) yearCombo.getSelectedItem();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (name.isEmpty() || id.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                    password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!id.matches("\\d{8}")) {
                JOptionPane.showMessageDialog(this, "Student ID must be 8 digits", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Here you would typically save to database
            // For now, just show success message
            JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Close registration form and return to login
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new StudentRegistration();
    }
}