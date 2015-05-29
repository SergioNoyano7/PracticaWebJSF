/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.beans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import pwjsf.ejb.TpostFacade;
import pwjsf.entity.Tpost;
import pwjsf.entity.Tusuario;

/**
 *
 * @author Sergio
 */
@ManagedBean
@SessionScoped
public class PostearBean {
    
    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginBean;
    
    @EJB
    private TpostFacade fachadaPost;
    
    private String texto;
    private int listado;
    private Tpost post;
    
    private List<Tpost> listaPost;
    
    /**
     * Creates a new instance of PostearBean
     */
    public PostearBean() {
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public TpostFacade getFachadaPost() {
        return fachadaPost;
    }

    public void setFachadaPost(TpostFacade fachadaPost) {
        this.fachadaPost = fachadaPost;
    }

    public Tpost getPost() {
        return post;
    }

    public void setPost(Tpost post) {
        this.post = post;
    }

    public List<Tpost> getListaPost() {
        return listaPost;
    }

    public void setListaPost(List<Tpost> listaPost) {
        this.listaPost = listaPost;
    }

    public int getListado() {
        listado++;
        return listado;
    }

    public void setListado(int listado) {
        this.listado = listado;
    }

    public String doPostear(){
        Tusuario user = loginBean.user;
        String paginaRedireccionada;
        if(user != null){
            post = new Tpost();

            post.setTexto(texto);
            post.setTusuarioIdUser(user);

            this.fachadaPost.insertarPost(post);

            paginaRedireccionada = "preguntaImagen";
            
        }else{
            paginaRedireccionada = "control";
        }
        return paginaRedireccionada;
    }
    
    public String doListaPost(){
        Tusuario user = loginBean.user;
        String siguientePag;
        if(user != null){
            listaPost = fachadaPost.findListPostByIdUsuario(user.getIdUser());
            
            siguientePag = "listaPost";
            
        }else{
            siguientePag = "control";
        }
        
        return siguientePag;
    }
    
    public String doMostrarImagen(Tpost p){
        setPost(p);
        return "mostrarImagen";
    }
    
    public String doPostearGrupo(){
        String mensaje = null;
        String redireccionamiento = null;
        
        if(texto.equals("")){
            mensaje = "Rellene el campo de texto";
        }else{
            try{
                List<Tusuario> usuariosGrupo = loginBean.user.getTgrupoId().getTusuarioList();
                fachadaPost.insertPostToGroup(usuariosGrupo, texto);
                redireccionamiento = "principal"; 
            }catch(NullPointerException e){
                mensaje = "El usuairo no está en ningún grupo";
                redireccionamiento = null;
            }
        }
        FacesMessage errorMessage = new FacesMessage(mensaje);
        errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
        
        return redireccionamiento;
    }
}
