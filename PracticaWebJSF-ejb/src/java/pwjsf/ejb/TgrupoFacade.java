/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwjsf.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pwjsf.entity.Tgrupo;

/**
 *
 * @author Sergio
 */
@Stateless
public class TgrupoFacade extends AbstractFacade<Tgrupo> {
    @PersistenceContext(unitName = "PracticaWebJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TgrupoFacade() {
        super(Tgrupo.class);
    }
    
}
