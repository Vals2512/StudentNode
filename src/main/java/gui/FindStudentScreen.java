package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;

import controller.StudentHandler;
import model.Student;

public class FindStudentScreen extends JFrame {
    private static final long serialVersionUID = 1L;

    private JLabel labelStudentID;
    private JLabel labelStudentFound;
    private JLabel labelStudentInfo;
    private JLabel labelStudentImage;

    private JTextField txtStudentId;

    private JButton buttonFind;
    private JButton buttonCancel;
    private StudentHandler sh = StudentHandler.getInstance(); // Obtener la instancia del manejador
    private PrincipalScreen ps;

    public FindStudentScreen(PrincipalScreen ps) {
        this.ps = ps;
        this.configureScreen();
        this.buildComponents();
        this.addComponents();
        this.configureEvents();
    }

    private void configureScreen() {
        setTitle("Find Student by Code");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.lightGray);
    }

    private void buildComponents() {
        this.labelStudentID = new JLabel("Student ID:");
        this.labelStudentFound = new JLabel("Student Found:");
        this.labelStudentInfo = new JLabel();
        this.labelStudentImage = new JLabel();

        Font boldFont = new Font("Times", Font.BOLD, 20);
        this.labelStudentID.setFont(boldFont);
        this.labelStudentFound.setFont(boldFont);

        this.txtStudentId = new JTextField(20);

        this.buttonFind = new JButton("Find");
        this.buttonCancel = new JButton("Return");
        this.buttonFind.setBackground(Color.GREEN);
        this.buttonCancel.setBackground(Color.RED);

        // Cargar la imagen del estudiante
        ImageIcon studentImage = new ImageIcon("src/main/resources/image1.png"); // Asegúrate de poner la ruta correcta
        Image scaledImage = studentImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        labelStudentImage = new JLabel(new ImageIcon(scaledImage));
        labelStudentImage.setHorizontalAlignment(JLabel.CENTER);

    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Inserción del título "Student ID"
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(labelStudentID, gbc);

        // Inserción del input del ID
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 0, 5, 5); // Reducir el espacio a la izquierda
        this.add(txtStudentId, gbc);

        // Inserción del botón "Find"
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 5, 5); // Reducir el espacio superior
        this.add(buttonFind, gbc);

        // Inserción del botón "Cancel"
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 5, 5); // Reducir el espacio superior
        this.add(buttonCancel, gbc);

        // Inserción del título "Student Found"
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(10, 5, 5, 5); // Aumentar el espacio superior
        this.add(labelStudentFound, gbc);

        // Inserción de la información del estudiante encontrado
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(10, 0, 5, 5); // Aumentar el espacio superior
        this.add(labelStudentInfo, gbc);

        // Inserción de la
        // Inserción de la imagen del estudiante encontrado
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 5, 5); // Aumentar el espacio superior
        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.add(labelStudentImage);
        this.add(panel, gbc);
    }

    private void configureEvents() {
        buttonFind.addActionListener(e -> {
            try {
                String studentId = txtStudentId.getText();
                Student student = sh.findStudentByCode(studentId);
                if (student != null) {
                    labelStudentInfo.setText(student.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Student not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        });

        buttonCancel.addActionListener(e -> {
            if (ps != null) {
                ps.requestFocus();
                ps.setVisible(true);
            }
            dispose(); // Close the FindStudentScreen window
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (ps != null) {
                    ps.requestFocus();
                    ps.setVisible(true);
                }
            }
        });
    }
}