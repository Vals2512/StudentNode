package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import controller.StudentHandler;
import model.EGender;
import model.Student;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

public class AddStudentScreen extends JFrame {
    private static final long serialVersionUID = 1L;

    private JLabel labelStudentID;
    private JLabel labelName;
    private JLabel labelLastname;
    private JLabel labelGender;
    private JLabel labelCareer;
    private JLabel labelEmail;
    private JLabel labelStudent;

    private JTextField txtStudentId;
    private JTextField txtName;
    private JTextField txtLastname;
    private JTextField txtGender;
    private JTextField txtCareer;
    private JTextField txtEmail;

    private JButton buttonAdd;
    private JButton buttonCancel;
    private StudentHandler sh = StudentHandler.getInstance(); // Obtener la instancia del manejador
    private PrincipalScreen ps;

    public enum ActionType {
        ADD,
        ADD_FIRST,
        ADD_LAST,
        ADD_BEFORE,
        ADD_AFTER
    }

    private ActionType actionType;

    public AddStudentScreen(PrincipalScreen ps, ActionType actionType) {
        this.actionType = actionType;
        this.configureScreen();
        this.buildComponents();
        this.addComponents();
        this.configureEvents();
        this.ps = ps;
    }

    public AddStudentScreen() {
        this.configureScreen();
        this.buildComponents();
        this.addComponents();
        this.configureEvents();
    }

    private void configureScreen() {
        setTitle("Add Student");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.lightGray);
    }

    private void buildComponents() {
        this.labelStudent = new JLabel("Student");
        this.labelStudentID = new JLabel("ID");
        this.labelName = new JLabel("Name");
        this.labelLastname = new JLabel("Lastname");
        this.labelCareer = new JLabel("Career");
        this.labelGender = new JLabel("Gender");
        this.labelEmail = new JLabel("Email");

        Font boldFont = new Font("Times", Font.BOLD, 40);
        this.labelStudent.setFont(boldFont);

        Font boldFont2 = new Font("Times", Font.ITALIC, 20);
        this.labelStudentID.setFont(boldFont2);
        this.labelName.setFont(boldFont2);
        this.labelLastname.setFont(boldFont2);
        this.labelGender.setFont(boldFont2);
        this.labelCareer.setFont(boldFont2);
        this.labelEmail.setFont(boldFont2);

        this.txtStudentId = new JTextField(20);
        this.txtName = new JTextField(20);
        this.txtLastname = new JTextField(20);
        this.txtGender = new JTextField(20);
        this.txtCareer = new JTextField(20);
        this.txtEmail = new JTextField(20);

        this.txtStudentId.setBackground(Color.WHITE);
        this.txtLastname.setBackground(Color.WHITE);
        this.buttonAdd = new JButton("Add");
        this.buttonCancel = new JButton("Return");
        this.buttonAdd.setBackground(Color.GREEN);
        this.buttonCancel.setBackground(Color.RED);
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Inserción del título "Student"
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(labelStudent, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Inserción del título "ID"
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(labelStudentID, gbc);

        // Inserción del input del ID
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(txtStudentId, gbc);

        // Inserción del título "Name"
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        this.add(labelName, gbc);

        // Inserción del input de nombre
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        this.add(txtName, gbc);

        // Inserción del título "Lastname"
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        this.add(labelLastname, gbc);

        // Inserción del input de apellido
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.add(txtLastname, gbc);

        // Inserción del título "Email"
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        this.add(labelEmail, gbc);

        // Inserción del input de email
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        this.add(txtEmail, gbc);

        // Inserción del título "Gender"
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        this.add(labelGender, gbc);

        // Inserción del input de género
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        this.add(txtGender, gbc);

        // Inserción del título "Career"
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        this.add(labelCareer, gbc);

        // Inserción del input de carrera
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        this.add(txtCareer, gbc);

        // Inserción del botón "Cancelar"
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttonCancel, gbc);

        // Inserción del botón "Agregar"
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttonAdd, gbc);
    }

    private void configureEvents() {
        buttonAdd.addActionListener(e -> {
            try {
                // Convertir el texto de txtGender a Gender
                EGender gender = EGender.valueOf(txtGender.getText().toUpperCase());

                Student student = new Student(
                        txtStudentId.getText(),
                        txtName.getText(),
                        txtLastname.getText(),
                        txtEmail.getText(),
                        gender,
                        txtCareer.getText());

                boolean success = false;

                // Lógica basada en el tipo de acción
                switch (actionType) {
                    case ADD:
                        if (sh.studentExists(student.getId())) {
                            JOptionPane.showMessageDialog(null, "The ID is already in use.");
                        } else {
                            success = sh.addStudent(student);
                        }
                        break;
                    case ADD_FIRST:
                        if (sh.studentExists(student.getId())) {
                            JOptionPane.showMessageDialog(null, "The ID is already in use.");
                        } else {
                            success = sh.addStudentFirst(student);
                        }
                        break;
                    case ADD_LAST:
                        if (sh.studentExists(student.getId())) {
                            JOptionPane.showMessageDialog(null, "The ID is already in use.");
                        } else {
                            success = sh.addStudentLast(student);
                        }
                        break;
                    case ADD_BEFORE:
                        String beforeId = JOptionPane
                                .showInputDialog("Enter the ID of the student before whom to add:");
                        if (beforeId != null) {
                            if (sh.studentExists(student.getId())) {
                                JOptionPane.showMessageDialog(null, "The ID is already in use.");
                            } else {
                                success = sh.addStudentBefore(beforeId, student);
                            }
                        }
                        break;
                    case ADD_AFTER:
                        String afterId = JOptionPane.showInputDialog("Enter the ID of the student after whom to add:");
                        if (afterId != null) {
                            if (sh.studentExists(student.getId())) {
                                JOptionPane.showMessageDialog(null, "The ID is already in use.");
                            } else {
                                success = sh.addStudentAfter(afterId, student);
                            }
                        }
                        break;
                }

                if (success) {
                    JOptionPane.showMessageDialog(null, "Student added successfully!");

                    // Mostrar la lista de estudiantes
                    StudentHandler sh = StudentHandler.getInstance();
                    List<Student> studentsAscending = sh.listAllStudents(true);
                    List<Student> studentsDescending = sh.listAllStudents(false);

                    // Create a string to display the lists
                    String message = "Students added:\n";
                    for (Student student1 : studentsAscending) {
                        message += student1 + "\n";
                    }
                    message += "\nStudent's added in descending order:\n";
                    for (Student student2 : studentsDescending) {
                        message += student2 + "\n";
                    }

                    // Show the message in a JOptionPane
                    JOptionPane.showMessageDialog(null, message, "Student's list",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Failed to add student. Please check the details and ensure no duplicates.");
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Invalid gender entered. Please use MALE, FEMALE, or OTHER.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        });

        buttonCancel.addActionListener(e -> {
            if (ps != null) {
                ps.requestFocus();
                ps.setVisible(true);
            }
            dispose(); // Close the AddStudentScreen window
        });
    }
}