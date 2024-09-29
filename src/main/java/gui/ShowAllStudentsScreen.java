package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.StudentHandler;
import model.Student;

public class ShowAllStudentsScreen extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTable table;
    private DefaultTableModel model;

    private StudentHandler sh = StudentHandler.getInstance(); // Obtener la instancia del manejador

    public ShowAllStudentsScreen() {
        this.configureScreen();
        this.buildComponents();
        this.addComponents();
        this.configureEvents();
    }

    private void configureScreen() {
        setTitle("Show All Students");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.decode("#F7F7F7")); // Color de fondo
    }

    private void buildComponents() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Career");

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setBackground(Color.decode("#FFFFFF")); // Color de fondo de la tabla
        table.setForeground(Color.decode("#333333")); // Color de texto de la tabla

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.blue); // Color de fondo del encabezado
        header.setForeground(Color.decode("#FFFFFF")); // Color de texto del encabezado

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.decode("#FFFFFF")); // Color de fondo del scroll pane
        scrollPane.getViewport().setBackground(Color.decode("#FFFFFF")); // Color de fondo del viewport
    }

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
        buttonAscending.setBackground(Color.MAGENTA); // Color de fondo del botón
        buttonAscending.setForeground(Color.decode("#FFFFFF")); // Color de texto del botón
        this.add(buttonAscending, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        JButton buttonDescending = new JButton("Descending");
        buttonDescending.addActionListener(e -> {
            loadStudents(false);
        });
        buttonDescending.setBackground(Color.CYAN);
        buttonDescending.setForeground(Color.black);
        this.add(buttonDescending, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        JButton buttonFirst = new JButton("First Student");
        buttonFirst.addActionListener(e -> {
            showStudentInfo(sh.getFirstStudent());
        });
        buttonFirst.setBackground(Color.blue); // Color de fondo del botón
        buttonFirst.setForeground(Color.decode("#FFFFFF")); // Color de texto del botón
        this.add(buttonFirst, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        JButton buttonLast = new JButton("Last Student");
        buttonLast.addActionListener(e -> {
            showStudentInfo(sh.getLastStudent());
        });
        buttonLast.setBackground(Color.pink); // Color de fondo del botón
        buttonLast.setForeground(Color.black); // Color de texto del botón
        this.add(buttonLast, gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
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
        buttonByPosition.setBackground(Color.decode("#4CAF50")); // Color de fondo del botón
        buttonByPosition.setForeground(Color.decode("#FFFFFF")); // Color de texto del botón
        this.add(buttonByPosition, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        JButton buttonBack = new JButton("Back");
        buttonBack.addActionListener(e -> {
            this.dispose();
        });
        buttonBack.setBackground(Color.red); // Color de fondo del botón
        buttonBack.setForeground(Color.decode("#FFFFFF")); // Color de texto del botón
        this.add(buttonBack, gbc);
    }

    private void configureEvents() {
        loadStudents(true);
    }

    private void loadStudents(boolean ascending) {
        model.setRowCount(0);
        for (Student student : sh.listAllStudents(ascending)) {
            model.addRow(new Object[] { student.getId(), student.getName(), student.getCareer() });
        }
    }

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
}