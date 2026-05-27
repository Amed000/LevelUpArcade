package controller;

import java.util.List;

import dao.ClienteDAOImpl;
import model.Cliente;

public class ClienteController {

    private ClienteDAOImpl dao;

    public ClienteController() {
        dao = new ClienteDAOImpl();
    }

    public void insertarCliente(Cliente c) {
        dao.insertar(c);
    }

    public List<Cliente> listarClientes() {
        return dao.listar();
    }

    public void eliminarCliente(int id) {
        dao.eliminar(id);
    }
}