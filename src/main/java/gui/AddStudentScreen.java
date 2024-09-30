package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import controller.StudentHandler;
import model.ActionType;
import model.EGender;
import model.Student;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

/**
 * The AddStudentScreen class represents the graphical user interface screen for
 * adding a student.
 * It allows the user to input the student's details and adds them to the list
 * of students managed
 * by the StudentHandler.
 */
public class AddStudentScreen extends JFrame {
    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The type of action being performed.
     */
    private ActionType actionType;

    /**
     * Label for displaying the student ID.
     */
    private JLabel labelStudentID;

    /**
     * Label for displaying the student's first name.
     */
    private JLabel labelName;

    /**
     * Label for displaying the student's last name.
     */
    private JLabel labelLastname;

    /**
     * Label for displaying the student's gender.
     */
    private JLabel labelGender;

    /**
     * Label for displaying the student's career.
     */
    private JLabel labelCareer;

    /**
     * Label for displaying the student's email.
     */
    private JLabel labelEmail;

    /**
     * Label for displaying the student's photo or image.
     */
    private JLabel labelStudent;

    /**
     * Text field for entering the student ID.
     */
    private JTextField txtStudentId;

    /**
     * Text field for entering the student's first name.
     */
    private JTextField txtName;

    /**
     * Text field for entering the student's last name.
     */
    private JTextField txtLastname;

    /**
     * Text field for entering the student's gender.
     */
    private JTextField txtGender;

    /**
     * Text field for entering the student's career.
     */
    private JTextField txtCareer;

    /**
     * Text field for entering the student's email.
     */
    private JTextField txtEmail;

    /**
     * Button to add or confirm the action.
     */
    private JButton buttonAdd;

    /**
     * Button to cancel the action.
     */
    private JButton buttonCancel;

    /**
     * Instance of the StudentHandler to manage student-related actions.
     */
    private StudentHandler sh = StudentHandler.getInstance(); // Get the instance of the handler

    /**
     * Reference to the main screen of the application.
     */
    private PrincipalScreen ps;

    /**
     * Constructor to initialize the AddStudentScreen with a reference to the
     * PrincipalScreen
     * and the action type to be performed.
     *
     * @param ps         The main screen reference
     * @param actionType The action type for adding the student
     */
    public AddStudentScreen(PrincipalScreen ps, ActionType actionType) {
        this.actionType = actionType;
        this.configureScreen();
        this.buildComponents();
        this.addComponents();
        this.configureEvents();
        this.ps = ps;
    }

    /**
     * Default constructor for AddStudentScreen.
     */
    public AddStudentScreen() {
        this.configureScreen();
        this.buildComponents();
        this.addComponents();
        this.configureEvents();
    }

    /**
     * Configures the main settings of the screen, such as title, size, layout, and
     * background color.
     */
    private void configureScreen() {
        setTitle("Add Student");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.lightGray);
    }

    /**
     * Initializes and sets up the GUI components.
     */

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

    /**
     * Adds the components to the screen using GridBagLayout constraints.
     */
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

    /**
     * Configures the event handlers for the buttons (Add and Cancel).
     */
    private void configureEvents() {
        buttonAdd.addActionListener(e -> {
            try {
                // Convertir el texto de txtGender a Gender
                EGender gender = EGender.valueOf(txtGender.getText().toUpperCase());

                String id = txtStudentId.getText();
                String name = txtName.getText();
                String lastname = txtLastname.getText();
                String email = txtEmail.getText();
                String career = txtCareer.getText();

                boolean success = false;

                // Lógica basada en el tipo de acción
                switch (actionType) {
                    case ADD:
                        if (sh.studentExists(id)) {
                            JOptionPane.showMessageDialog(null, "The ID is already in use.");
                        } else {
                            success = sh.addStudent(id, name, lastname, email, gender, career);
                        }
                        break;
                    case ADD_FIRST:
                        if (sh.studentExists(id)) {
                            JOptionPane.showMessageDialog(null, "The ID is already in use.");
                        } else {
                            success = sh.addStudentFirst(id, name, lastname, email, gender, career);
                        }
                        break;
                    case ADD_LAST:
                        if (sh.studentExists(id)) {
                            JOptionPane.showMessageDialog(null, "The ID is already in use.");
                        } else {
                            success = sh.addStudentLast(id, name, lastname, email, gender, career);
                        }
                        break;
                    case ADD_BEFORE:
                        String beforeId = JOptionPane
                                .showInputDialog("Enter the ID of the student before whom to add:");
                        if (beforeId != null) {
                            if (!sh.studentExists(beforeId)) {
                                JOptionPane.showMessageDialog(null,
                                        "The student with ID " + beforeId + " does not exist.");
                            } else if (sh.studentExists(id)) {
                                JOptionPane.showMessageDialog(null, "The ID is already in use.");
                            } else {
                                success = sh.addStudentBefore(beforeId, id, name, lastname, email, gender, career);
                            }
                        }
                        break;
                    case ADD_AFTER:
                        String afterId = JOptionPane.showInputDialog("Enter the ID of the student after whom to add:");
                        if (afterId != null) {
                            if (!sh.studentExists(afterId)) {
                                JOptionPane.showMessageDialog(null,
                                        "The student with ID " + afterId + " does not exist.");
                            } else if (sh.studentExists(id)) {
                                JOptionPane.showMessageDialog(null, "The ID is already in use.");
                            } else {
                                success = sh.addStudentAfter(afterId, id, name, lastname, email, gender, career);
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