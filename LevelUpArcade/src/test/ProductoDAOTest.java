package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.ProductoDAOImpl;
import model.Producto;

/**
 * Clase de pruebas unitarias para ProductoDAO.
 * Se validan operaciones CRUD sobre la base de datos.
 */
public class ProductoDAOTest {

    ProductoDAOImpl dao = new ProductoDAOImpl();

    /**
     * Test de inserción de un producto válido.
     * Comprueba que no se produzcan errores en el INSERT.
     */
    @Test
    public void testInsertarProducto() {

        Producto p = new Producto();

        p.setNombre("Producto Test");
        p.setDescripcion("Test JUnit");
        p.setPrecio(10.5);
        p.setStock(5);
        p.setIdCategoria(1);
        p.setIdProveedor(1);

        assertDoesNotThrow(() -> dao.insertar(p));
    }

    /**
     * Test de búsqueda de producto por ID.
     * Verifica que el producto existe en la base de datos.
     */
    @Test
    public void testBuscarProducto() {

        Producto p = dao.buscarPorId(1);

        assertNotNull(p, "El producto debería existir en la base de datos");
    }

    /**
     * Test de actualización de un producto existente.
     * Modifica datos y comprueba que la operación no falla.
     */
    @Test
    public void testActualizarProducto() {

        Producto p = dao.buscarPorId(1);

        if (p != null) {

            p.setNombre("Producto Actualizado");
            p.setPrecio(99.99);

            assertDoesNotThrow(() -> dao.actualizar(p));
        }
    }

    /**
     * Test de eliminación de un producto.
     * Inserta un producto temporal y lo elimina después.
     */
    @Test
    public void testEliminarProducto() {

        Producto p = new Producto();

        p.setNombre("Producto a eliminar");
        p.setDescripcion("Temporal");
        p.setPrecio(5);
        p.setStock(1);
        p.setIdCategoria(1);
        p.setIdProveedor(1);

        dao.insertar(p);

        Producto ultimo = dao.listar().get(dao.listar().size() - 1);

        assertDoesNotThrow(() -> dao.eliminar(ultimo.getIdProducto()));
    }
}