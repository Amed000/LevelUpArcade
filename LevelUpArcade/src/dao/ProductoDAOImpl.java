package dao;

import java.sql.*;
import java.util.*;

import model.Producto;

/**
 * Implementación del DAO de Producto.
 * Gestiona operaciones CRUD sobre la tabla producto en la base de datos.
 */
public class ProductoDAOImpl implements ProductoDAO {

    Connection conn = DatabaseConnection.getConnection();

    /**
     * Valida los datos de un producto antes de realizar operaciones en BD.
     *
     * @param p objeto Producto a validar
     * @return true si es válido, false si tiene errores
     */
    private boolean validarProducto(Producto p) {

        if (p == null) {
            System.out.println("Producto nulo");
            return false;
        }

        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            System.out.println("Nombre obligatorio");
            return false;
        }

        if (p.getPrecio() < 0) {
            System.out.println("Precio inválido");
            return false;
        }

        if (p.getStock() < 0) {
            System.out.println("Stock inválido");
            return false;
        }

        return true;
    }

    /**
     * Inserta un nuevo producto en la base de datos.
     *
     * @param p objeto Producto a insertar
     */
    @Override
    public void insertar(Producto p) {

        if (!validarProducto(p)) return;

        String sql = "INSERT INTO producto(nombre, descripcion, precio, stock, id_categoria, id_proveedor) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getIdCategoria());
            ps.setInt(6, p.getIdProveedor());

            ps.executeUpdate();

            System.out.println("✔ Producto insertado correctamente");

        } catch (Exception e) {
            System.out.println("Error insertando producto: " + e.getMessage());
        }
    }

    /**
     * Obtiene la lista completa de productos.
     *
     * @return lista de productos almacenados en la base de datos
     */
    @Override
    public List<Producto> listar() {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM producto";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Producto p = new Producto();

                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setIdProveedor(rs.getInt("id_proveedor"));

                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error listando productos: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Busca un producto por su ID.
     *
     * @param id identificador del producto
     * @return objeto Producto si existe, null si no se encuentra
     */
    @Override
    public Producto buscarPorId(int id) {

        String sql = "SELECT * FROM producto WHERE id_producto = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Producto p = new Producto();

                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setIdProveedor(rs.getInt("id_proveedor"));

                return p;
            }

        } catch (Exception e) {
            System.out.println("Error buscando producto: " + e.getMessage());
        }

        return null;
    }

    /**
     * Actualiza los datos de un producto existente.
     *
     * @param p objeto Producto con los nuevos datos
     */
    @Override
    public void actualizar(Producto p) {

        if (!validarProducto(p)) return;

        if (p.getIdProducto() <= 0) {
            System.out.println("ID inválido para actualizar");
            return;
        }

        String sql = "UPDATE producto SET nombre=?, descripcion=?, precio=?, stock=?, id_categoria=?, id_proveedor=? WHERE id_producto=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getIdCategoria());
            ps.setInt(6, p.getIdProveedor());
            ps.setInt(7, p.getIdProducto());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println(" Producto actualizado correctamente");
            } else {
                System.out.println("Producto no encontrado");
            }

        } catch (Exception e) {
            System.out.println("Error actualizando producto: " + e.getMessage());
        }
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id identificador del producto a eliminar
     */
    @Override
    public void eliminar(int id) {

        if (id <= 0) {
            System.out.println("ID inválido");
            return;
        }

        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("✔ Producto eliminado correctamente");
            } else {
                System.out.println("Producto no encontrado");
            }

        } catch (Exception e) {
            System.out.println("Error eliminando producto: " + e.getMessage());
        }
    }
}