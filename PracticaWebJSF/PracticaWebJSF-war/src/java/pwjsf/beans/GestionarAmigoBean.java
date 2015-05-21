/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import pwjsf.ejb.TusuarioFacade;

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
    
}
