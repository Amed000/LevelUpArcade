package dao;

import java.util.List;
import model.Producto;

public interface ProductoDAO {

    void insertar(Producto p);

    List<Producto> listar();

    Producto buscarPorId(int id);

    void actualizar(Producto p);

    void eliminar(int id);
}