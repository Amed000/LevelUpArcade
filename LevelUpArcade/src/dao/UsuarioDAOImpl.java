package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

    Connection conn = DatabaseConnection.getConnection();

    /**
     * Inserta un nuevo usuario en la base de datos.
     */
    @Override
    public void insertar(Usuario u) {

        String sql = "INSERT INTO usuario(username,password,rol) VALUES (?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRol());

            ps.executeUpdate();

            System.out.println("Usuario insertado correctamente");

        } catch (Exception e) {
            System.out.println("Error insertando usuario: " + e.getMessage());
        }
    }

    /**
     * Lista todos los usuarios.
     */
    @Override
    public List<Usuario> listar() {

        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuario";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRol(rs.getString("rol"));

                lista.add(u);
            }

        } catch (Exception e) {
            System.out.println("Error listando usuarios: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Elimina un usuario por ID.
     */
    @Override
    public void eliminar(int id) {

        String sql = "DELETE FROM usuario WHERE id_usuario=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario eliminado correctamente");
            } else {
                System.out.println("No existe usuario con ID: " + id);
            }

        } catch (Exception e) {
            System.out.println("Error eliminando usuario: " + e.getMessage());
        }
    }

    // =========================
    // LOGIN (FASE 7)
    // =========================

    /**
     * Valida login de usuario.
     *
     * @param username nombre de usuario
     * @param password contraseña en texto plano
     * @return Usuario si es válido, null si no existe
     */
    public Usuario login(String username, String password) {

        String sql = "SELECT * FROM usuario WHERE username=? AND password=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Usuario u = new Usuario();

                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRol(rs.getString("rol"));

                return u;
            }

        } catch (Exception e) {
            System.out.println("Error en login: " + e.getMessage());
        }

        return null;
    }
}