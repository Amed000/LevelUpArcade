package dao;

import model.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAOImpl {

    Connection conn = DatabaseConnection.getConnection();

    public void insertar(Proveedor p) {

        String sql = "INSERT INTO proveedor(nombre, telefono) VALUES (?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());

            ps.executeUpdate();

            System.out.println("✔ Proveedor insertado");

        } catch (Exception e) {
            System.out.println("Error proveedor: " + e.getMessage());
        }
    }

    public List<Proveedor> listar() {

        List<Proveedor> lista = new ArrayList<>();

        String sql = "SELECT * FROM proveedor";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Proveedor p = new Proveedor();

                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setTelefono(rs.getString("telefono"));

                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error listar proveedor: " + e.getMessage());
        }

        return lista;
    }

    public void eliminar(int id) {

        String sql = "DELETE FROM proveedor WHERE id_proveedor=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar proveedor: " + e.getMessage());
        }
    }
}