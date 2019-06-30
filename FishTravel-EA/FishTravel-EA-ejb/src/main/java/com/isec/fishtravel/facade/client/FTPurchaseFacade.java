/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.client;

import static com.isec.fishtravel.common.ErrorCodes.*;
import com.isec.fishtravel.dao.TFlightDAO;
import com.isec.fishtravel.dao.TPurchaseDAO;
import com.isec.fishtravel.dao.TUserDAO;
import com.isec.fishtravel.dto.DTOLuggage;
import com.isec.fishtravel.dto.DTOPurchase;
import com.isec.fishtravel.jpa.TPurchase;
import com.isec.fishtravel.jpa.Tcontains;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class FTPurchaseFacade {
    
    @EJB
    private FTLuggageFacade ejbLuggageFacade;

    /**
     * Main DAO of this facade
     */
    @EJB
    private TPurchaseDAO purchaseDAO;
    
    @EJB
    private TUserDAO userDAO;
    
    @EJB
    private TFlightDAO flightDAO;
        
    public Integer newOrder(DTOPurchase purchase, List<DTOLuggage> luggage){
               
        int retCode = 0;
        
        // --- Calculates the total of the purchase
        Float total = flightDAO.calculateTotal(purchase.getFlights());
        if (total == 0f)
            return ERR_FLIGHT_CALC_TOTAL;
        
        // --- Validates the list of flights
        retCode = flightDAO.checkFlightsAvailability(purchase.getFlights());
        if (retCode != ALL_OK) return retCode;
        
        // === Start of the changes point
        
        // --- Verifies and decreases user credits
        if (userDAO.hasCredits(purchase.getUserId(), total)){
            userDAO.decreaseCredits(purchase.getUserId(), total);
        } else {
            return ERR_FLIGHT_NO_CREDITS;
        }
        
        // --- Updates the available seat no. on flight table
        flightDAO.updateSeatNo(purchase.getFlights());
        
        // --- Starts a new Purchase
        addPurchase(purchase);
        ejbLuggageFacade.addLuggage(luggage);
        return ALL_OK;        
    }
    
    private void addPurchase(DTOPurchase purchase){
        
        List<Tcontains> itemEntryList = new ArrayList<>();
        TPurchase newPurchase = purchaseDAO.createRetId(mapDTOtoEntity(purchase));
        Set<Integer> uniqueIds = new HashSet<>(purchase.getFlights());
        for (Integer f : uniqueIds){
            Tcontains itemEntry = new Tcontains(f,newPurchase.getIdPurchase());
            itemEntry.setTFlight(flightDAO.getFlightById(f));
            itemEntry.setTPurchase(newPurchase);
            
            // - Check how many times the same flight has been bought
            itemEntry.setQuant(Collections.frequency(purchase.getFlights(), f));
            
            itemEntryList.add(itemEntry);
        }
        
        newPurchase.setTcontainsCollection(itemEntryList);
        purchaseDAO.edit(newPurchase);
    }
    
    private TPurchase mapDTOtoEntity(DTOPurchase dto){
        
        TPurchase e = new TPurchase();
        
        e.setIdUser(userDAO.find(dto.getUserId()));
        //e.setTFlightCollection(flightDAO.getFlightsByIds(dto.getFlights()));
        e.setDatePurchase(new Date());
        return e;
    }
    
}
