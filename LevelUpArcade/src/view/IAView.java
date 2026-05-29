package view;

import util.UIComponents;
import util.UITheme;

import javax.swing.*;
import java.awt.*;

public class IAView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextArea chatArea;
    private JTextField txtPregunta;
    private JButton btnEnviar;
    private JButton btnLimpiar;

    public IAView() {

        setTitle("Asistente IA - LevelUp Arcade");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        add(UIComponents.header("ASISTENTE IA"), BorderLayout.NORTH);
        add(crearChat(), BorderLayout.CENTER);
        add(crearInputPanel(), BorderLayout.SOUTH);
    }

    // =========================
    // CHAT
    // =========================
    private JScrollPane crearChat() {
        chatArea = UIComponents.createChatArea();

        chatArea.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(chatArea);

        scroll.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        return scroll;
    }

    // =========================
    // INPUT PANEL
    // =========================

    private JPanel crearInputPanel() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        txtPregunta = new JTextField();

        btnEnviar = UIComponents.button(
                "Enviar",
                UITheme.IA
        );

        btnLimpiar = UIComponents.button(
                "Limpiar",
                UITheme.DANGER
        );

        JPanel botones = new JPanel(new GridLayout(1, 2, 10, 10));

        botones.add(btnEnviar);
        botones.add(btnLimpiar);

        panel.add(txtPregunta, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.EAST);

        return panel;
    }

    // =========================
    // MÉTODOS CHAT
    // =========================

    public void agregarMensajeUsuario(String mensaje) {

        chatArea.append("\n🧑 Tú:\n" + mensaje + "\n");
    }

    public void agregarMensajeIA(String mensaje) {

        chatArea.append("\n🤖 IA:\n" + mensaje + "\n");
    }

    public void limpiarChat() {

        chatArea.setText("");
    }

    // =========================
    // GETTERS
    // =========================

    public JTextField getTxtPregunta() {
        return txtPregunta;
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }
}