/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarefa2;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author RAF
 */
public class JPAEdicao {
    

private EntityManager em;
    
    public JPAEdicao() {
    }
        
    public void salva(EdicaoEntity e) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(e);
        et.commit();
        em.close();
    }
    
     public void exclui(EdicaoEntity e) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(e);
        et.commit();
        em.close();
    }
     public void exclui(Long id) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        EdicaoEntity e = em.find(EdicaoEntity.class, id);
        em.remove(e);
        et.commit();
        em.close();
    }
     
    public EdicaoEntity recupera(Long id) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        EdicaoEntity e = em.find(EdicaoEntity.class, id);
        et.commit();
        em.close();
        return e;
    }
    
    public List<EdicaoEntity> buscaNumeroEdicao(int num) {
        String jpqlQuery = "SELECT e FROM EdicaoEntity e where e.numero_edicao = :ne";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("ne", num);
        List<EdicaoEntity> e = query.getResultList();
        em.close();
        return e;
    }
    
    public List<EdicaoEntity> buscaAnoEdicao(int ano) {
        String jpqlQuery = "SELECT e FROM EdicaoEntity e where e.ano_edicao = :ne";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("ne", ano);
        List<EdicaoEntity> e = query.getResultList();
        em.close();
        return e;
    }
    public List<EdicaoEntity> ListarTodasEdicoes() {
        String jpqlQuery = "SELECT e FROM EdicaoEntity e";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        List<EdicaoEntity> e = query.getResultList();
        em.close();
        return e;
    }

    public List<EdicaoEntity> ListarTodasEdicoes(Long idev) {
        String jpqlQuery = "SELECT e FROM EdicaoEntity e where e.id_evento = :ne";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("ne", idev);
        List<EdicaoEntity> e = query.getResultList();
        em.close();
        return e;
    }

    public List<Object> buscaEdicoesTimeStamp(String inidate, String enddate) {
        List<Object> eFull = null;
        String jpqlQuery,jpqlQueryEv;
        List<EdicaoEntity> e = null;
        List<Long> ev = null;
        boolean addEds = true,addEvs = false;
    try {
        //todas edicoes
        if(enddate.length()>0)
            jpqlQuery = "SELECT e FROM EdicaoEntity e where (e.dataInicio_edicao >= :di and e.dataInicio_edicao <= :df) ";
        else
            jpqlQuery = "SELECT e FROM EdicaoEntity e where (e.dataInicio_edicao >= :di) ";

        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("di", inidate);
        query.setParameter("df", enddate);
        e = query.getResultList();
        //todos eventos
        if(enddate.length()>0)
            jpqlQueryEv = "SELECT * FROM EventoEntity e where e.id_evento in(  SELECT distinct id_evento FROM EdicaoEntity e where (e.dataInicio_edicao >= :di2 and e.dataInicio_edicao <= :df2)) ";
        else
            jpqlQueryEv = "SELECT * FROM EventoEntity e where e.id_evento in(  SELECT distinct id_evento FROM EdicaoEntity e where (e.dataInicio_edicao >= :di2)) ";
        Query queryEv = em.createQuery(jpqlQueryEv);
        queryEv.setParameter("di2", inidate);
        queryEv.setParameter("df2", enddate);
        ev = query.getResultList();
        //consolidando
        addEds=eFull.addAll(e);
        addEvs=eFull.addAll(ev);
        
        
    } catch (Exception ex) {
        Logger.getLogger(JPAEdicao.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Erro nas datas fornecidas");
        if (!addEds)
            System.out.println("Erro ao recuperar edicões");
        if (!addEvs)
            System.out.println("Erro ao recuperar eventos");
    }
    finally{
        em.close();

        return eFull;
    }

    }

    public List<Object> buscaEdicoesCidade(String cidade) {
        List<Object> eFull = null;
        String jpqlQuery;
        List<EdicaoEntity> e = null;
        List<Long> ev = null;
        boolean addEds = true,addEvs = false;
    try {
        //todas edicoes
        jpqlQuery = "SELECT e FROM EdicaoEntity e where (e.cidade_edicao = :cd ";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("cd", cidade);
        e = query.getResultList();
        //todos eventos
        String jpqlQueryEv = "SELECT * FROM EventoEntity e where e.id_evento in(SELECT e FROM EdicaoEntity e where (e.cidade_edicao = :cd) ";
        Query queryEv = em.createQuery(jpqlQueryEv);
        queryEv.setParameter("di", cidade);
        ev = query.getResultList();
        //consolidando
        addEds=eFull.addAll(e);
        addEvs=eFull.addAll(ev);
        
        
    } catch (Exception ex) {
        Logger.getLogger(JPAEdicao.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Erro na cidade fornecidas");
        if (!addEds)
            System.out.println("Erro ao recuperar edicões");
        if (!addEvs)
            System.out.println("Erro ao recuperar eventos");
    }
    finally{
        em.close();

        return eFull;
    }

    }

    public List<Object> buscaEdicoesCidadeTimestamp(String cidade, String inidate, String enddate){
        List<Object> eFull = null;
        String jpqlQuery,jpqlQueryEv;
        List<EdicaoEntity> e = null;
        List<Long> ev = null;
        boolean addEds = true,addEvs = false;
    try {
        //todas edicoes
        if(enddate.length()>0)
            jpqlQuery = "SELECT e FROM EdicaoEntity e where (e.dataInicio_edicao >= :di and e.dataInicio_edicao <= :df and e.cidade_edicao= :cd) ";
        else
            jpqlQuery = "SELECT e FROM EdicaoEntity e where (e.dataInicio_edicao >= :di and e.cidade_edicao= :cd) ";

        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("di", inidate);
        query.setParameter("df", enddate);
        query.setParameter("cd", cidade);
        e = query.getResultList();
        //todos eventos
        if(enddate.length()>0)
            jpqlQueryEv = "SELECT * FROM EventoEntity e where e.id_evento in(  SELECT distinct id_evento FROM EdicaoEntity e where (e.dataInicio_edicao >= :di2 and e.dataInicio_edicao <= :df2 and e.cidade_edicao= :cd2)) ";
        else
            jpqlQueryEv = "SELECT * FROM EventoEntity e where e.id_evento in(  SELECT distinct id_evento FROM EdicaoEntity e where (e.dataInicio_edicao >= :di2 and e.cidade_edicao= :cd2)) ";
        Query queryEv = em.createQuery(jpqlQueryEv);
        queryEv.setParameter("di2", inidate);
        queryEv.setParameter("df2", enddate);
        queryEv.setParameter("cd2", cidade);
        ev = query.getResultList();
        //consolidando
        addEds=eFull.addAll(e);
        addEvs=eFull.addAll(ev);
        
        
    } catch (Exception ex) {
        Logger.getLogger(JPAEdicao.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Erro nas datas ou na cidade fornecidas");
        if (!addEds)
            System.out.println("Erro ao recuperar edicões");
        if (!addEvs)
            System.out.println("Erro ao recuperar eventos");
    }
    finally{
        em.close();

        return eFull;
    }

    }

    
}
