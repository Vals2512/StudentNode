package gui;

import javax.swing.*;
import controller.StudentHandler;
import model.EGender;
import model.Student;
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

    private JTextField idField;
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField careerField;
    private JComboBox<EGender> genderComboBox;
    private JButton updateButton;
    private JButton returnButton;

    /**
     * Constructs an UpdateStudentScreen.
     *
     * @param ps The principal screen that will be displayed when the user returns.
     */
    public UpdateStudentScreen(PrincipalScreen ps) {

        idField = new JTextField(10);
        nameField = new JTextField(10);
        lastNameField = new JTextField(10);
        emailField = new JTextField(10);
        careerField = new JTextField(10);
        genderComboBox = new JComboBox<>(EGender.values());

        updateButton = new JButton("Update");
        returnButton = new JButton("Return");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Career:"));
        panel.add(careerField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderComboBox);

        panel.add(updateButton);
        panel.add(returnButton);

        add(panel);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

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
                    StudentHandler sh = new StudentHandler();

                    if (sh.studentExists(id)) {
                        Student updatedStudent = new Student(id, name, lastName, email, gender, career);
                        sh.updateStudent(id, updatedStudent);
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

}
