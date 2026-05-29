package controller;

import dao.UsuarioDAOImpl;
import model.Usuario;
import view.LoginView;
import view.MainMenuView;

import javax.swing.*;

public class LoginController {

    private LoginView view;
    private UsuarioDAOImpl dao;

    public LoginController(LoginView view) {

        this.view = view;
        this.dao = new UsuarioDAOImpl();

        initEvents();
    }

    private void initEvents() {

        view.getBtnLogin().addActionListener(e -> login());
    }

    private void login() {

        String username = view.getTxtUser().getText();
        String password = new String(view.getTxtPass().getPassword());

        Usuario u = dao.login(username, password);

        if (u != null) {

            JOptionPane.showMessageDialog(view,
                    "Bienvenido " + u.getUsername() + " (" + u.getRol() + ")");

            MainMenuView menu = new MainMenuView();

            // 🔥 aquí puedes usar el rol después si quieres
            menu.setVisible(true);

            view.dispose();

        } else {

            JOptionPane.showMessageDialog(view,
                    "Usuario o contraseña incorrectos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}