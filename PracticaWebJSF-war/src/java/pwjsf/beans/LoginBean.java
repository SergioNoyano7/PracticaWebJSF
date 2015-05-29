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
    Las variables de abajo se van a utilizar en la gestion de los Amigos
    - char gestionar indicará si se va a añadir o borrar un amigo. Si vale a
    indica que se va a añadir un amigo; si vale d, borrar
    - List<Tusuario> listaAmigos que contendrá la lista de amigos del usuario logueado
    */
    private char gestionar;
    private char gestionarGrupos;
    private List<Tusuario> listaAmigos;
    
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

    public List<Tusuario> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(List<Tusuario> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    public char getGestionarGrupos() {
        return gestionarGrupos;
    }

    public void setGestionarGrupos(char gestionarGrupos) {
        this.gestionarGrupos = gestionarGrupos;
    }
    
    public String doEnviar (){
        
        if (username == null || username.equalsIgnoreCase("") || password ==null || password.equalsIgnoreCase("") ){
        return "control" ;    
        } else{
            user= this.tusuarioFacade.findByNameAndPass(username, password);
            if (user!=null) {
               if (user.getBloqueado().equals('1')){
                return "estasBloqueado";
               }
               List<Tusuario> lista1 = user.getTusuarioList();
               List<Tusuario> lista2 = user.getTusuarioList1();
               for(Tusuario u: lista2){
                   if(!lista1.contains(u)){
                       lista1.add(u);
                   }
               }
               listaAmigos=lista1;
              return "principal"; 
            } else return "control";
            }
                
       }
        
}
    
    
    
    
    

