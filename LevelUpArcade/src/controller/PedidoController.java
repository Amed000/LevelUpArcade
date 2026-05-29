package controller;

import dao.PedidoDAOImpl;
import model.Pedido;
import view.PedidoView;

import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.List;

public class PedidoController {

    private PedidoView view;
    private DefaultTableModel model;
    private PedidoDAOImpl dao;

    public PedidoController(PedidoView view) {

        this.view = view;
        this.model = view.getModelo();
        this.dao = new PedidoDAOImpl();

        initEvents();
        loadTable();
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

            Pedido p = new Pedido();

            // 🔥 FECHA AUTOMÁTICA (NO SE ESCRIBE)
            p.setFecha(new Date());

            // 🔥 ESTADO DESDE COMBOBOX
            p.setEstado(view.getCbEstado().getSelectedItem().toString());

            dao.insertar(p);

            loadTable();
            limpiar();

        } catch (Exception e) {
            System.out.println("Error pedido: " + e.getMessage());
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

            loadTable();
        }
    }

    // =========================
    // LIMPIAR
    // =========================
    private void limpiar() {

        view.getCbEstado().setSelectedIndex(0);
    }

    // =========================
    // CARGAR TABLA
    // =========================
    private void loadTable() {

        model.setRowCount(0);

        List<Pedido> lista = dao.listar();

        for (Pedido p : lista) {

            model.addRow(new Object[]{
                    p.getIdPedido(),
                    p.getFecha(),
                    p.getEstado()
            });
        }
    }
}