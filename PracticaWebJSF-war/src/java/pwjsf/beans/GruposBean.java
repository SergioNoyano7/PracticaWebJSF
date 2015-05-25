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
import pwjsf.ejb.TgrupoFacade;
import pwjsf.ejb.TusuarioFacade;
import pwjsf.entity.Tgrupo;

/**
 *
 * @author Ignacio
 */
@ManagedBean
@RequestScoped
public class GruposBean {
    @EJB
    private TusuarioFacade tusuarioFacade;
    @EJB
    private TgrupoFacade tgrupoFacade;

    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginBean;
    
    
    
    private Tgrupo grupo;
    
    
    /**
     * Creates a new instance of GruposBean
     */
    public GruposBean() {
    }

    public TusuarioFacade getTusuarioFacade() {
        return tusuarioFacade;
    }

    public void setTusuarioFacade(TusuarioFacade tusuarioFacade) {
        this.tusuarioFacade = tusuarioFacade;
    }

    public TgrupoFacade getTgrupoFacade() {
        return tgrupoFacade;
    }

    public void setTgrupoFacade(TgrupoFacade tgrupoFacade) {
        this.tgrupoFacade = tgrupoFacade;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Tgrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Tgrupo grupo) {
        this.grupo = grupo;
    }
    
    
    
}
