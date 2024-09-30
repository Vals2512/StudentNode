package gui;

import javax.swing.*;

import model.ActionType;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * The main screen of the application that extends {@link JFrame}.
 * <p>
 * This class serves as the primary user interface for the application,
 * providing access to various functionalities related to student management.
 * It initializes components and manages user interactions within the main window.
 * </p>
 */
public class PrincipalScreen extends JFrame {

    /**
     * Reference to the screen for adding a student.
     */
    private AddStudentScreen ss;

    /**
     * The type of action being performed.
     */
    private ActionType actionType;


    /**
     * Constructs a new PrincipalScreen instance.
     */
    public PrincipalScreen() {
        this.ss = new AddStudentScreen(this, actionType);

        setTitle("Student System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Student System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(75, 0, 130));
        add(titleLabel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);

        ImageIcon studentImage = new ImageIcon("src/main/resources/student.png");
        Image scaledImage = studentImage.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBackground(Color.CYAN);

        leftPanel.add(imageLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.LIGHT_GRAY);

        rightPanel.add(Box.createVerticalStrut(60));

        String[] buttonLabels = {
                "Add in order by Id", "Add First", "Add Last", "Add Before", "Add After",
                "Find by Id", "Update", "Delete", "Show All Students"
        };

        for (String label : buttonLabels) {
            JButton button = createCustomButton(label);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            rightPanel.add(button);
            rightPanel.add(Box.createVerticalStrut(8));
        }

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Creates a custom button with the specified text.
     * 
     * @param text the text for the button
     * @return the custom button
     */
    private JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(123, 104, 238));
        button.setForeground(Color.WHITE); // Color del texto
        button.setPreferredSize(new Dimension(200, 30));
        button.setMaximumSize(new Dimension(200, 30));

        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.addActionListener(new ButtonHandler(this));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(button.getBackground().darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(123, 104, 238));
            }
        });

        return button;
    }

    /**
     * Gets the AddStudentScreen instance associated with this PrincipalScreen.
     * 
     * @return the AddStudentScreen instance
     */
    public AddStudentScreen getSs() {
        return ss;
    }

    /**
     * Sets the AddStudentScreen instance associated with this PrincipalScreen.
     * 
     * @param ss the AddStudentScreen instance
     */
    public void setSs(AddStudentScreen ss) {
        this.ss = ss;
    }

}
