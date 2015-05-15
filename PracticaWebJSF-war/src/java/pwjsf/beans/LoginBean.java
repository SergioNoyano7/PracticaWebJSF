package pwjsf.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public String doEnviar (){
        
        user= this.tusuarioFacade.findByNameAndPass(username, password);
        return "principal";
    }
    
    
    
    }
    

