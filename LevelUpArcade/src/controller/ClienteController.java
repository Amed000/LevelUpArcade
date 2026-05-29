package controller;

import dao.ClienteDAOImpl;
import model.Cliente;
import view.ClienteView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClienteController {

    private ClienteView view;
    private DefaultTableModel model;
    private ClienteDAOImpl dao;

    public ClienteController(ClienteView view) {

        this.view = view;
        this.model = view.getModelo();

        dao = new ClienteDAOImpl();

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

            Cliente c = new Cliente();

            c.setNombre(view.getTxtNombre().getText());

            c.setEmail(view.getTxtEmail().getText());

            c.setTelefono(view.getTxtTelefono().getText());

            dao.insertar(c);

            cargarTabla();

            limpiar();

        } catch (Exception e) {

            System.out.println("Error agregando cliente: " + e.getMessage());
        }
    }

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

    private void limpiar() {

        view.getTxtNombre().setText(""); 

        view.getTxtEmail().setText("");

        view.getTxtTelefono().setText("");
    }

    private void cargarTabla() {

        model.setRowCount(0);

        List<Cliente> lista = dao.listar();

        for (Cliente c : lista) {

            model.addRow(new Object[]{

                    c.getIdCliente(),
                    c.getNombre(),
                    c.getEmail(),
                    c.getTelefono()
            });
        }
    }
}