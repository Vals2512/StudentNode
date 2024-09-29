package gui;

import javax.swing.*;

import controller.StudentHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The DeleteStudentScreen class provides a user interface for deleting a
 * student
 * record based on the student's ID. It allows the user to input the ID and
 * perform the delete operation.
 */

public class DeleteStudentScreen extends JFrame implements ActionListener {

    private PrincipalScreen principalScreen;
    private JTextField idField;

    /**
     * Constructor for the DeleteStudentScreen class.
     *
     * @param principalScreen The main screen of the application.
     */
    public DeleteStudentScreen(PrincipalScreen principalScreen) {
        this.principalScreen = principalScreen;
        initUI();
    }

    /**
     * Initializes the user interface of the frame.
     */
    private void initUI() {
        setTitle("Delete Student");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Background panel
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBackground(new Color(240, 240, 240));

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(new Color(100, 149, 237));
        JLabel titleLabel = new JLabel("Delete Student");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(240, 240, 240));

        // ID field panel
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout());
        JLabel idLabel = new JLabel("Student ID:");
        idField = new JTextField(20);
        idPanel.add(idLabel);
        idPanel.add(idField);

        // Delete button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        buttonPanel.add(deleteButton);

        // Return button panel
        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new FlowLayout());
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principalScreen.setVisible(true);
                dispose();
            }
        });
        returnPanel.add(returnButton);

        // Add components to content panel
        contentPanel.add(idPanel);
        contentPanel.add(buttonPanel);
        contentPanel.add(returnPanel);

        // Add components to background panel
        backgroundPanel.add(titlePanel, BorderLayout.NORTH);
        backgroundPanel.add(contentPanel, BorderLayout.CENTER);

        // Add background panel to frame
        add(backgroundPanel);
    }

    /**
     * Handles the action event triggered by the delete button.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String id = idField.getText();
        if (id != null && !id.isEmpty()) {
            try {
                StudentHandler sh = new StudentHandler();
                sh.deleteStudent(id);
                principalScreen.setVisible(true);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Student not found");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid student ID");
        }
    }
}