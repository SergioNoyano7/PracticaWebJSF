package pwjsf.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import pwjsf.ejb.TusuarioFacade;
import pwjsf.entity.Tusuario;

/**
 *
 * @author alumno
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    @EJB
    private TusuarioFacade tusuarioFacade;

    private String username;
    private String password;
    
    Tusuario user;
    
    /*
    Fran:
    Añado aqui dos nuevas propiedades:
    - char gestionar: esta propiedad me sirve para diferenciar si lo que 
                        quería el usuario era borrar o añadir un amigo.
    - List<Tusuario> listaAmigos: Esta propiedad almaceza los amigos del usuario
                        que se ha logueado. 
    */
    private char gestionar;
    private List<Tusuario> listaUsuario;
    
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Tusuario getUser() {
        return user;
    }

    public void setUser(Tusuario user) {
        this.user = user;
    }

    public char getGestionar() {
        return gestionar;
    }

    public void setGestionar(char gestionar) {
        this.gestionar = gestionar;
    }

    public List<Tusuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Tusuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    public String doEnviar (){
        
        if (username == null || username.equalsIgnoreCase("") || password ==null || password.equalsIgnoreCase("") ){
        return "control" ;    
        } else{
            user= this.tusuarioFacade.findByNameAndPass(username, password);
            
            if (user.getBloqueado().equals('1')){
                return "estasBloqueado";
            } else {
                
                if (user!=null) {
                    /*
                    Aquí tengo que inicialiizar la variable listaAmigos
                    Copiar del otro proyecto en el servlet ListaAmigosServlet
                    */
                    return "principal";
                } else return "control";
            }
                
       }
        
}
    
    
    
    }
    

