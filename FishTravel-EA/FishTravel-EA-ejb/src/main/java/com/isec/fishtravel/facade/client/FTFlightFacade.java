/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.client;

import com.isec.fishtravel.dao.TFlightDAO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import com.isec.fishtravel.jpa.TFlight;
import com.isec.fishtravel.dto.DTOFlight;
import javax.ejb.EJB;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class FTFlightFacade {
    
    @EJB
    private TFlightDAO flightDAO;

    public FTFlightFacade() {
    }
    
    /* Main Logic */
    
    public List<DTOFlight> getAllFlights() {
        
        return mapAllEntitiesToDTO(flightDAO.findAll());
    }

    public DTOFlight getFlightById(Integer id) {
        
        return mapEntityToDTO(flightDAO.getFlightById(id));
    }
    
    public List<DTOFlight> getFlightsByDest(String dest) {
        
        return mapAllEntitiesToDTO(flightDAO.getFlightsByDest(dest));
    }
    
    public List<DTOFlight> getCheapestFlightForDest(){
        
        return mapAllEntitiesToDTO(flightDAO.getCheapestFlightForDest());
    }
        
    private DTOFlight mapEntityToDTO(TFlight e){
        
        DTOFlight dto = new DTOFlight();
        
        dto.setId(e.getIdFlight());
        dto.setCompany(e.getIdCompany().getNameCompany());
        dto.setFrom(e.getFromAirport().getCode() + " - " + e.getFromAirport().getNameAirport());
        dto.setTo(e.getToAirport().getCode() + " - " + e.getToAirport().getNameAirport());
        dto.setFlightName(e.getNameFlight());
        dto.setPrice(e.getPrice());
        dto.setTimeDeparture(e.getTimeDeparture());
        dto.setTimeArrival(e.getTimeArrival());
        dto.setMaxSeats(e.getMaxSeats());
        dto.setAvailableSeats(e.getAvailSeats());
        dto.setFlightStatus(e.getIdStatus());
        
        return dto;
    }
    
    private List<DTOFlight> mapAllEntitiesToDTO(List<TFlight> list){
        
        List<DTOFlight> entityDtoList = new ArrayList<>();
        Iterator<TFlight> it = list.iterator();
        
        while(it.hasNext()){
            TFlight f = it.next();
            entityDtoList.add(mapEntityToDTO(f));
        }
        
        return entityDtoList;
        
    }
    
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
}
