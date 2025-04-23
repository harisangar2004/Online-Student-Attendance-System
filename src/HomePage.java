//package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomePage extends JFrame implements ActionListener {

    // Components
    private JLabel welcomeLabel, dateTimeLabel;
    private JButton attendanceBtn, viewReportBtn, manageStudentsBtn, logoutBtn;
    private JPanel cardsPanel;
    private CardLayout cardLayout;

    // Card Panels
    private JPanel dashboardPanel, attendancePanel, reportPanel, studentsPanel;

    public HomePage(String username) {
        setTitle("Student Attendance System - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Main container with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(52, 143, 226));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);

        dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateTimeLabel.setForeground(Color.WHITE);
        updateDateTime();

        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        headerPanel.add(dateTimeLabel, BorderLayout.EAST);

        // Navigation Panel
        JPanel navPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        attendanceBtn = createNavButton("Mark Attendance", new Color(76, 175, 80));
        viewReportBtn = createNavButton("View Reports", new Color(33, 150, 243));
        manageStudentsBtn = createNavButton("Manage Students", new Color(156, 39, 176));
        logoutBtn = createNavButton("Logout", new Color(244, 67, 54));

        navPanel.add(attendanceBtn);
        navPanel.add(viewReportBtn);
        navPanel.add(manageStudentsBtn);
        navPanel.add(logoutBtn);

        // Card Panel for content
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        // Create all card panels
        createDashboardPanel();
        createAttendancePanel();
        createReportPanel();
        createStudentsPanel();

        cardsPanel.add(dashboardPanel, "dashboard");
        cardsPanel.add(attendancePanel, "attendance");
        cardsPanel.add(reportPanel, "reports");
        cardsPanel.add(studentsPanel, "students");

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(navPanel, BorderLayout.CENTER);
        mainPanel.add(cardsPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JButton createNavButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.addActionListener(this);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void createDashboardPanel() {
        dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Dashboard Overview", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        // Stats Panel
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 15, 15));

        addStatCard(statsPanel, "Total Students", "1,245", new Color(63, 81, 181));
        addStatCard(statsPanel, "Today's Attendance", "87%", new Color(0, 150, 136));
        addStatCard(statsPanel, "Pending Requests", "12", new Color(255, 152, 0));
        addStatCard(statsPanel, "Active Courses", "15", new Color(233, 30, 99));

        // Recent Activity
        JTextArea activityArea = new JTextArea();
        activityArea.setEditable(false);
        activityArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        activityArea.setText("Recent Activity:\n"
                + "• Student John Doe marked present for CS101\n"
                + "• New student Jane Smith registered\n"
                + "• Attendance report generated for Mathematics\n");

        JScrollPane scrollPane = new JScrollPane(activityArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Recent Activity"));

        dashboardPanel.add(title, BorderLayout.NORTH);
        dashboardPanel.add(statsPanel, BorderLayout.CENTER);
        dashboardPanel.add(scrollPane, BorderLayout.SOUTH);
    }

    private void addStatCard(JPanel panel, String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        titleLabel.setForeground(Color.WHITE);

        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(Color.WHITE);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        panel.add(card);
    }

    private void createAttendancePanel() {
        attendancePanel = new JPanel(new BorderLayout());
        attendancePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Mark Attendance", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        // Attendance Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Course Selection
        JLabel courseLabel = new JLabel("Select Course:");
        courseLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        String[] courses = {"CS101 - Introduction to Programming",
                "MATH201 - Calculus",
                "PHY301 - Modern Physics"};
        JComboBox<String> courseCombo = new JComboBox<>(courses);
        courseCombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        // Date Selection
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JTextField dateField = new JTextField(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dateField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        dateField.setEditable(false);

        // Student Table
        String[] columnNames = {"Student ID", "Name", "Status"};
        Object[][] data = {
                {"S1001", "John Doe", "Present"},
                {"S1002", "Jane Smith", "Absent"},
                {"S1003", "Robert Johnson", "Present"}
        };

        JTable studentTable = new JTable(data, columnNames);
        studentTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Submit Button
        JButton submitBtn = new JButton("Submit Attendance");
        submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        submitBtn.setBackground(new Color(76, 175, 80));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Add components to form
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(courseLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(courseCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(scrollPane, gbc);

        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(submitBtn, gbc);

        attendancePanel.add(title, BorderLayout.NORTH);
        attendancePanel.add(formPanel, BorderLayout.CENTER);
    }

    private void createReportPanel() {
        reportPanel = new JPanel(new BorderLayout());
        reportPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Attendance Reports", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        // Report Options
        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JComboBox<String> reportType = new JComboBox<>(new String[]{"Daily Report", "Weekly Report", "Monthly Report"});
        JComboBox<String> courseFilter = new JComboBox<>(new String[]{"All Courses", "CS101", "MATH201", "PHY301"});
        JTextField dateFrom = new JTextField(10);
        JTextField dateTo = new JTextField(10);
        JButton generateBtn = new JButton("Generate Report");

        optionsPanel.add(new JLabel("Report Type:"));
        optionsPanel.add(reportType);
        optionsPanel.add(new JLabel("Course:"));
        optionsPanel.add(courseFilter);
        optionsPanel.add(new JLabel("From:"));
        optionsPanel.add(dateFrom);
        optionsPanel.add(new JLabel("To:"));
        optionsPanel.add(dateTo);
        optionsPanel.add(generateBtn);

        // Sample Report Data
        String[] reportColumns = {"Date", "Course", "Total Students", "Present", "Absent", "Percentage"};
        Object[][] reportData = {
                {"2023-10-01", "CS101", 45, 40, 5, "89%"},
                {"2023-10-01", "MATH201", 50, 42, 8, "84%"},
                {"2023-10-02", "CS101", 45, 38, 7, "84%"}
        };

        JTable reportTable = new JTable(reportData, reportColumns);
        JScrollPane reportScroll = new JScrollPane(reportTable);

        // Export Button
        JButton exportBtn = new JButton("Export to PDF");
        exportBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(exportBtn);

        reportPanel.add(title, BorderLayout.NORTH);
        reportPanel.add(optionsPanel, BorderLayout.CENTER);
        reportPanel.add(reportScroll, BorderLayout.SOUTH);
        reportPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createStudentsPanel() {
        studentsPanel = new JPanel(new BorderLayout());
        studentsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Manage Students", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        // Student Management Controls
        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton addBtn = new JButton("Add New Student");
        JButton editBtn = new JButton("Edit Student");
        JButton deleteBtn = new JButton("Delete Student");
        JTextField searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");

        controlsPanel.add(addBtn);
        controlsPanel.add(editBtn);
        controlsPanel.add(deleteBtn);
        controlsPanel.add(new JLabel("Search:"));
        controlsPanel.add(searchField);
        controlsPanel.add(searchBtn);

        // Student List Table
        String[] studentColumns = {"Student ID", "Name", "Email", "Department", "Year"};
        Object[][] studentData = {
                {"S1001", "John Doe", "john@univ.edu", "Computer Science", "3"},
                {"S1002", "Jane Smith", "jane@univ.edu", "Mathematics", "2"},
                {"S1003", "Robert Johnson", "robert@univ.edu", "Physics", "4"}
        };

        JTable studentTable = new JTable(studentData, studentColumns);
        JScrollPane studentScroll = new JScrollPane(studentTable);

        studentsPanel.add(title, BorderLayout.NORTH);
        studentsPanel.add(controlsPanel, BorderLayout.CENTER);
        studentsPanel.add(studentScroll, BorderLayout.SOUTH);
    }

    private void updateDateTime() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateTime = LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy - hh:mm:ss a"));
                dateTimeLabel.setText(dateTime);
            }
        });
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == attendanceBtn) {
            cardLayout.show(cardsPanel, "attendance");
        } else if (e.getSource() == viewReportBtn) {
            cardLayout.show(cardsPanel, "reports");
        } else if (e.getSource() == manageStudentsBtn) {
            cardLayout.show(cardsPanel, "students");
        } else if (e.getSource() == logoutBtn) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION);

//            if (confirm == JOptionPxane.YES_OPTION) {
//                this.dispose();
//                new pages.StudentAttendanceSystem().setVisible(true);
//            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new HomePage("Admin User").setVisible(true);
        });
    }
}