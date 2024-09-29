package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrincipalScreen extends JFrame {

    private AddStudentScreen ss;

    private AddStudentScreen.ActionType actionType;

    public PrincipalScreen() {
        this.ss = new AddStudentScreen(this, actionType);

        // Configuración del JFrame
        setTitle("Student System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Tamaño de la ventana aumentado
        setLayout(new BorderLayout());

        // Título en la parte superior
        JLabel titleLabel = new JLabel("Student System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36)); // Cambiar la fuente y el tamaño
        titleLabel.setForeground(Color.WHITE); // Color del texto
        titleLabel.setOpaque(true); // Necesario para que se muestre el color de fondo
        titleLabel.setBackground(new Color(75, 0, 130)); // Color púrpura para el fondo
        add(titleLabel, BorderLayout.NORTH);

        // Panel izquierdo para la imagen
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.WHITE); // Fondo blanco

        // Cargar la imagen del estudiante y redimensionarla
        ImageIcon studentImage = new ImageIcon("src/main/resources/student.png"); // Asegúrate de poner la ruta correcta
        Image scaledImage = studentImage.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBackground(Color.CYAN);

        leftPanel.add(imageLabel, BorderLayout.CENTER);

        // Panel derecho para los botones
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS)); // Cambiado a BoxLayout
        rightPanel.setBackground(Color.LIGHT_GRAY);

        // Añadir un espacio entre el título y los botones
        rightPanel.add(Box.createVerticalStrut(60)); // Espacio adicional

        // Crear los botones personalizados
        String[] buttonLabels = {
                "Add in order by Id", "Add First", "Add Last", "Add Before", "Add After",
                "Find by Id", "Update", "Delete", "Show All Students"
        };

        for (String label : buttonLabels) {
            JButton button = createCustomButton(label);
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrando el botón
            rightPanel.add(button);
            rightPanel.add(Box.createVerticalStrut(8)); // Espacio entre los botones
        }

        // Añadir los paneles al JFrame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Método para crear botones personalizados
    private JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente más atractiva
        button.setBackground(new Color(123, 104, 238)); // Color de fondo (tono púrpura)
        button.setForeground(Color.WHITE); // Color del texto
        button.setPreferredSize(new Dimension(200, 30)); // Tamaño fijo para que todos sean iguales
        button.setMaximumSize(new Dimension(200, 30)); // Asegurarse de que todos tengan la misma longitud

        // Crear bordes redondeados
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.addActionListener(new ButtonHandler(this));
        // Añadir un MouseListener para oscurecer el botón al pasar el cursor
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(button.getBackground().darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(123, 104, 238)); // Volver al color original
            }
        });

        return button;
    }

    public AddStudentScreen getSs() {
        return ss;
    }

    public void setSs(AddStudentScreen ss) {
        this.ss = ss;
    }

}
