package dao;

import model.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl {

    Connection conn = DatabaseConnection.getConnection();

    public void insertar(Pedido p) {

        String sql = "INSERT INTO pedido(fecha, estado) VALUES (?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(p.getFecha().getTime()));
            ps.setString(2, p.getEstado());

            ps.executeUpdate();

            System.out.println("✔ Pedido insertado");

        } catch (Exception e) {
            System.out.println("Error insertando pedido: " + e.getMessage());
        }
    }

    public List<Pedido> listar() {

        List<Pedido> lista = new ArrayList<>();

        String sql = "SELECT * FROM pedido";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Pedido p = new Pedido();

                p.setIdPedido(rs.getInt("id_pedido"));
                p.setFecha(rs.getDate("fecha"));
                p.setEstado(rs.getString("estado"));

                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error listando pedidos: " + e.getMessage());
        }

        return lista;
    }

    public void eliminar(int id) {

        String sql = "DELETE FROM pedido WHERE id_pedido=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminando pedido: " + e.getMessage());
        }
    }
}