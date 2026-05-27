package dao;

import java.util.List;
import model.Cliente;

public interface ClienteDAO {

    void insertar(Cliente c);
    List<Cliente> listar();
    void eliminar(int id);
}