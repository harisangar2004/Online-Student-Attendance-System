package pages;

import javax.swing.*;
import java.awt.*;

public class StudentAttendanceReport extends JFrame {

    public StudentAttendanceReport(String studentId, String studentName) {
        setTitle("Attendance Report for " + studentName);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JLabel title = new JLabel("Attendance Report for " + studentName + " (" + studentId + ")", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mainPanel.add(title, BorderLayout.NORTH);

        // Sample report data - in real app, fetch from database
        String[] columns = {"Date", "Course", "Status", "Time"};
        Object[][] data = {
                {"2023-10-15", "CS101", "Present", "09:15:23"},
                {"2023-10-16", "MATH201", "Absent", "-"},
                {"2023-10-17", "ENG105", "Present", "08:30:15"}
        };

        JTable reportTable = new JTable(data, columns);
        reportTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(reportTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer with export button
        JButton exportBtn = new JButton("Export to PDF");
        exportBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.add(exportBtn);
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);
    }
}