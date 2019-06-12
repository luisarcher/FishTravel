/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.isec.fishtravel.jpa.TFlight;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TFlightDAO extends AbstractDAO<TFlight> {

    @PersistenceContext(unitName = "FishTravel-ea-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TFlightDAO() {
        super(TFlight.class);
    }
    
    public List<TFlight> getAllFlights() {
                
        try {

            Query q = em.createNamedQuery("TFlight.findAll");
            return q.getResultList();

        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public TFlight getFlightById(Integer id) {
        
        try{
            
            Query query = em.createNamedQuery("TFlight.findByIdFlight");
            query.setParameter("idFlight", id);
            return (TFlight) query.getSingleResult();
            
        } catch (NoResultException e){
            
            System.err.println("No result for Flight id: " + id);
            
        } catch (Exception e){
            
            System.err.println(e.getMessage());
        }
        
        return null;
    }
    
    public List<TFlight> getFlightsByIds(List<Integer> ids) {
        
        try{
            

            return em.createQuery("SELECT t FROM TFlight t WHERE t.idFlight IN :ids").setParameter("ids", ids).getResultList();

            
        } catch (NoResultException e){
            
            System.err.println("No result for Flights ids: " + ids);
            
        } catch (Exception e){
            
            System.err.println(e.getMessage());
        }
        
        return null;
    }
    
    public List<TFlight> getFlightsByDest(String dest) {
                
        try{
            /*
            Query query = em.createNamedQuery("TAirport.findByCode");
            query.setParameter("code", dest);
            TAirport airport = (TAirport) query.getSingleResult();*/

            Query query = em.createQuery("SELECT t from TFlight t\n" +
                                    "LEFT JOIN t.toAirport a\n" +
                                    "WHERE a.code = :dest_code");
            query.setParameter("dest_code", dest);
            return query.getResultList();
                       
        } catch (NoResultException e){
            
            System.err.println("No result for Flight destination: " + dest);
            
        } catch (Exception e){
            
            System.err.println(e.getMessage());
        }
        
        return null;
    }
    
    public List<TFlight> getCheapestFlightForDest(){
        
        try{

            Query query = em.createQuery("SELECT t from TFlight t\n" +
                                    "WHERE t.price = (SELECT MIN(t2.price) "
                    + "from TFlight t2 where t.toAirport = t2.toAirport)");
            
            return query.getResultList();
                       
        } catch (Exception e){
            
            System.err.println(e.getMessage());
            return null;
        }
    }
}
