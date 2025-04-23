package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudentAttendancePage extends JFrame implements ActionListener {

    private JLabel welcomeLabel, dateTimeLabel, courseLabel, statusLabel;
    private JComboBox<String> courseComboBox;
    private JButton markPresentBtn, markAbsentBtn, viewReportBtn, logoutBtn;
    private JTextArea statusArea;
    private String studentId;
    private String studentName;

    public StudentAttendancePage(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;

        setTitle("Student Attendance Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(52, 143, 226);
                Color color2 = new Color(84, 194, 205);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        welcomeLabel = new JLabel("Welcome, " + studentName + " (" + studentId + ")");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);

        dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateTimeLabel.setForeground(Color.WHITE);
        updateDateTime();

        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        headerPanel.add(dateTimeLabel, BorderLayout.EAST);

        // Center panel for attendance form
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;

        // Course selection
        courseLabel = new JLabel("Select Course:");
        courseLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        courseLabel.setForeground(Color.WHITE);

        String[] courses = getStudentCourses(studentId); // Get courses for this student
        courseComboBox = new JComboBox<>(courses);
        courseComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        courseComboBox.setPreferredSize(new Dimension(300, 35));

        // Attendance buttons
        markPresentBtn = new JButton("Mark Present");
        styleButton(markPresentBtn, new Color(76, 175, 80));

        markAbsentBtn = new JButton("Mark Absent");
        styleButton(markAbsentBtn, new Color(244, 67, 54));

        // Status area
        statusLabel = new JLabel("Attendance Status:");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        statusLabel.setForeground(Color.WHITE);

        statusArea = new JTextArea(8, 40);
        statusArea.setEditable(false);
        statusArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusArea.setBackground(new Color(255, 255, 255, 150));
        statusArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(statusArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 100)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // Add components to center panel
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(courseLabel, gbc);

        gbc.gridx = 1;
        centerPanel.add(courseComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(markPresentBtn);
        buttonPanel.add(markAbsentBtn);
        centerPanel.add(buttonPanel, gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(statusLabel, gbc);

        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(scrollPane, gbc);

        // Footer panel with navigation buttons
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        footerPanel.setOpaque(false);

        viewReportBtn = new JButton("View My Attendance");
        styleButton(viewReportBtn, new Color(33, 150, 243));

        logoutBtn = new JButton("Logout");
        styleButton(logoutBtn, new Color(158, 158, 158));

        footerPanel.add(viewReportBtn);
        footerPanel.add(logoutBtn);

        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Add action listeners
        markPresentBtn.addActionListener(this);
        markAbsentBtn.addActionListener(this);
        viewReportBtn.addActionListener(this);
        logoutBtn.addActionListener(this);

        // Add main panel to frame
        add(mainPanel);

        // Load initial status
        updateAttendanceStatus();
    }

    private String[] getStudentCourses(String studentId) {
        // In a real application, this would fetch from database
        // Mock data for demonstration
        return new String[]{
                "-- Select Course --",
                "CS101 - Introduction to Programming",
                "MATH201 - Calculus",
                "ENG105 - Academic Writing"
        };
    }

    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
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

    private void updateAttendanceStatus() {
        // In a real app, this would fetch from database
        String status = "Your recent attendance:\n";
        status += "• CS101: Present (2023-10-15 09:15:23)\n";
        status += "• MATH201: Absent (2023-10-16 10:05:42)\n";
        status += "• ENG105: Present (2023-10-17 08:30:15)\n\n";
        status += "Select a course and mark your attendance for today.";

        statusArea.setText(status);
    }

    private void markAttendance(String status) {
        String selectedCourse = (String) courseComboBox.getSelectedItem();

        if (selectedCourse.equals("-- Select Course --")) {
            JOptionPane.showMessageDialog(this,
                    "Please select a course first",
                    "Selection Required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // In a real application, this would save to database
        boolean success = saveAttendanceToDatabase(studentId, selectedCourse, status, timestamp);

        if (success) {
            statusArea.append("\nMarked " + status + " for " + selectedCourse + " at " + timestamp);
            JOptionPane.showMessageDialog(this,
                    "Attendance marked successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to mark attendance. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean saveAttendanceToDatabase(String studentId, String course, String status, String timestamp) {
        // This would be your JDBC code to save to database
        // For now, just simulate success
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == markPresentBtn) {
            markAttendance("Present");
        } else if (e.getSource() == markAbsentBtn) {
            markAttendance("Absent");
        } else if (e.getSource() == viewReportBtn) {
            // Open student's attendance report
            new pages.StudentAttendanceReport(studentId, studentName).setVisible(true);
        } else if (e.getSource() == logoutBtn) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION);

//            if (confirm == JOptionPane.YES_OPTION) {
//                this.dispose();
//                new StudentAttendanceSystem().setVisible(true);
//            }
        }
    }

    public static void main( String[] args ) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // For testing, provide sample student ID and name
            new StudentAttendancePage("S1001", "John Doe").setVisible(true);
        });
    }
}