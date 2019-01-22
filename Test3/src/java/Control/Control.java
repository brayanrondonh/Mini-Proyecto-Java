package Control;

    import java.io.*;
    import java.util.*;
    import java.sql.*;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.*;
    import javax.servlet.http.*;
    import dao.UsuarioDao;
    import dto.Usuario;
import jdbc.Conexion;
    import jdbc.ProcesosJDBC;

@WebServlet(name="Control",urlPatterns={"/Control"})
public class Control extends HttpServlet
{
    public void gestor(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        String accion = request.getParameter("accion");
        
        if(accion.equals("listado"))
        {
            this.confirmarUsuario(request, response);
        }
        else if(accion.equals("agregar"))
        {
            this.agregarUsuario(request, response);
        }
        else if(accion.equals("modificar"))
        {
            this.modificarUsuario(request,response);
        }
        else if(accion.equals("eliminar"))
        {
            this.eliminarUsuario(request,response);
        }
        else if(accion.equals("procesar"))
        {
            this.procesarUsuario(request,response);
        }
        else if(accion.equals("login"))
        {
            this.loginUsuario(request, response);
        }
        else if(accion.equals("salir"))
        {
            this.salir(request, response);
        }
    }
    
    public void listadoUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        HttpSession session = request.getSession();
        String usuarioLogin = (String)session.getAttribute("usuario");
        if(usuarioLogin!=null)
        {
            UsuarioDao usuarioDao = new ProcesosJDBC();
            try
            {
                List<Usuario> usuarios = usuarioDao.select();
                if(usuarios!=null&&usuarios.size()>0)
                {
                    request.setAttribute("listaUsuarios", usuarios);
                    request.getRequestDispatcher("WEB-INF/listados.jsp").forward(request, response);
                }
            }
            catch(SQLException sql)
            {
                System.out.println("Error al rescatar lista de la base de datos");
                sql.printStackTrace();
            }
        }
        else
        {
            System.out.print("no sirve la sesion");
            request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
        }
    }
    
    public void agregarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        request.getRequestDispatcher("WEB-INF/edicion.jsp").forward(request, response);
    }
    
    public void modificarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        String idUsuarioParam = request.getParameter("usuario");
        int idUsuario = 0;
        
        if(idUsuarioParam!=null && !idUsuarioParam.trim().equals(""))
        {    
            idUsuario = Integer.parseInt(idUsuarioParam);
            UsuarioDao usuarioDao = new ProcesosJDBC();
            try
            {
                Usuario usuarios = usuarioDao.consulta2(idUsuario);
                //List<Usuario> usuarios = usuarioDao.consulta(idUsuario);
                if(usuarios!=null)
                {
                    request.setAttribute("usuarios", usuarios);
                    request.getRequestDispatcher("WEB-INF/edicion.jsp").forward(request, response);
                }
            }
            catch(SQLException sqle)
            {
                System.out.println("Error en el controlador al tratar de consultar usuario");
                sqle.printStackTrace();
            }
        }
    }
    
    public void procesarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        String idParametro = request.getParameter("id");
        String usuarioParametro = request.getParameter("usuario");
        String passwordParametro = request.getParameter("password");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Id usuario: "+idParametro);
        out.println("Usuario: "+usuarioParametro);
        out.println("Password: "+passwordParametro);
        if(idParametro.trim().equals(""))
        {
            out.println("Ingreso por el insert");
            UsuarioDao usuarioDao = new ProcesosJDBC();
            try
            {
                boolean test = usuarioDao.agregarUsuario(usuarioParametro, passwordParametro);
                if(test)
                {
                    this.listadoUsuarios(request, response);
                }
            }
            catch(SQLException sqle)
            {
                System.out.println("Error al intentar modificar datos en el JDBC");
                sqle.printStackTrace();
            }
        }
        else if(!idParametro.trim().equals(""))
        {
            out.println("Ingreso por el update");
            int idUserPar = Integer.parseInt(idParametro);
            UsuarioDao usuarioDao = new ProcesosJDBC();
            try
            {
                boolean test = usuarioDao.modificarUsuario(idUserPar, usuarioParametro, passwordParametro);
                if(test)
                {
                    this.listadoUsuarios(request, response);
                }
            }
            catch(SQLException sqle)
            {
                System.out.println("Error al intentar insertar datos en el JDBC");
                sqle.printStackTrace();
            }
        }
    }
    
    public void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        String id = request.getParameter("usuario");
        int idNueva = Integer.parseInt(id);
        UsuarioDao usuarioDao = new ProcesosJDBC();
        try
        {
            boolean test = usuarioDao.eliminarUsuario(idNueva);
            if(test)
            {
                this.listadoUsuarios(request, response);
            }
        }
        catch(SQLException sqle)
        {
            System.out.println("Error al itentar eliminar usuario");
            sqle.printStackTrace();
        }
    }
    
    public void loginUsuario(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        UsuarioDao usaurioDao = new ProcesosJDBC();
        try
        {
            Usuario usuarios = usaurioDao.loginUsuario(usuario, password);
            if(usuarios!=null)
            {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuarios.getUsuario());
                System.out.println("Se cargo session? "+session.getAttribute(usuario));
                this.listadoUsuarios(request, response);
            }
            else
            {
                request.setAttribute("mensaje", "El usuario o contraseña son incorrectos");
                request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
            }
        }
        catch(SQLException sql)
        {
            System.out.println("Exploto al intentar login de usuario en el JDBC");
            sql.printStackTrace();
        }
        
    }
    
    public void confirmarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        System.out.println(usuario);
        if(usuario!=null)
        {
            this.listadoUsuarios(request, response);
        }
        else
        {
            System.out.print("Se activo en confirmar");
            request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
        }
    }
    
    public void salir(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        request.getSession().invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        this.gestor(request,response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        gestor(request,response);
    }
}
