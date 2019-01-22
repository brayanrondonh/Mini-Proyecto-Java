package dao;
import dto.Usuario;
import java.sql.*;
import java.util.List;

public interface UsuarioDao
{
    public List<Usuario> select() throws SQLException;
    public boolean agregarUsuario(String usuario,String password) throws SQLException;
    public Usuario consulta2(int idUsuario)throws SQLException;
    public boolean modificarUsuario(int id_usuario,String usuario,String password)throws SQLException;
    public boolean eliminarUsuario(int idUsuario) throws SQLException;
    public Usuario loginUsuario(String usuario,String password)throws SQLException;
}
