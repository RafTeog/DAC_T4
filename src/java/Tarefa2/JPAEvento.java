package Tarefa2;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class JPAEvento {
    
    private EntityManager em;
    
    public JPAEvento() {
    }
        
    public void salva(EventoEntity e) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(e);
        et.commit();
        em.close();
    }
    
     public void exclui(Long id) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        EventoEntity e = em.find(EventoEntity.class, id);
        em.remove(e);
        et.commit();
        em.close();
    }
    
    public EventoEntity recupera(Long id) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        EventoEntity e = em.find(EventoEntity.class, id);
        et.commit();
        em.close();
        return e;
    }
    
    public void edita(EventoEntity e) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        EventoEntity e2 = em.find(EventoEntity.class, e.getId());
        e2=em.merge(e);
        et.commit();
        em.close();
        
    }
    
    public List<EventoEntity> buscaNomeEvento(String nomeEvento) {
        String jpqlQuery = "SELECT e FROM EventoEntity e where e.nome_evento = :ne";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("ne", nomeEvento);
        List<EventoEntity> e = query.getResultList();
        em.close();
        return e;
    }
    
     public List<EventoEntity> buscaSiglaEvento(String siglaEvento) {
        String jpqlQuery = "SELECT e FROM EventoEntity e where e.sigla_evento = :ne";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("ne", siglaEvento);
        List<EventoEntity> e = query.getResultList();
        em.close();
        return e;
    }
    
    public List<EventoEntity> ListarTodosEventos() {
        String jpqlQuery = "SELECT e FROM EventoEntity e";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        List<EventoEntity> e = query.getResultList();
        em.close();
        return e;
    }
    
}
