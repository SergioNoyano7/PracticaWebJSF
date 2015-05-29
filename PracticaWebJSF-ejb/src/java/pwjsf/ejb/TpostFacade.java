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
    
     public List<Tpost> findListPostByIdUsuario(BigDecimal id){
        Query q;
        List<Tpost> listaPost;
        
        q = em.createQuery("SELECT p FROM Tpost p WHERE p.tusuarioIdUser.idUser = :ID");
        q.setParameter("ID", id);
        listaPost = (List<Tpost>)q.getResultList();
        
        if(listaPost.isEmpty()){
            listaPost=null;
        }
        
        return listaPost;
    }
     public void insertPostToGroup(List<Tusuario> usuariosGrupo, String post) {
        
       for(Tusuario u :usuariosGrupo){
           em.persist(u.insertPost(post));
       }
    }
    
}
