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

    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginBean;
    
    @EJB
    private TpostFacade fachadaPost;
    
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
        String devolver;
        
        if(user != null){
            List<Tpost> listaPost = this.fachadaPost.findListPostByIdUsuario(user.getIdUser());
            Tpost p = new Tpost();

            p.setTexto(post);
            p.setTusuarioIdUser(user);

            this.fachadaPost.insertarPostByUsuario(user, listaPost, post);

            devolver = "preguntaImagen";
        }else{
            devolver = "control";
        }
        return devolver;
    }
}
