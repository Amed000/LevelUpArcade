package dao;

import java.sql.*;
import java.util.*;
import model.Pedido;

public class PedidoDAOImpl implements PedidoDAO {

    Connection conn = DatabaseConnection.getConnection();

    public void insertar(Pedido p) {

        String sql = "INSERT INTO pedido(fecha,estado,id_cliente,id_usuario) VALUES (?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(p.getFecha().getTime()));
            ps.setString(2, p.getEstado());
            ps.setInt(3, p.getIdCliente());
            ps.setInt(4, p.getIdUsuario());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Pedido> listar() {
        return new ArrayList<>();
    }
}