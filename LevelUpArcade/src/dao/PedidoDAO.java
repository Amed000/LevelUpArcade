package dao;

import java.util.List;
import model.Pedido;

public interface PedidoDAO {

    void insertar(Pedido p);
    List<Pedido> listar();
}