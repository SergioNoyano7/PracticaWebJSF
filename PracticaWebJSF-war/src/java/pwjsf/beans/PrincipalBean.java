/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Sergio
 */
@ManagedBean
@RequestScoped
public class PrincipalBean {

    /**
     * Creates a new instance of PrincipalBean
     */
    public PrincipalBean() {
        
    }
    
    public String doPostear(){
        return "postearFormulario";
    }
    
}
