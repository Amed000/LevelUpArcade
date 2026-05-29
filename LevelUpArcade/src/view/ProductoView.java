package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductoView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtNombre, txtPrecio, txtStock;

    private JButton btnAgregar, btnEliminar, btnLimpiar;
    

    public ProductoView() {

        setTitle("Gestión Productos");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        add(header(), BorderLayout.NORTH);
        add(table(), BorderLayout.CENTER);
        add(bottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel header() {

        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);

        JLabel t = new JLabel("GESTIÓN PRODUCTOS");
        t.setForeground(Color.WHITE);
        t.setFont(new Font("Segoe UI", Font.BOLD, 22));

        p.add(t);
        return p;
    }

    private JScrollPane table() {

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Precio", "Stock"}, 0
        );

        tabla = new JTable(modelo);
        tabla.setRowHeight(25);

        return new JScrollPane(tabla);
    }

    private JPanel form() {

        JPanel p = new JPanel(new GridLayout(3, 2, 10, 10));

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();

        p.add(new JLabel("Nombre:"));
        p.add(txtNombre);

        p.add(new JLabel("Precio:"));
        p.add(txtPrecio);

        p.add(new JLabel("Stock:"));
        p.add(txtStock);

        return p;
    }

    private JPanel buttons() {

        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        p.add(btnAgregar);
        p.add(btnEliminar);
        p.add(btnLimpiar);

        return p;
    }

    private JPanel bottomPanel() {

        JPanel p = new JPanel(new BorderLayout());
        p.add(form(), BorderLayout.CENTER);
        p.add(buttons(), BorderLayout.SOUTH);
        return p;
    }

    // GETTERS
    public JTable getTabla() { return tabla; }
    public DefaultTableModel getModelo() { return modelo; }

    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtPrecio() { return txtPrecio; }
    public JTextField getTxtStock() { return txtStock; }

    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
}