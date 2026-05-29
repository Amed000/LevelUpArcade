package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PedidoView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtFecha;
    private JComboBox<String> cbEstado;

    private JButton btnAgregar, btnEliminar, btnLimpiar;

    public PedidoView() {

        setTitle("Gestión Pedidos");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        add(header(), BorderLayout.NORTH);
        add(table(), BorderLayout.CENTER);
        add(bottom(), BorderLayout.SOUTH);
    }

    private JPanel header() {

        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);

        JLabel t = new JLabel("GESTIÓN PEDIDOS");
        t.setForeground(Color.WHITE);
        t.setFont(new Font("Segoe UI", Font.BOLD, 22));

        p.add(t);
        return p;
    }

    private JScrollPane table() {

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Fecha", "Estado"}, 0
        );

        tabla = new JTable(modelo);

        return new JScrollPane(tabla);
    }

    // 🔥 FORMULARIO (ESTO ES LO QUE TE FALTABA)

    private JPanel form() {

        JPanel p = new JPanel(new GridLayout(2, 2, 10, 10));

        txtFecha = new JTextField(); // opcional (puedes ignorarlo)
        cbEstado = new JComboBox<>(new String[]{
                "PENDIENTE",
                "PREPARADO",
                "ENVIADO",
                "ENTREGADO",
                "CANCELADO"
        });

        p.add(new JLabel("Fecha:"));
        p.add(txtFecha);

        p.add(new JLabel("Estado:"));
        p.add(cbEstado);

        return p;
    }

    private JPanel buttons() {

        JPanel p = new JPanel();

        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        p.add(btnAgregar);
        p.add(btnEliminar);
        p.add(btnLimpiar);

        return p;
    }

    private JPanel bottom() {

        JPanel p = new JPanel(new BorderLayout());
        p.add(form(), BorderLayout.CENTER);
        p.add(buttons(), BorderLayout.SOUTH);
        return p;
    }

    // GETTERS

    public JTable getTabla() { return tabla; }
    public DefaultTableModel getModelo() { return modelo; }

    public JTextField getTxtFecha() { return txtFecha; }
    public JComboBox<String> getCbEstado() { return cbEstado; }

    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
}