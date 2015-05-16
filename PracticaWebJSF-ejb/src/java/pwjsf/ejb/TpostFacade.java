/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.ejb;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pwjsf.entity.Tpost;
import pwjsf.entity.Tusuario;

/**
 *
 * @author Sergio
 */
@Stateless
public class TpostFacade extends AbstractFacade<Tpost> {
    @PersistenceContext(unitName = "PracticaWebJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TpostFacade() {
        super(Tpost.class);
    }
    
    public void insertarPost(Tpost p){
       em.persist(p);
    }
    
}
