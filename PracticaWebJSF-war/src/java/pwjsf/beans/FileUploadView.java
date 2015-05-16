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
import javax.faces.context.FacesContext;
 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import pwjsf.ejb.TpostFacade;
import pwjsf.entity.Tpost;
/**
 *
 * @author Sergio
 */

@ManagedBean
public class FileUploadView {
    
    @ManagedProperty(value="#{postearBean}")
    private PostearBean postearBean;
    
    @EJB
    private TpostFacade fachadaPost;
     

    public PostearBean getPostearBean() {
        return postearBean;
    }

    public void setPostearBean(PostearBean postearBean) {
        this.postearBean = postearBean;
    }

    public TpostFacade getFachadaPost() {
        return fachadaPost;
    }

    public void setFachadaPost(TpostFacade fachadaPost) {
        this.fachadaPost = fachadaPost;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        //falta actualizar el post e insertarlo en la base de datos

        Tpost p = postearBean.getPost();
        System.out.println(p.getTexto());
        p.setImagen(event.getFile().getFileName());

        this.fachadaPost.edit(p);
    }
}
