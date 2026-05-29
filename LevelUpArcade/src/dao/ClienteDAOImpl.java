package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import util.LoggerUtil;

/**
 * Implementación DAO de Cliente utilizando JDBC.
 * Gestiona las operaciones CRUD de la tabla cliente.
 */
public class ClienteDAOImpl implements ClienteDAO {

    Connection conn = DatabaseConnection.getConnection();

    /**
     * Inserta un cliente en la base de datos.
     */
    @Override
    public void insertar(Cliente c) {

        String sql = "INSERT INTO cliente(nombre,email,telefono) VALUES (?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getTelefono());

            ps.executeUpdate();

            LoggerUtil.log("Cliente insertado correctamente: " + c.getNombre());

        } catch (Exception e) {

            LoggerUtil.log("ERROR insertando cliente: " + e.getMessage());

            System.out.println(e.getMessage());
        }
    }

    /**
     * Obtiene todos los clientes de la base de datos.
     */
    @Override
    public List<Cliente> listar() {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM cliente";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Cliente c = new Cliente();

                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));

                lista.add(c);
            }

            LoggerUtil.log("Listado de clientes obtenido correctamente");

        } catch (Exception e) {

            LoggerUtil.log("ERROR listando clientes: " + e.getMessage());

            System.out.println(e.getMessage());
        }

        return lista;
    }

    /**
     * Elimina un cliente de la base de datos.
     */
    @Override
    public void eliminar(int id) {

        String sql = "DELETE FROM cliente WHERE id_cliente=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            LoggerUtil.log("Cliente eliminado correctamente. ID: " + id);

        } catch (Exception e) {

            LoggerUtil.log("ERROR eliminando cliente: " + e.getMessage());

            System.out.println(e.getMessage());
        }
    }
}