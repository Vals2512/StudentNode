package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.StudentHandler;

public class ButtonHandler implements ActionListener {

    private StudentHandler sh;

    private PrincipalScreen principalScreen;

    public ButtonHandler(PrincipalScreen ps) {
        this.sh = new StudentHandler();
        this.principalScreen = ps;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Add in order by Id":
                AddStudentScreen addStudentScreen = new AddStudentScreen(principalScreen,
                        AddStudentScreen.ActionType.ADD);
                addStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;
            case "Add First":
                AddStudentScreen addFirstStudentScreen = new AddStudentScreen(principalScreen,
                        AddStudentScreen.ActionType.ADD_FIRST);
                addFirstStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;
            case "Add Last":
                AddStudentScreen addLastStudentScreen = new AddStudentScreen(principalScreen,
                        AddStudentScreen.ActionType.ADD_LAST);
                addLastStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;
            case "Add Before":
                AddStudentScreen addBeforeStudentScreen = new AddStudentScreen(principalScreen,
                        AddStudentScreen.ActionType.ADD_BEFORE);
                addBeforeStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;
            case "Add After":
                AddStudentScreen addAfterStudentScreen = new AddStudentScreen(principalScreen,
                        AddStudentScreen.ActionType.ADD_AFTER);
                addAfterStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;

            case "Find by Id":
                FindStudentScreen findStudentScreen = new FindStudentScreen(principalScreen);
                findStudentScreen.setVisible(true);
                principalScreen.setVisible(false);
                break;
            case "Update":
                System.out.println("Update button clicked");
                break;
            case "Delete":
                System.out.println("Delete button clicked");
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
