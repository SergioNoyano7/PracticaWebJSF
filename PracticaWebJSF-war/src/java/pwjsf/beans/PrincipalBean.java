/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.beans;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import pwjsf.ejb.TusuarioFacade;
import pwjsf.entity.Tusuario;

/**
 *
 * @author Sergio
 */
@ManagedBean
@SessionScoped
public class PrincipalBean {
    
    @EJB
    private TusuarioFacade fachada;
    
    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginBean;
    
    private List<Tusuario> lista;
    private long usuarioSel = 0;
    private Tusuario user;

    /**
     * Creates a new instance of PrincipalBean
     */
    public PrincipalBean() {
        
    }
    
    public String doPostear(){
        return "postearFormulario";
    }
    
    public String doGrupos(){
        return "menuGrupos";
    }
    
    public String doUnirseGrupo(){
        loginBean.setGestionarGrupos('0');
        return "GestionGrupo";
    }
    
    public String doCrearGrupo(){
        loginBean.setGestionarGrupos('1');
        return "GestionGrupo";
    }
    
    public TusuarioFacade getFachada() {
        return fachada;
    }

    public void setFachada(TusuarioFacade fachada) {
        this.fachada = fachada;
    }

    public List<Tusuario> getLista() {
        return lista;
    }

    public void setLista(List<Tusuario> lista) {
        this.lista = lista;
    }
    
    public String doListaUsuarios(){
        lista = this.fachada.findAll();
        return "listaUsuariosRed";
    }

    public long getUsuarioSel() {
        return usuarioSel;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    public void setUsuarioSel(long usuarioSel) {
        this.usuarioSel = usuarioSel;
    }
     public String doBloquearUser () {
        user= fachada.findById(String.valueOf(usuarioSel));
        int pos = lista.indexOf(user);
        user.setBloqueado('1');
        fachada.bloquear(user);
        usuarioSel= 0;
        lista.set(pos, user);
        return "listaUsuariosRed";
    }
     public String doDesbloquearUser () {
        user= fachada.findById(String.valueOf(usuarioSel));
        int pos = lista.indexOf(user);
        user.setBloqueado('0');
        fachada.bloquear(user);
        usuarioSel= 0;
        lista.set(pos, user);
        return "listaUsuariosRed";
    }
     
    public String doAnadirAmigo(){
        loginBean.setGestionar('a');
        return "gestionAmigo";
    }
    
    public String doEliminarAmigo(){
        loginBean.setGestionar('d');
        return "gestionAmigo";
    }
    
    
    
    public String doSalirSesion(){
        loginBean.setUser(null);
        return "login";
    }
    
    @PostConstruct
    public void ComprobarUser(){
        if(loginBean.getUser()==(null)){
            try {
                FacesContext.getCurrentInstance().getExternalContext().dispatch("control.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(GruposBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
}
