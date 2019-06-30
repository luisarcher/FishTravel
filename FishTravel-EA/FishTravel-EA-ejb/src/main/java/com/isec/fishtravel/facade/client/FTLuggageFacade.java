/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.client;

import com.isec.fishtravel.dao.TFlightDAO;
import com.isec.fishtravel.dao.TLuggageDAO;
import com.isec.fishtravel.dao.TUserDAO;
import com.isec.fishtravel.dto.DTOLuggage;
import com.isec.fishtravel.jpa.TLuggage;
import java.util.Date;
import javax.ejb.Stateless;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class FTLuggageFacade{
    
    @EJB
    private TLuggageDAO luggageDAO;
    
    @EJB
    private TUserDAO userDAO;
    
    @EJB
    private TFlightDAO flightDAO;
    
    public FTLuggageFacade() {
    }
    
    public void addLuggage(List<DTOLuggage> list){
        
        for (DTOLuggage l : list){
            luggageDAO.create(mapDTOtoEntity(l));
        }
    }
    
    private TLuggage mapDTOtoEntity(DTOLuggage dto){
        
        TLuggage e = new TLuggage();
        
        e.setIdUser(userDAO.find(dto.getUserId()));
        e.setIdFlight(flightDAO.find(dto.getFlightId()));
        e.setKg(dto.getKg());
        e.setDateReg(new Date());
        return e;
        
    }
}
