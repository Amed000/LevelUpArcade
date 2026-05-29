package controller;

import view.LoginView;

public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {

            // LOGIN
            LoginView loginView = new LoginView();
            new LoginController(loginView);

            loginView.setVisible(true);

        });
    }
}