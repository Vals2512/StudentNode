package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ActionType;

/**
 * The ButtonHandler class handles the events triggered by button clicks
 * in the graphical user interface. It implements the ActionListener
 * interface to manage actions for various buttons on the PrincipalScreen.
 */
public class ButtonHandler implements ActionListener {
    /**
     * Reference to the main application screen.
     */
    private PrincipalScreen principalScreen;


    /**
     * Constructor for the ButtonHandler class.
     *
     * @param ps The main screen (PrincipalScreen) from which the button events are
     *           managed.
     */
    public ButtonHandler(PrincipalScreen ps) {
        this.principalScreen = ps;
    }

    /**
     * Handles button click events based on the command associated with the button.
     * Different actions are performed depending on the button clicked.
     *
     * @param e The ActionEvent triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Add in order by Id":
                AddStudentScreen addStudentScreen = new AddStudentScreen(principalScreen,
                        ActionType.ADD);
                addStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;
            case "Add First":
                AddStudentScreen addFirstStudentScreen = new AddStudentScreen(principalScreen,
                        ActionType.ADD_FIRST);
                addFirstStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;
            case "Add Last":
                AddStudentScreen addLastStudentScreen = new AddStudentScreen(principalScreen,
                        ActionType.ADD_LAST);
                addLastStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;
            case "Add Before":
                AddStudentScreen addBeforeStudentScreen = new AddStudentScreen(principalScreen,
                        ActionType.ADD_BEFORE);
                addBeforeStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;
            case "Add After":
                AddStudentScreen addAfterStudentScreen = new AddStudentScreen(principalScreen,
                        ActionType.ADD_AFTER);
                addAfterStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;
            case "Find by Id":
                FindStudentScreen findStudentScreen = new FindStudentScreen(principalScreen);
                findStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;
            case "Update":
                UpdateStudentScreen updateStudentScreen = new UpdateStudentScreen(principalScreen);
                updateStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;
            case "Delete":

                DeleteStudentScreen deleteStudentScreen = new DeleteStudentScreen(principalScreen);
                deleteStudentScreen.setVisible(true);
                principalScreen.setVisible(false);

                break;
            case "Show All Students":
                ShowAllStudentsScreen screen = new ShowAllStudentsScreen();
                screen.setVisible(true);
                break;
            default:
                System.out.println("Unknown action: " + command);
        }
    }
}
