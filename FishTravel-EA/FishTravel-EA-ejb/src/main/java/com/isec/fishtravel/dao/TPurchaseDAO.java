/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.isec.fishtravel.jpa.TPurchase;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TPurchaseDAO extends AbstractDAO<TPurchase> {

    @PersistenceContext(unitName = "FishTravel-ea-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TPurchaseDAO() {
        super(TPurchase.class);
    }
    
    // 'Add purchase' is defined by facade
    
    public Boolean hasFlightBought(Integer userId, Integer flightId){
        
        /*
        select p.id_purchase
        from purchase p, tcontains jt
        where p.id_user = 13
        and p.id_purchase = jt.id_purchase
        and jt.id_flight = 16
        */
        
        try{
            /*return em.createQuery("SELECT * "
                                + "FROM TPurchase p "
                                + "WHERE p."); //.setParameter("ids", ids).getResultList();
        */
            Query query = em.createQuery("SELECT p from TPurchase p\n" +
                                    "LEFT JOIN p.tFlightCollection f\n" +
                                    "WHERE p.idUser = :idUser AND f.idFlight = :idFlight");
            query.setParameter("idUser", userId);
            query.setParameter("idFlight", flightId);
            return query.getResultList().size() >= 1;
            
        } catch (NoResultException e){
            System.err.println("No result for Flights ids: " + e.getMessage());
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        
        return false;
    }
        
}
