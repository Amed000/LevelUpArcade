package view;

import controller.ClienteController;
import controller.IAController;
import controller.PedidoController;
import controller.ProductoController;
import controller.ProveedorController;

import javax.swing.*;
import java.awt.*;

import static util.UIComponents.*;
import static util.UITheme.*;

public class MainMenuView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnProductos;
    private JButton btnClientes;
    private JButton btnPedidos;
    private JButton btnProveedores;
    private JButton btnIA;
    private JButton btnSalir;

    public MainMenuView() {

        setTitle("LevelUp Arcade - Panel Admin");

        setSize(750, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        getContentPane().setBackground(LIGHT);

        // =========================
        // HEADER
        // =========================

        add(header("LEVELUP ARCADE"), BorderLayout.NORTH);

        // =========================
        // MENU
        // =========================

        add(menu(), BorderLayout.CENTER);

        // =========================
        // EVENTOS
        // =========================

        events();
    }

    // =========================
    // MENU
    // =========================

    private JPanel menu() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(6,1,15,15));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        40,
                        180,
                        40,
                        180
                )
        );

        panel.setBackground(LIGHT);

        btnProductos = button("Productos", PRIMARY);

        btnClientes = button("Clientes", PRIMARY);

        btnPedidos = button("Pedidos", PRIMARY);

        btnProveedores = button("Proveedores", PRIMARY);

        btnIA = button("Asistente IA", DARK);

        btnSalir = button("Salir", GRAY);

        panel.add(btnProductos);
        panel.add(btnClientes);
        panel.add(btnPedidos);
        panel.add(btnProveedores);
        panel.add(btnIA);
        panel.add(btnSalir);

        return panel;
    }

    // =========================
    // EVENTOS
    // =========================

    private void events() {

        // =========================
        // PRODUCTOS
        // =========================

    	btnProductos.addActionListener(e -> {

    	    ProductoView view = new ProductoView();

    	    new ProductoController(view);

    	    view.setVisible(true);
    	});

        // =========================
        // CLIENTES
        // =========================

    	btnClientes.addActionListener(e -> {

    	    ClienteView view = new ClienteView();

    	    new ClienteController(view);

    	    view.setVisible(true);
    	});

        // =========================
        // PEDIDOS
        // =========================

    	btnPedidos.addActionListener(e -> {

    	    PedidoView view = new PedidoView();

    	    new PedidoController(view);

    	    view.setVisible(true);
    	});

        // =========================
        // PROVEEDORES
        // =========================

    	btnProveedores.addActionListener(e -> {

    	    ProveedorView view = new ProveedorView();

    	    new ProveedorController(view);

    	    view.setVisible(true);
    	});

        // =========================
        // IA
        // =========================

        btnIA.addActionListener(e -> {

            IAView view = new IAView();

            new IAController(view);

            view.setVisible(true);
        });

        // =========================
        // SALIR
        // =========================

        btnSalir.addActionListener(e -> {

            System.exit(0);
        });
    }
}
