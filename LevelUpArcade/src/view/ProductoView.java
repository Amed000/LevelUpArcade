package view;

import service.LlmService;

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

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;

    public ProductoView() {

        setTitle("Gestión de Productos");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ======================
        // PANEL PRINCIPAL
        // ======================
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("GESTIÓN DE PRODUCTOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        // ======================
        // TABLA
        // ======================
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        // ======================
        // FORMULARIO
        // ======================
        JPanel formulario = new JPanel(new GridLayout(3, 2, 10, 10));

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();

        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);

        formulario.add(new JLabel("Precio:"));
        formulario.add(txtPrecio);

        formulario.add(new JLabel("Stock:"));
        formulario.add(txtStock);

        // ======================
        // BOTONES CRUD
        // ======================
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        // ======================
        // BOTONES IA
        // ======================
        JButton btnIADescripcion = new JButton("IA Descripción");
        JButton btnIACategoria = new JButton("IA Categoría");

        JPanel botones = new JPanel();

        botones.add(btnAgregar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);
        botones.add(btnIADescripcion);
        botones.add(btnIACategoria);

        // ======================
        // PANEL INFERIOR
        // ======================
        JPanel inferior = new JPanel(new BorderLayout());
        inferior.add(formulario, BorderLayout.CENTER);
        inferior.add(botones, BorderLayout.SOUTH);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(inferior, BorderLayout.SOUTH);

        add(panel);

        // ======================
        // EVENTOS CRUD
        // ======================
        btnAgregar.addActionListener(e -> agregarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // ======================
        // EVENTOS IA
        // ======================
        btnIADescripcion.addActionListener(e -> generarDescripcionIA());
        btnIACategoria.addActionListener(e -> sugerirCategoriaIA());
    }

    // ======================
    // CRUD
    // ======================
    private void agregarProducto() {

        if (txtNombre.getText().isEmpty()
                || txtPrecio.getText().isEmpty()
                || txtStock.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Rellena todos los campos");
            return;
        }

        modelo.addRow(new Object[]{
                modelo.getRowCount() + 1,
                txtNombre.getText(),
                txtPrecio.getText(),
                txtStock.getText()
        });

        limpiarCampos();
    }

    private void eliminarProducto() {

        int fila = tabla.getSelectedRow();

        if (fila >= 0) {
            modelo.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Selecciona un producto");
        }
    }

    private void limpiarCampos() {

        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
    }

    // ======================
    // IA (FASE 5)
    // ======================
    private void generarDescripcionIA() {

        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Introduce un nombre de producto");
            return;
        }

        LlmService ia = new LlmService();

        String prompt =
                "Genera una descripción corta y profesional del siguiente producto: "
                        + txtNombre.getText();

        String respuesta = ia.enviarPrompt(prompt);

        JOptionPane.showMessageDialog(this, respuesta);
    }

    private void sugerirCategoriaIA() {

        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Introduce un nombre de producto");
            return;
        }

        LlmService ia = new LlmService();

        String prompt =
                "Dime la categoría de tienda adecuada para este producto: "
                        + txtNombre.getText();

        String respuesta = ia.enviarPrompt(prompt);

        JOptionPane.showMessageDialog(this, respuesta);
    }
}