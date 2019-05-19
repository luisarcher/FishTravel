/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.isec.fishtravel.jpa.TMsglog;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TMsglogDAO extends AbstractDAO<TMsglog> {

    @PersistenceContext(unitName = "FishTravel-ea-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TMsglogDAO() {
        super(TMsglog.class);
    }
    
    public void addMsg(String msg) {

        try {
            
            TMsglog logEntry = new TMsglog();
            logEntry.setMsg(msg);
            em.persist(logEntry);
            
        }  catch (Exception e){
            
            System.out.println("Log - Erro em Persist:" + e.getMessage());
            
        }
    }
    
}
