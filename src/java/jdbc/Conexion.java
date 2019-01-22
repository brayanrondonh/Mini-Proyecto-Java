package jdbc;
    import java.sql.*;

public class Conexion
{
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false";
    private static String JDBC_USER = "root";
    private static String JDBC_PASS = "";
    private static Driver driver=null;
    
    public static synchronized Connection getConnection() throws SQLException
    {
        try
        {
            if(driver==null)
            {
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error el cargar Driver");
            e.printStackTrace();
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }
    
    public static void close(ResultSet rs)
    {
        try
        {
            if(rs==null)
            {
                rs.close();
            }
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
            System.out.print("Error en el close ResultSet");
        }
    }
    
    public static void close(PreparedStatement stmt)
    {
        try
        {
            if(stmt==null)
            {
                stmt.close();
            }
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
            System.out.print("Error en el close PreparedStatement");
        }
    }
    
    public static void close(Connection conn)
    {
        try
        {
            if(conn==null)
            {
                conn.close();
            }
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
            System.out.print("Error en el close Connection");
        }
    }
}
