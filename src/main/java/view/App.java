package view;

import gui.PrincipalScreen;

/**
 * Main application class to launch the student management application.
 * It initializes the PrincipalScreen and sets its visibility.
 */
public class App {

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        PrincipalScreen ps = new PrincipalScreen();
        ps.setVisible(Boolean.TRUE);
        ps.getSs().setVisible(false);
    }
}

