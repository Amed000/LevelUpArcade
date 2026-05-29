package dao;

import java.util.List;
import model.Proveedor;

public interface ProveedorDAO {

    void insertar(Proveedor p);

    void eliminar(int id);

    List<Proveedor> listar();

    Proveedor buscarPorId(int id);

    void actualizar(Proveedor p);
}