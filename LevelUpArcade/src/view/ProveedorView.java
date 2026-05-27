package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProveedorView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtNombre;
    private JTextField txtEmpresa;

    public ProveedorView() {

        setTitle("Proveedores");
        setSize(700,450);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel(
                "GESTIÓN DE PROVEEDORES",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Empresa");

        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);

        JPanel formulario = new JPanel(
                new GridLayout(2,2,10,10)
        );

        txtNombre = new JTextField();
        txtEmpresa = new JTextField();

        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);

        formulario.add(new JLabel("Empresa:"));
        formulario.add(txtEmpresa);

        JPanel botones = new JPanel();

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");

        botones.add(btnAgregar);
        botones.add(btnEliminar);

        JPanel inferior = new JPanel(new BorderLayout());

        inferior.add(formulario, BorderLayout.CENTER);
        inferior.add(botones, BorderLayout.SOUTH);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(inferior, BorderLayout.SOUTH);

        add(panel);

        btnAgregar.addActionListener(e -> agregarProveedor());

        btnEliminar.addActionListener(e -> eliminarProveedor());
    }

    private void agregarProveedor() {

        modelo.addRow(new Object[] {

                modelo.getRowCount() + 1,
                txtNombre.getText(),
                txtEmpresa.getText()
        });
    }

    private void eliminarProveedor() {

        int fila = tabla.getSelectedRow();

        if(fila >= 0) {

            modelo.removeRow(fila);
        }
    }
}