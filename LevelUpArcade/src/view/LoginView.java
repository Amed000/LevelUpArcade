package view;

import javax.swing.*;

import dao.UsuarioDAOImpl;
import model.Usuario;

import java.awt.*;

public class LoginView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();

    // 🔥 ESTO ES LO QUE TE FALTABA
    private Usuario usuarioLogueado;

    public LoginView() {

        setTitle("Login - LevelUp Arcade");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);

        panel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnLogin = new JButton("Iniciar Sesión");

        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(e -> login());
    }

    /**
     * LOGIN conectado a BD (Fase 7)
     */
    private void login() {

        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rellena todos los campos");
            return;
        }

        Usuario u = usuarioDAO.login(usuario, password);

        if (u != null) {

            //  GUARDAR USUARIO LOGUEADO (ESTO ARREGLA TU JAR)
            this.usuarioLogueado = u;

            JOptionPane.showMessageDialog(this,
                    "Bienvenido " + u.getUsername() + " (" + u.getRol() + ")");

            // CONTROL DE ROLES
            if (u.getRol().equalsIgnoreCase("ADMIN")) {

                new MainMenuView().setVisible(true);

            } else if (u.getRol().equalsIgnoreCase("EMPLEADO")) {

                new MainMenuEmpleadoView().setVisible(true);
            }

            dispose();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Usuario o contraseña incorrectos");
        }
    }

    /**
     * ESTO ES CLAVE PARA MainApp
     */
    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }
}