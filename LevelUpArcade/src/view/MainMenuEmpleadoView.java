package view;

import javax.swing.*;
import java.awt.*;

/**
 * Menú de empleado.
 * Solo permite consultas (sin permisos de modificación).
 */
public class MainMenuEmpleadoView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JButton btnListarProductos;
    private JButton btnListarClientes;
    private JButton btnListarPedidos;
    private JButton btnSalir;

    public MainMenuEmpleadoView() {

        setTitle("Menú Empleado - LevelUp Arcade");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // =========================
        // BOTONES
        // =========================

        btnListarProductos = new JButton("Consultar Productos");
        btnListarClientes = new JButton("Consultar Clientes");
        btnListarPedidos = new JButton("Consultar Pedidos");
        btnSalir = new JButton("Cerrar Sesión");

        panel.add(btnListarProductos);
        panel.add(btnListarClientes);
        panel.add(btnListarPedidos);
        panel.add(btnSalir);

        add(panel);

        // =========================
        // ACCIONES
        // =========================

        btnListarProductos.addActionListener(e -> listarProductos());
        btnListarClientes.addActionListener(e -> listarClientes());
        btnListarPedidos.addActionListener(e -> listarPedidos());
        btnSalir.addActionListener(e -> salir());
    }

    // =========================
    // ACCIONES DEL MENÚ
    // =========================

    private void listarProductos() {

        JOptionPane.showMessageDialog(this,
                "Función de consulta de productos (empleado)");
    }

    private void listarClientes() {

        JOptionPane.showMessageDialog(this,
                "Función de consulta de clientes (empleado)");
    }

    private void listarPedidos() {

        JOptionPane.showMessageDialog(this,
                "Función de consulta de pedidos (empleado)");
    }

    private void salir() {

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quieres cerrar sesión?",
                "Salir",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new LoginView().setVisible(true);
        }
    }
}