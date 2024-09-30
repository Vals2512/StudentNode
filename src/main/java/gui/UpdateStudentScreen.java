package gui;

import javax.swing.*;
import controller.StudentHandler;
import model.EGender;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The UpdateStudentScreen class represents a GUI for updating student
 * information.
 * It extends JFrame to create a window where the user can enter and modify
 * details
 * of a student identified by their ID.
 * 
 */
public class UpdateStudentScreen extends JFrame {

    /**
     * Text field for entering the student's ID.
     */
    private JTextField idField;

    /**
     * Text field for entering the student's first name.
     */
    private JTextField nameField;

    /**
     * Text field for entering the student's last name.
     */
    private JTextField lastNameField;

    /**
     * Text field for entering the student's email.
     */
    private JTextField emailField;

    /**
     * Text field for entering the student's career.
     */
    private JTextField careerField;

    /**
     * Combo box for selecting the student's gender.
     */
    private JComboBox<EGender> genderComboBox;

    /**
     * Button to update the student's information.
     */
    private JButton updateButton;

    /**
     * Button to return to the previous screen.
     */
    private JButton returnButton;

    /**
     * Constructs an UpdateStudentScreen.
     *
     * @param ps The principal screen that will be displayed when the user returns.
     */
    public UpdateStudentScreen(PrincipalScreen ps) {
        // Set the title for the window
        setTitle("Update Student Information");

        // Create text fields and combo box
        idField = new JTextField(10);
        nameField = new JTextField(10);
        lastNameField = new JTextField(10);
        emailField = new JTextField(10);
        careerField = new JTextField(10);
        genderComboBox = new JComboBox<>(EGender.values());

        // Create buttons with styling
        updateButton = new JButton("Update");
        styleButton(updateButton, "#4CAF50");

        returnButton = new JButton("Return");
        styleButton(returnButton, "#B71C1C");

        // Create a panel with a grid layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.decode("#F7F7F7"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("ID of the student:"), gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("New Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("New Last Name:"), gbc);
        gbc.gridx = 1;
        panel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("New Email:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("New Career:"), gbc);
        gbc.gridx = 1;
        panel.add(careerField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("New Gender:"), gbc);
        gbc.gridx = 1;
        panel.add(genderComboBox, gbc);

        // Add buttons
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(updateButton, gbc);
        gbc.gridx = 1;
        panel.add(returnButton, gbc);

        // Add the panel to the frame
        add(panel);

        // Frame settings
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        panel.setBackground(Color.decode("#E3F2FD"));

        // Action listeners for buttons
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String email = emailField.getText().trim();
                String career = careerField.getText().trim();
                EGender gender = (EGender) genderComboBox.getSelectedItem();

                if (!id.isEmpty() && !name.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !career.isEmpty()) {
                    StudentHandler sh = StudentHandler.getInstance();

                    if (sh.studentExists(id)) {
                        sh.updateStudent(id, name, lastName, email, gender, career);
                        PrincipalScreen principalScreen = new PrincipalScreen();
                        principalScreen.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Student with code " + id + " does not exist.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter all the fields");
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrincipalScreen principalScreen = new PrincipalScreen();
                principalScreen.setVisible(true);
                dispose();
            }
        });
    }

    /**
     * Styles a button with the given background color.
     *
     * @param button The button to be styled.
     * @param color  The color code for the background.
     */
    private void styleButton(JButton button, String color) {
        button.setBackground(Color.decode(color));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
    }
}
