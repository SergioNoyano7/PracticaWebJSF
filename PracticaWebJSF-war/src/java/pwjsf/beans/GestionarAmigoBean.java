/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import pwjsf.ejb.TusuarioFacade;
import pwjsf.entity.Tusuario;

/**
 *
 * @author Francisco
 */
@ManagedBean
@RequestScoped
public class GestionarAmigoBean {
    @EJB
    private TusuarioFacade tusuarioFacade;
    
    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginBean;
    
    private String nombreAmigoGestionado;
    private Tusuario usuarioGestionado;
    
    /**
     * Creates a new instance of GestionarAmigoBean
     */
    public GestionarAmigoBean() {
    }

    public String getNombreAmigoGestionado() {
        return nombreAmigoGestionado;
    }

    public void setNombreAmigoGestionado(String nombreAmigoGestionado) {
        this.nombreAmigoGestionado = nombreAmigoGestionado;
    }
    
    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    public String doAnadir(){
        String mensaje = null;
        String redireccionamiento = "gestionOK";
        if(nombreAmigoGestionado.equals("")){
            mensaje = "Introduzca el nombre del usuario";
            redireccionamiento = null;
        }else{
            this.usuarioGestionado = tusuarioFacade.findByName(nombreAmigoGestionado);
            if(usuarioGestionado==null){
                mensaje = "El usuario no existe";
                redireccionamiento = null;
            }else{
                if(!tusuarioFacade.insertarAmigoByNombre(loginBean.user, usuarioGestionado)){
                    mensaje = "Este usuario ya es tu amigo";
                    redireccionamiento = null;
                }
            }
        }
        FacesMessage errorMessage = new FacesMessage(mensaje);
        errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
        return null;
    }
    
    public String doEliminar(){
       
        String mensaje = null;
        if(nombreAmigoGestionado.equals("")){
            mensaje = "Introduzca el nombre del usuario";
            
        }else{
            this.usuarioGestionado = tusuarioFacade.findByName(nombreAmigoGestionado);
            if(usuarioGestionado==null){
                mensaje = "El usuario no existe";
                
            }else{
                if(!tusuarioFacade.deleteFriendByName(loginBean.user, usuarioGestionado)){
                    mensaje = "Este usuario No es tu amigo";
                    
                }
            }
        }
        FacesMessage errorMessage = new FacesMessage(mensaje);
        errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
        
        return null;
    }
}
