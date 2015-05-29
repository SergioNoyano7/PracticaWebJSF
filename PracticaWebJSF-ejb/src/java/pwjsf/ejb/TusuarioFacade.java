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
    public Tusuario findById (String id) {
        Query q;
        List<Tusuario> lista;
        Tusuario u;
        
        q = em.createQuery("SELECT u FROM Tusuario u WHERE u.idUser = :ID");
        q.setParameter("ID", BigDecimal.valueOf(Integer.parseInt(id)));
        
        lista =(List<Tusuario>)q.getResultList();
        
        if(lista.isEmpty()){
            u = null;
        }else{
            u = (Tusuario)lista.get(0);
        }
        
        return u;
    }
     
    public void bloquear (Tusuario usuario) {
        
            
        em.merge(usuario);
        
        
    }
    
    public Tusuario findByName(String nombre) {
        Query q;
        List<Tusuario> lu ;
        Tusuario u;
        
        q = em.createQuery("SELECT u FROM Tusuario u WHERE u.nombre = :NOM");
        q.setParameter("NOM", nombre);
        
        lu=(List<Tusuario>)q.getResultList();
        if(lu.isEmpty()){
            u=null;
        }else{
            u=lu.get(0);
        }
        
        return u;
    }
    
    public boolean insertarAmigoByNombre(Tusuario usuario, Tusuario nuevoAmigo) {
        boolean ok = false;
        if(usuario.anadirAmigo(nuevoAmigo)){
            nuevoAmigo.anadirAmigo(usuario);
            em.merge(usuario);
            em.merge(nuevoAmigo);
            ok=true;
        }
        return ok;
    }
    
        public void añadirGrupoAUsuario(Tgrupo g, Tusuario u){
        
        u.setTgrupoId(g);
        List<Tusuario> lista = g.getTusuarioList();
        lista.add(u);
        g.setTusuarioList(lista);
        
        edit(u);
    }
        
    public boolean deleteFriendByName(Tusuario usuario, Tusuario eliminando) {
        boolean eliminado = false;
        if(usuario.removeFriend(eliminando) && eliminando.removeFriend(usuario)){
            em.merge(usuario);
            em.merge(eliminando);
            eliminado = true;
        }
        return eliminado;
    }
    
    public void abandonarGrupo(Tusuario u){
        u.setTgrupoId(null);
        edit(u);

    }
    
}
