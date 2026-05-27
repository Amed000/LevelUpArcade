package view;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnProductos;
    private JButton btnClientes;
    private JButton btnPedidos;
    private JButton btnProveedores;
    private JButton btnSalir;

    public MainMenuView() {

        setTitle("LevelUp Arcade");
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel(
                "LEVELUP ARCADE",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panelBotones = new JPanel(
                new GridLayout(5,1,15,15)
        );

        panelBotones.setBorder(
                BorderFactory.createEmptyBorder(20,60,20,60)
        );

        btnProductos = new JButton("Productos");
        btnClientes = new JButton("Clientes");
        btnPedidos = new JButton("Pedidos");
        btnProveedores = new JButton("Proveedores");
        btnSalir = new JButton("Salir");

        panelBotones.add(btnProductos);
        panelBotones.add(btnClientes);
        panelBotones.add(btnPedidos);
        panelBotones.add(btnProveedores);
        panelBotones.add(btnSalir);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.CENTER);

        add(panel);

        // EVENTOS

        btnProductos.addActionListener(e -> {

            ProductoView ventana = new ProductoView();
            ventana.setVisible(true);

        });

        btnClientes.addActionListener(e -> {

            ClienteView ventana = new ClienteView();
            ventana.setVisible(true);

        });

        btnPedidos.addActionListener(e -> {

            PedidoView ventana = new PedidoView();
            ventana.setVisible(true);

        });

        btnProveedores.addActionListener(e -> {

            ProveedorView ventana = new ProveedorView();
            ventana.setVisible(true);

        });

        btnSalir.addActionListener(e -> System.exit(0));
    }
}