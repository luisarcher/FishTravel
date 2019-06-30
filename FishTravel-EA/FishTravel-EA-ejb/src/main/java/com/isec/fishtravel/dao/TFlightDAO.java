/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.dao;

import static com.isec.fishtravel.common.Consts.FS_TO_DEPART;
import static com.isec.fishtravel.common.ErrorCodes.ALL_OK;
import static com.isec.fishtravel.common.ErrorCodes.ERR_FLIGHT_NOT_AVAIL_SEATS;
import static com.isec.fishtravel.common.ErrorCodes.ERR_FLIGHT_NOT_DEPARTING;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.isec.fishtravel.jpa.TFlight;
import java.util.ArrayList;
import java.util.Collections;
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
    
    public List<TFlight> getFlightsByIds(Boolean uniques, List<Integer> ids) {
        
        List<TFlight> list = null;
        try{
            
            list = new ArrayList<>(em.createQuery("SELECT t FROM TFlight t WHERE t.idFlight IN :ids")
                    .setParameter("ids", ids)
                    .getResultList());
            
            if (!uniques){
                // Include Duplicates
                for (TFlight f : list){
                    int occurrences = Collections.frequency(ids, f.getIdFlight());

                    for (int i = 1; i < occurrences ; i++){
                        list.add(f);
                    }
                }
            }
        } catch (NoResultException e){
            
            System.err.println("No result for Flights ids: " + e.getMessage());
            
        } catch (Exception e){
            
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public Float calculateTotal(List<Integer> ids){
        
        Float price = 0f;
        
        List<TFlight> list = getFlightsByIds(false,ids);
        for (TFlight f : list){
            price += f.getPrice();
        }
        return price;
    }
    
    /**
     * Given a list of flights,
     * check if those flight are ready to be bought.
     * @param ids
     * @return 
     */
    public Integer checkFlightsAvailability(List<Integer> ids){
                
        for (TFlight f : getFlightsByIds(true, ids)){
            int quant = Collections.frequency(ids, f.getIdFlight());
            
            if (f.getAvailSeats() < quant)
                return ERR_FLIGHT_NOT_AVAIL_SEATS;
            
            if (f.getIdStatus() != FS_TO_DEPART)
                return ERR_FLIGHT_NOT_DEPARTING;
        }
        return ALL_OK;
    }
    
    /**
     * Update the available seats on the flights that are about to be bought.
     * @param ids 
     */
    public void updateSeatNo(List<Integer> ids){
        
        for (TFlight f : getFlightsByIds(true, ids)){
            
            int quant = Collections.frequency(ids, f.getIdFlight());
            f.setAvailSeats(f.getAvailSeats() - quant);
            this.edit(f);
        }
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
