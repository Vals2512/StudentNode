package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.StudentHandler;
import model.Student;

/**
 * This class represents the graphical user interface screen to display all
 * students.
 * It allows users to view all students, as well as interact with buttons to
 * perform various actions such as displaying the first/last student, and
 * retrieving a student by position.
 */
public class ShowAllStudentsScreen extends JFrame {
    private static final long serialVersionUID = 1L;

    /** JTable to display the list of students. */
    private JTable table;

    /** Table model to manage the data shown in the JTable. */
    private DefaultTableModel model;

    /** Instance of StudentHandler to manage student-related operations. */
    private StudentHandler sh = StudentHandler.getInstance();

    /**
     * Constructs a new ShowAllStudentsScreen.
     * Initializes the screen configuration, builds components, adds them to the
     * frame, and sets up event handling.
     */
    public ShowAllStudentsScreen() {
        this.configureScreen();
        this.buildComponents();
        this.addComponents();
        this.configureEvents();
    }

    /**
     * Configures the basic properties of the screen, such as title, size, layout,
     * and background color.
     */
    private void configureScreen() {
        setTitle("Show All Students");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.decode("#F7F7F7"));
    }

    /**
     * Builds the components for the screen, including the table and its model, and
     * configures styles for the table.
     */
    private void buildComponents() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Career");

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setBackground(Color.decode("#FFFFFF"));
        table.setForeground(Color.decode("#333333"));

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.blue);
        header.setForeground(Color.decode("#FFFFFF"));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.decode("#FFFFFF"));
        scrollPane.getViewport().setBackground(Color.decode("#FFFFFF"));
    }

    /**
     * Adds all the components to the screen, such as the table and various buttons,
     * and configures their layout.
     */
    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(new JScrollPane(table), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        JButton buttonAscending = new JButton("Ascending");
        buttonAscending.addActionListener(e -> {
            loadStudents(true);
        });
        buttonAscending.setBackground(Color.MAGENTA);
        buttonAscending.setForeground(Color.decode("#FFFFFF"));
        this.add(buttonAscending, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton buttonDescending = new JButton("Descending");
        buttonDescending.addActionListener(e -> {
            loadStudents(false);
        });
        buttonDescending.setBackground(Color.CYAN);
        buttonDescending.setForeground(Color.black);
        this.add(buttonDescending, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        JButton buttonFirst = new JButton("First Student");
        buttonFirst.addActionListener(e -> {
            showStudentInfo(sh.getFirstStudent());
        });
        buttonFirst.setBackground(Color.blue);
        buttonFirst.setForeground(Color.decode("#FFFFFF"));
        this.add(buttonFirst, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        JButton buttonLast = new JButton("Last Student");
        buttonLast.addActionListener(e -> {
            showStudentInfo(sh.getLastStudent());
        });
        buttonLast.setBackground(Color.pink);
        buttonLast.setForeground(Color.black);
        this.add(buttonLast, gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        JButton buttonByPosition = new JButton("By Position");
        buttonByPosition.addActionListener(e -> {
            String position = JOptionPane.showInputDialog(this, "Enter the position:");
            if (position != null) {
                try {
                    int pos = Integer.parseInt(position);
                    showStudentInfo(sh.getStudentByPosition(pos));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonByPosition.setBackground(Color.decode("#4CAF50"));
        buttonByPosition.setForeground(Color.decode("#FFFFFF"));
        this.add(buttonByPosition, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton buttonTotalStudents = new JButton("Total Students");
        buttonTotalStudents.addActionListener(e -> {
            showTotalStudents();
        });
        buttonTotalStudents.setBackground(Color.decode("#8BC34A"));
        buttonTotalStudents.setForeground(Color.decode("#FFFFFF"));
        this.add(buttonTotalStudents, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton buttonBack = new JButton("Back");
        buttonBack.addActionListener(e -> {
            this.dispose();
        });
        buttonBack.setBackground(Color.red);
        buttonBack.setForeground(Color.decode("#FFFFFF"));
        this.add(buttonBack, gbc);
    }

    /**
     * Configures event handling for the screen, primarily loading students when the
     * screen is initialized.
     */
    private void configureEvents() {
        loadStudents(true);
    }

    /**
     * Loads all students into the table, in ascending or descending order based on
     * the given parameter.
     * 
     * @param ascending true if students should be loaded in ascending order, false
     *                  for descending
     */
    private void loadStudents(boolean ascending) {
        model.setRowCount(0);
        if(!sh.listAllStudents(ascending).isEmpty() ) {
            for (Student student : sh.listAllStudents(ascending)) {
                model.addRow(new Object[]{student.getId(), student.getName(), student.getCareer()});
            }
        }
    }

    /**
     * Displays the information of a given student in a dialog box.
     * 
     * @param student the student whose information will be displayed
     */
    private void showStudentInfo(Student student) {
        if (student != null) {
            String info = "ID: " + student.getId() + "\n";
            info += "Name: " + student.getName() + "\n";
            info += "Career: " + student.getCareer() + "\n";
            JOptionPane.showMessageDialog(this, info, "Student Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No student found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shows the total number of students in a dialog box.
     */
    private void showTotalStudents() {
        int totalStudents = sh.getNumberOfStudents();
        JOptionPane.showMessageDialog(this, "Total Students: " + totalStudents, "Total Students",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
