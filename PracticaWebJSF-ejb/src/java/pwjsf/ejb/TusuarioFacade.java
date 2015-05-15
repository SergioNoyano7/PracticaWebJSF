/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pwjsf.entity.Tusuario;

/**
 *
 * @author Sergio
 */
@Stateless
public class TusuarioFacade extends AbstractFacade<Tusuario> {
    @PersistenceContext(unitName = "PracticaWebJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TusuarioFacade() {
        super(Tusuario.class);
    }
    
    public Tusuario findByNameAndPass(String name,String pass){
        Query q;
        List<Tusuario> lista;
        Tusuario user;
        
        System.out.println(name + " " + pass);
        
        q = em.createQuery("SELECT u FROM Tusuario u WHERE u.nombre = :NAME AND u.password = :PASS");
        q.setParameter("NAME", name);
        q.setParameter("PASS", pass);
       
        lista= (List<Tusuario>)q.getResultList();
        if(!lista.isEmpty()){
            user = lista.get(0);
        }else{
            user = null;
        }
        return user;
        
    }
    
}
