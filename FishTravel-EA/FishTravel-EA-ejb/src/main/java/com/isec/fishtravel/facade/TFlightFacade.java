/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade;

//import dto.DTOFlight;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.isec.fishtravel.jpa.TFlight;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TFlightFacade extends AbstractFacade<TFlight> {

    @PersistenceContext(unitName = "FishTravel-ea-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TFlightFacade() {
        super(TFlight.class);
    }
    
    /* Main Logic */
    
    /*private DTOFlight getDTOByIdentity(TFlight f){
        return new DTOFlight(
                f.getIdFlight(),
                f.getIdCompany().getNameCompany(),
                f.getFromAirport().getCode() + " - " + f.getFromAirport().getNameAirport(),
                f.getToAirport().getCode() + " - " + f.getToAirport().getNameAirport(),
                f.getNameFlight(),
                f.getPrice(),
                f.getTimeDeparture(),
                f.getTimeArrival(),
                f.getMaxSeats(),
                f.getAvailSeats()
        );
    }*/
    
    /*public List<DTOFlight> getAllFlights() {
        
        List<DTOFlight> flightsDTO = new ArrayList<DTOFlight>();
        List<TFlight> flights = null;
        
        try {

            Query q = em.createNamedQuery("TFlight.findAll");
            flights = q.getResultList();

            Iterator<TFlight> it = flights.iterator();
            while(it.hasNext()){

                TFlight f = it.next();
                flightsDTO.add(getDTOByIdentity(f));
            }
            
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return flightsDTO;
    }

    public DTOFlight getFlightById(Integer id) {
        
        try{
            
            Query query = em.createNamedQuery("TFlight.findByIdFlight");
            query.setParameter("idFlight", id);
            TFlight tf = (TFlight) query.getSingleResult();
            return getDTOByIdentity(tf);
            
        } catch (NoResultException e){
            
            System.err.println("No result for Flight id: " + id);
            
        } catch (Exception e){
            
            System.err.println(e.getMessage());
        }
        
        return null; 
    }*/
    
   /* public List<DTOFlight> getFlightsByDest(String dest) {
        
        List<DTOFlight> flightsDTO = new ArrayList<DTOFlight>();
        List<TFlight> flights = null;
        
        try{
            */
            /*Query query = em.createNamedQuery("TAirport.findByCode");
            query.setParameter("code", dest);
            TAirport airport = (TAirport) query.getSingleResult();*/
/*
            Query query = em.createQuery("SELECT t from TFlight t\n" +
                                    "LEFT JOIN t.toAirport a\n" +
                                    "WHERE a.code = :dest_code");
            query.setParameter("dest_code", dest);
            flights = query.getResultList();
            
            Iterator<TFlight> it = flights.iterator();
            while(it.hasNext()){

                TFlight f = it.next();
                flightsDTO.add(getDTOByIdentity(f));
            }
            
            System.out.println("VOOS ENCONTRADOS: " + flights.size());
            
            
        } catch (NoResultException e){
            
            System.err.println("No result for Flight destination: " + dest);
            
        } catch (Exception e){
            
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
        return flightsDTO;
    }*/
    
}
