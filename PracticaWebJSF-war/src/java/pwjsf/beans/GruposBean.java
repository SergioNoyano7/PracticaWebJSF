/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.beans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import pwjsf.ejb.TgrupoFacade;
import pwjsf.ejb.TusuarioFacade;
import pwjsf.entity.Tgrupo;
import pwjsf.entity.Tusuario;

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
    
    
    private String nombreGrupo;
    private Tgrupo grupo;
    private List<Tgrupo> listaGrupo;
    
    
    
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

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public List<Tgrupo> getListaGrupo() {
        List<Tgrupo> listaGrupos = tgrupoFacade.findAll();
        return listaGrupos;
    }

    public void setListaGrupo(List<Tgrupo> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }
    
    public String doAbandondarGrupo(){
        String paginaSiguiente="menuGrupos";
        Tusuario user = loginBean.getUser();
        Tgrupo grupo=user.getTgrupoId();
        tusuarioFacade.abandonarGrupo(user);
        if(grupo.getTusuarioList().size()==1 || grupo.getTusuarioList().size()==0){
            tgrupoFacade.eliminarGrupo(grupo);
        }
        
        return paginaSiguiente;
    }
    
    public String doUnirseGrupo(){
        String paginaSiguiente="menuGrupos";
        Tusuario user = loginBean.getUser();
        
        Tgrupo g = tgrupoFacade.findByName(nombreGrupo);
        
        tusuarioFacade.añadirGrupoAUsuario(g, user);
        
        return paginaSiguiente;
    }
    
    public String doCrearGrupo(){
        String paginaSiguiente="menuGrupos";
        Tusuario usuario= loginBean.getUser();
        Tgrupo g = new Tgrupo();
        g.setNombre(nombreGrupo);
        
        tgrupoFacade.insertarGrupoByName(g);

        tusuarioFacade.añadirGrupoAUsuario(g, usuario);
                
        return paginaSiguiente;

    }
    
   
    
    
}
