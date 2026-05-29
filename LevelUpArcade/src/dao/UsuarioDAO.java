package dao;

import java.util.List;
import model.Usuario;

public interface UsuarioDAO {

    void insertar(Usuario u);

    List<Usuario> listar();

    void eliminar(int id);
}