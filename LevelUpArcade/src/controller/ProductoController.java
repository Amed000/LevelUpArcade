package controller;

import java.util.List;

import dao.ProductoDAOImpl;
import model.Producto;

public class ProductoController {

    private ProductoDAOImpl dao;

    public ProductoController() {
        dao = new ProductoDAOImpl();
    }

    public void insertarProducto(Producto p) {
        dao.insertar(p);
    }

    public List<Producto> listarProductos() {
        return dao.listar();
    }
}