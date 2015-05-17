/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.beans;

import static com.sun.faces.facelets.util.Path.context;
import java.io.File;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
 
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
    
    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginBean;
    
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
        
        String filePath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("file-upload") + event.getFile().getFileName();
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        //falta guardar el archivo en el glassfish
        

        Tpost p = postearBean.getPost();
        System.out.println(filePath );
        p.setImagen(filePath);

        this.fachadaPost.edit(p);
    }
    
//    public void handleFileUpload(FileUploadEvent event){
//        boolean isMultipart;
//        String filePath;
//        int maxFileSize = 50 * 1024;
//        int maxMemSize = 4 * 1024;
//        File file;
//        Tusuario user = loginBean.user;
//        
//        if(user!=null){
//            // Get the file location where it would be stored.
//            filePath = 
//                 FacesContext.getCurrentInstance().getExternalContext().getInitParameter("file-upload");
//            // Check that we have a file upload request
//          isMultipart = ServletFileUpload.isMultipartContent(request);
//
//          if( !isMultipart ){
//             RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("errorImagen.jsp");
//             dispatcher.forward(request, response);
//          }else{
//             DiskFileItemFactory factory = new DiskFileItemFactory();
//                // maximum size that will be stored in memory
//             factory.setSizeThreshold(maxMemSize);
//            // Location to save data that is larger than maxMemSize.
//             factory.setRepository(new File("/Users/Sergio/NetBeansProjects/"));
//
//            // Create a new file upload handler
//             ServletFileUpload upload = new ServletFileUpload(factory);
//            // maximum file size to be uploaded.
//             upload.setSizeMax( maxFileSize );
//
//             try {
//                List fileItems = upload.parseRequest(request);
//                if(fileItems != null){
//                   FileItem fi;
//                    fi = (FileItem)fileItems.get(0);
//                   if ( !fi.isFormField () )	
//                   {
//                        // Get the uploaded file parameters
//                        String fieldName = fi.getFieldName();
//                        String fileName = fi.getName();
//                        String contentType = fi.getContentType();
//                        boolean isInMemory = fi.isInMemory();
//                        long sizeInBytes = fi.getSize();
//                        // Write the file
//                        if( fileName.lastIndexOf("\\") >= 0 ){
//                            file = new File( filePath +
//                                    fileName.substring( fileName.lastIndexOf("\\"))) ;
//                        }else{
//                            file = new File( filePath +
//                                    fileName.substring(fileName.lastIndexOf("\\")+1)) ;
//                        }
//                        fi.write( file );
//                   }
//                }
//            } catch (Exception ex) {
//                System.out.println(ex);
//             }
//            System.out.println("Uploaded Filename: " + filePath + file.getName());
//
//              //hacer el update aqui para la BD
//            Tpost p = (Tpost)session.getAttribute("post");
//            //p.setImagen(filePath + file.getName());
//            //this.fachadaPost.edit(p);
//            this.fachadaPost.actualizarImagenPost(p, filePath + file.getName() );
//    }
}
