package dto;

public class Usuario
{
    private int id_usuario;
    private int id_persona;
    private String usuario;
    private String password;
    
    public Usuario()
    {
        
    }
    
    public int getId_usuario()
    {
        return id_usuario;
    }
    
    public void setId_usuario(int id_usuario)
    {
        this.id_usuario=id_usuario;
    }
    
    public int getId_persona()
    {
        return id_persona;
    }
    
    public void setId_persona(int id_persona)
    {
        this.id_persona=id_persona;
    }
    
    public String getUsuario()
    {
        return usuario;
    }
    
    public void setUsuario(String usuario)
    {
        this.usuario=usuario;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password=password;
    }
}
