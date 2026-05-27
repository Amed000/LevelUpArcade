package controller;

import java.util.List;

import dao.PedidoDAOImpl;
import model.Pedido;

public class PedidoController {

    private PedidoDAOImpl dao;

    public PedidoController() {
        dao = new PedidoDAOImpl();
    }

    public void insertarPedido(Pedido p) {
        dao.insertar(p);
    }

    public List<Pedido> listarPedidos() {
        return dao.listar();
    }
}