package jdbc;

    import java.sql.*;
    import java.util.*;
    import dao.UsuarioDao;
    import dto.Usuario;
    import jdbc.Conexion;

public class ProcesosJDBC implements UsuarioDao
{
    private Connection userConn=null;
    private String SQL_LISTA_USUARIO = "select * from usuario";
    private String SQL_INSERT_USUARIO = "insert into usuario (usuario,password) values (?,?)";
    private String SQL_CONSULTAR_USUARIO = "select * from usuario where id_usuario=?";
    private String SQL_MODIFICAR_USUARIO = "update usuario set usuario=?, password=? where id_usuario=?";
    private String SQL_ELIMINAR_USUARIO = "delete from usuario where id_usuario=?";
    private String SQL_LOGIN_USUARIO = "select * from usuario where usuario=? and password=?";
    
    public ProcesosJDBC()
    {
        
    }
    
    public ProcesosJDBC(Connection conn)
    {
        this.userConn=conn;
    }
    
    public boolean agregarUsuario(String usuario,String password) throws SQLException
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        boolean exito=false;
        int rows=0;
        
        try
        {
            conn = (this.userConn!=null)?this.userConn : Conexion.getConnection();
            pstmt = conn.prepareStatement(SQL_INSERT_USUARIO);
            int index=1;
            pstmt.setString(index++, usuario);
            pstmt.setString(index++, password);
            rows = pstmt.executeUpdate();
            System.out.println("Se ejecuto inset");
            System.out.println("Cantidad de filas afectadas: "+rows);
            exito=true;
        }
        catch(SQLException sqle)
        {
            System.out.println("Exploto el insertar usuario");
            sqle.printStackTrace();
        }
        finally
        {
            Conexion.close(pstmt);
            if(userConn==null)
            {
                Conexion.close(conn);
            }
        }
        return exito;
    }
    
    public List<Usuario> select() throws SQLException
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario>usuarios = new ArrayList<Usuario>();
        
        try
        {
            conn = (this.userConn!=null) ? this.userConn : Conexion.getConnection();
            pstmt = conn.prepareStatement(SQL_LISTA_USUARIO);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                int idTemp = rs.getInt(1);
                String userTemp = rs.getString(2);
                String passTemp = rs.getString(3);
                usuario = new Usuario();
                usuario.setId_usuario(idTemp);
                usuario.setUsuario(userTemp);
                usuario.setPassword(passTemp);
                usuarios.add(usuario);
                System.out.println("Se ejecuto selec List");
            }
        }
        catch(SQLException sql)
        {
            System.out.print("Exploto en jdbc en lista usuario");
            sql.printStackTrace();
        }
        finally
        {
            Conexion.close(rs);
            Conexion.close(pstmt);
            if(userConn==null)
            {
                Conexion.close(conn);
            }
        }
        return usuarios;
    }
    
    public Usuario consulta2(int idUsuario) throws SQLException
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
    
        try
        {
            conn = (this.userConn!=null) ? this.userConn : Conexion.getConnection();
            pstmt = conn.prepareStatement(SQL_CONSULTAR_USUARIO);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                int idTem = rs.getInt(1);
                String userTemp = rs.getString(2);
                String passTemp = rs.getString(3);
                usuario = new Usuario();
                usuario.setId_usuario(idTem);
                usuario.setUsuario(userTemp);
                usuario.setPassword(passTemp);
                System.out.println("Se ejecuto consulta usuario");
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("Exploto la consulta del usuario2");
            sqle.printStackTrace();
        }
        finally
        {
            Conexion.close(rs);
            Conexion.close(pstmt);
            if(userConn==null)
            {
                Conexion.close(conn);
            }
        }
        return usuario;
    }
    
    public boolean modificarUsuario(int id_usuario,String usuario,String password) throws SQLException
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        boolean exito=false;
        int row=0;
        
        try
        {
            conn = (this.userConn!=null) ? this.userConn : Conexion.getConnection();
            pstmt = conn.prepareStatement(SQL_MODIFICAR_USUARIO);
            int index=1;
            pstmt.setString(index++, usuario);
            pstmt.setString(index++, password);
            pstmt.setInt(index, id_usuario);
            row = pstmt.executeUpdate();
            System.out.println("Se ejecuto modificacion");
            System.out.println("Filas afectadas "+row);
            exito=true;
        }
        catch(SQLException sqle)
        {
            System.out.println("Error al modificar usuario en el JDBC");
            sqle.printStackTrace();
        }
        finally
        {
            Conexion.close(pstmt);
            if(userConn==null)
            {
                Conexion.close(conn);
            }
        }
        return exito;
    }
    
    public boolean eliminarUsuario(int idUsuario)throws SQLException
    {
        Connection conn = null;
        PreparedStatement pstmt=null;
        boolean exito=false;
        int row=0;
        
        try
        {
            conn = (this.userConn!=null) ? this.userConn : Conexion.getConnection();
            pstmt = conn.prepareStatement(SQL_ELIMINAR_USUARIO);
            pstmt.setInt(1, idUsuario);
            row = pstmt.executeUpdate();
            System.out.println("Se ejecuto el delete usuario");
            System.out.println("Filas afectadas: "+row);
            exito=true;
        }
        catch(SQLException sqle)
        {
            System.out.println("Error al elimina usuario en el jdbc");
            sqle.printStackTrace();
        }
        finally
        {
            Conexion.close(pstmt);
            if(userConn==null)
            {
                Conexion.close(conn);
            }
        }
        return exito;
    }
    
    public Usuario loginUsuario(String usuario,String password) throws SQLException
    {
        Connection conn = null;
        PreparedStatement pstmt=null;
        ResultSet rs = null;
        Usuario usuarios = null;
        
        try
        {
            conn = (this.userConn!=null) ? this.userConn : Conexion.getConnection();
            pstmt = conn.prepareStatement(SQL_LOGIN_USUARIO);
            pstmt.setString(1, usuario);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                String userTemp = rs.getString(1);
                String passTemp = rs.getString(2);
                usuarios = new Usuario();
                usuarios.setUsuario(usuario);
                usuarios.setPassword(password);
                System.out.println("Se ejecuto el login");
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("Error al realizar login en el JDBC");
            sqle.printStackTrace();
        }
        finally
        {
            Conexion.close(pstmt);
            if(userConn==null)
            {
                Conexion.close(conn);
            }
        }
        return usuarios;
    }
}