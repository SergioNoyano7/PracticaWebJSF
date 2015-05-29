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
    
    public Tgrupo findById(BigDecimal id){
        Query q;
        List<Tgrupo> listaGrupo ;
        Tgrupo g;
        
        q = em.createQuery("SELECT g FROM Tgrupo g WHERE g.id = :ID");
        q.setParameter("ID", id);
        
        listaGrupo=(List<Tgrupo>)q.getResultList();
        
        
            g=listaGrupo.get(0);
        
        
        return g;
    }
    
    public Tgrupo findByName(String name){
        Query q;
        List<Tgrupo> listaGrupo ;
        Tgrupo g;
        
        q = em.createQuery("SELECT g FROM Tgrupo g WHERE g.nombre = :NAME");
        q.setParameter("NAME", name);
        
        listaGrupo=(List<Tgrupo>)q.getResultList();
        
        
            g=listaGrupo.get(0);
        
        
        return g;
    }
    
    public void insertarGrupoByName(Tgrupo grupo){
        em.persist(grupo);
    }
    
    public List<Tgrupo> findAll(){
        Query q;
        List<Tgrupo> listaGrupo ;
 
        q = em.createQuery("SELECT g FROM Tgrupo g");
        
        listaGrupo=(List<Tgrupo>)q.getResultList();
        
        return listaGrupo;
    }

    public void eliminarGrupo(Tgrupo grupo) {
        Tgrupo g = find(grupo.getId());
        remove(g);
    }
    
}
