/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.beans;

import static com.sun.faces.facelets.util.Path.context;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import pwjsf.ejb.TpostFacade;
import pwjsf.entity.Tpost;
import pwjsf.entity.Tusuario;
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
        
        InputStream input = null;
        UploadedFile uploadedFile = event.getFile();
        try {
            String filePath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("file-upload");
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            
            //guardando el archivo en el glassfish
            input = uploadedFile.getInputstream();

            String filename = FilenameUtils.getBaseName(uploadedFile.getFileName()); 
            
            String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
            Path folder = Paths.get(filePath + filename + "." + extension);
            
            Path file = Files.createFile(folder);
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Uploaded file successfully saved in " + file);
            
            //guardando el nombre del fichero en la base de datos
            Tpost p = postearBean.getPost();
            p.setImagen(file.getFileName().toString());
            this.fachadaPost.edit(p);
        } catch(FileAlreadyExistsException exc){
            //si la imagen ya existe en la carpeta destino, se guarda el nombre del fichero en la base de datos
            Tpost p = postearBean.getPost();
            p.setImagen(event.getFile().getFileName());
            this.fachadaPost.edit(p);
        } catch (IOException ex) {
            Logger.getLogger(FileUploadView.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(FileUploadView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
