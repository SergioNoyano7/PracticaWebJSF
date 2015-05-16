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
import javax.faces.bean.SessionScoped;
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
    
    private Tpost post;
    
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
    
    public String doPostear(){
        Tusuario user = loginBean.user;
        String paginaRedireccionada;
        System.out.println(user.getNombre());
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
}
