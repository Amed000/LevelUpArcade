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

    public PedidoView() {

        setTitle("Pedidos");
        setSize(700,400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel(
                "GESTIÓN DE PEDIDOS",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("Fecha");
        modelo.addColumn("Total");

        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnNuevo = new JButton("Nuevo Pedido");

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnNuevo, BorderLayout.SOUTH);

        add(panel);

        btnNuevo.addActionListener(e -> nuevoPedido());
    }

    private void nuevoPedido() {

        modelo.addRow(new Object[] {

                modelo.getRowCount() + 1,
                "Cliente Demo",
                "2026-05-25",
                "0.00 €"
        });
    }
}