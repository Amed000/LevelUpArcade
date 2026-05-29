package controller;

import dao.ProveedorDAOImpl;
import model.Proveedor;
import view.ProveedorView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProveedorController {

    private ProveedorView view;
    private DefaultTableModel model;
    private ProveedorDAOImpl dao;

    public ProveedorController(ProveedorView view) {

        this.view = view;
        this.model = view.getModelo();
        this.dao = new ProveedorDAOImpl();

        initEvents();
        cargarTabla();
    }

    private void initEvents() {

        view.getBtnAgregar().addActionListener(e -> agregar());
        view.getBtnEliminar().addActionListener(e -> eliminar());
        view.getBtnLimpiar().addActionListener(e -> limpiar());
    }

    private void agregar() {

        try {

            Proveedor p = new Proveedor();

            p.setNombre(view.getTxtNombre().getText());
            p.setTelefono(view.getTxtTelefono().getText());
           

            dao.insertar(p);

            cargarTabla();
            limpiar();

        } catch (Exception e) {
            System.out.println("Error agregando proveedor: " + e.getMessage());
        }
    }

    private void eliminar() {

        int fila = view.getTabla().getSelectedRow();

        if (fila != -1) {

            int id = Integer.parseInt(model.getValueAt(fila, 0).toString());

            dao.eliminar(id);

            cargarTabla();
        }
    }

    private void limpiar() {

        view.getTxtNombre().setText("");
        view.getTxtTelefono().setText("");
 
    }

    private void cargarTabla() {

        model.setRowCount(0);

        List<Proveedor> lista = dao.listar();

        for (Proveedor p : lista) {

            model.addRow(new Object[]{
                    p.getIdProveedor(),
                    p.getNombre(),
                    p.getTelefono(),
                    p.getEmail()
            });
        }
    }
}