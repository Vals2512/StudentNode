package view;

import gui.PrincipalScreen;

public class App {

    public static void main(String[] args) {
        PrincipalScreen ps = new PrincipalScreen();
        ps.setVisible(Boolean.TRUE);
        ps.getSs().setVisible(false);
    }

}
