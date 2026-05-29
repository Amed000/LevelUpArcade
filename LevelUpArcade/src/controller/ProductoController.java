package controller;

import dao.ProductoDAOImpl;
import model.Producto;
import view.ProductoView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoController {

    private ProductoView view;
    private DefaultTableModel model;

    // DAO
    private ProductoDAOImpl dao;

    public ProductoController(ProductoView view) {

        this.view = view;
        this.model = view.getModelo();

        dao = new ProductoDAOImpl();

        initEvents();

        cargarTabla();
    }

    private void initEvents() {

        view.getBtnAgregar().addActionListener(e -> agregar());

        view.getBtnEliminar().addActionListener(e -> eliminar());

        view.getBtnLimpiar().addActionListener(e -> limpiar());
    }

    // =========================
    // AGREGAR
    // =========================

    private void agregar() {

        try {

            Producto p = new Producto();

            p.setNombre(view.getTxtNombre().getText());

            p.setPrecio(
                    Double.parseDouble(
                            view.getTxtPrecio().getText()
                    )
            );

            p.setStock(
                    Integer.parseInt(
                            view.getTxtStock().getText()
                    )
            );

            // VALORES TEMPORALES
            p.setDescripcion("Producto");
            p.setIdCategoria(1);
            p.setIdProveedor(1);

            // INSERTAR EN MYSQL
            dao.insertar(p);

            // RECARGAR TABLA
            cargarTabla();

            limpiar();

        } catch (Exception e) {

            System.out.println(
                    "Error agregando producto: "
                            + e.getMessage()
            );
        }
    }

    // =========================
    // ELIMINAR
    // =========================

    private void eliminar() {

        int fila = view.getTabla().getSelectedRow();

        if (fila != -1) {

            int id = Integer.parseInt(
                    model.getValueAt(fila, 0).toString()
            );

            dao.eliminar(id);

            cargarTabla();
        }
    }

    // =========================
    // LIMPIAR
    // =========================

    private void limpiar() {

        view.getTxtNombre().setText("");

        view.getTxtPrecio().setText("");

        view.getTxtStock().setText("");
    }

    // =========================
    // CARGAR TABLA
    // =========================

    private void cargarTabla() {

        model.setRowCount(0);

        List<Producto> lista = dao.listar();

        for (Producto p : lista) {

            model.addRow(new Object[]{

                    p.getIdProducto(),
                    p.getNombre(),
                    p.getPrecio(),
                    p.getStock()
            });
        }
    }
}