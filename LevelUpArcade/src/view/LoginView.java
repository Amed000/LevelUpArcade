package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public LoginView() {

        setTitle("Login - LevelUp Arcade");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        add(header(), BorderLayout.NORTH);
        add(form(), BorderLayout.CENTER);
        add(button(), BorderLayout.SOUTH);
    }

    private JPanel header() {

        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);

        JLabel l = new JLabel("LOGIN");
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Segoe UI", Font.BOLD, 18));

        p.add(l);
        return p;
    }

    private JPanel form() {

        JPanel p = new JPanel(new GridLayout(2,2));

        txtUser = new JTextField();
        txtPass = new JPasswordField();

        p.add(new JLabel("Usuario"));
        p.add(txtUser);

        p.add(new JLabel("Contraseña"));
        p.add(txtPass);

        return p;
    }

    private JPanel button() {

        JPanel p = new JPanel();

        btnLogin = new JButton("Entrar");
        p.add(btnLogin);

        return p;
    }

    public JTextField getTxtUser() { return txtUser; }
    public JPasswordField getTxtPass() { return txtPass; }
    public JButton getBtnLogin() { return btnLogin; }
}