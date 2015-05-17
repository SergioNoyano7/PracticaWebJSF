/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.beans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    
    private List<Tusuario> lista;

    /**
     * Creates a new instance of PrincipalBean
     */
    public PrincipalBean() {
        
    }
    
    public String doPostear(){
        return "postearFormulario";
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
}
