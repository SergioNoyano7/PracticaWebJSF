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
import pwjsf.ejb.TpostFacade;
import pwjsf.entity.Tpost;
import pwjsf.entity.Tusuario;

/**
 *
 * @author Sergio
 */
@ManagedBean
@RequestScoped
public class PostearBean {

    @EJB
    private TpostFacade fachadaPost;
    
    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginBean;
    
    private String post;
    
    /**
     * Creates a new instance of PostearBean
     */
    public PostearBean() {
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    public String doPostear(){
        Tusuario user = loginBean.user;
        String paginaRedireccionada;
        System.out.println(user.getNombre());
        if(user != null){
            Tpost p = new Tpost();

            p.setTexto(post);
            p.setTusuarioIdUser(user);

            this.fachadaPost.insertarPost(p);

            paginaRedireccionada = "preguntaImagen";
        }else{
            paginaRedireccionada = "control";
        }
        return paginaRedireccionada;
    }
}
