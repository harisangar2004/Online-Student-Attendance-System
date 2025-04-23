//package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.ResultSet;

public class StudentAttendanceSystem extends JFrame implements ActionListener {

    JButton login, clear, signup;
    JTextField studentIdField;
    JPasswordField passwordField;
    JComboBox<String> courseComboBox;
    JTextArea statusArea;

    public JLabel createLabels(String content, int size, int x, int y, int width, int height) {
        JLabel temp = new JLabel(content);
        temp.setFont(new Font("Courier New", Font.BOLD, size));
        temp.setBounds(x,y,width,height);
        add(temp);
        return temp;
    }

    public JButton createButton(String content, int x, int y, int width) {
        JButton temp = new JButton(content);
        temp.setBounds(x,y,width,30);
        temp.setBackground(Color.BLACK);
        temp.setForeground(Color.WHITE);
        temp.addActionListener(this);
        add(temp);
        return temp;
    }

    public StudentAttendanceSystem() {
        setTitle("STUDENT ATTENDANCE SYSTEM");
        setLayout(null);

//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/school_logo.png"));
//        Image img = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
//        ImageIcon logo = new ImageIcon(img);
//        JLabel label = new JLabel(logo);
//        label.setBounds(150,10,100,100);
//        add(label);

        JLabel title = createLabels("STUDENT ATTENDANCE PORTAL", 38, 100, 50, 700, 40);
        JLabel studentIdLabel = createLabels("Student ID: ", 28, 100, 150, 250, 40);
        JLabel passwordLabel = createLabels("Password: ", 28, 120, 200, 400, 40);
        JLabel courseLabel = createLabels("Course: ", 28, 120, 250, 400, 40);

        studentIdField = new JTextField();
        studentIdField.setBounds(300, 155, 350, 30);
        studentIdField.setFont(new Font("Courier New", Font.BOLD, 14));
        add(studentIdField);

        passwordField = new JPasswordField();
        passwordField.setBounds(300, 205, 350, 30);
        passwordField.setFont(new Font("Courier New", Font.BOLD, 14));
        add(passwordField);

        String[] courses = {"-- Select Course --", "Computer Science 101", "Mathematics 201",
                "Physics 301", "Chemistry 102", "Biology 202"};
        courseComboBox = new JComboBox<>(courses);
        courseComboBox.setBounds(300, 255, 350, 30);
        courseComboBox.setFont(new Font("Courier New", Font.BOLD, 14));
        add(courseComboBox);

        statusArea = new JTextArea();
        statusArea.setBounds(120, 350, 550, 100);
        statusArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        statusArea.setEditable(false);
        statusArea.setBackground(new Color(240, 240, 240));
        statusArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(statusArea);

        login = createButton("LOGIN", 300, 470, 100);
        clear = createButton("CLEAR", 430, 470, 100);
        signup = createButton("REGISTER", 300, 510, 230);

        // Add date/time label
        JLabel dateTimeLabel = new JLabel();
        dateTimeLabel.setBounds(500, 550, 300, 20);
        dateTimeLabel.setFont(new Font("Courier New", Font.PLAIN, 12));
        updateDateTime(dateTimeLabel);
        add(dateTimeLabel);

        getContentPane().setBackground(Color.WHITE);
        setSize(800, 600);
        setVisible(true);
        setLocation(350, 100);
    }

    private void updateDateTime(JLabel label) {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateTime = LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy - hh:mm:ss a"));
                label.setText(dateTime);
            }
        });
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            studentIdField.setText("");
            passwordField.setText("");
            courseComboBox.setSelectedIndex(0);
            statusArea.setText("");
        } else if (ae.getSource() == login) {
            String studentId = studentIdField.getText();
            String password = new String(passwordField.getPassword());
            String course = (String) courseComboBox.getSelectedItem();

            if (studentId.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both Student ID and Password");
                return;
            }

            if (course.equals("-- Select Course --")) {
                JOptionPane.showMessageDialog(null, "Please select a course");
                return;
            }

            // Database authentication would go here
            if (authenticate(studentId, password)) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                statusArea.append("Login successful! Welcome " + studentId + "\n");
                statusArea.append("Course: " + course + "\n");
                statusArea.append("Attendance marked at: " + timestamp + "\n");

                // In real implementation, save to database here
                // markAttendance(studentId, course, timestamp);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Student ID or Password");
            }
        } else if (ae.getSource() == signup) {
            // Open registration form
            // new StudentRegistration().setVisible(true);
            setVisible(false); // Hide login window
            new pages.StudentRegistration().setVisible(true); // Show registration window
//            JOptionPane.showMessageDialog(null, "Registration form would open here");
        }
    }

    private boolean authenticate(String studentId, String password) {
        // Simulated authentication - replace with database check
        return studentId.matches("\\d{8}") && password.length() >= 6;
    }

    public static void main(String[] args) {
        new StudentAttendanceSystem();
    }
}