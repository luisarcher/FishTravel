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
        
        List<Tcontains> itemEntryList = new ArrayList<>();
        
        // Calculates the total of the purchase
        Float total = flightDAO.calculateTotal(purchase.getFlights());
        if (total == null)
            return ERR_FLIGHT_CALC_TOTAL;
                
        // Verificar se todos os voos estao em depart
        if (!flightDAO.checkFlightsDeparting(purchase.getFlights()))
            return ERR_FLIGHT_NOT_DEPARTING;
        
        userDAO.begin();
        flightDAO.begin();
        
        // Decreases user credits
        if (!userDAO.decreaseCredits(purchase.getUserId(), total))
            return ERR_FLIGHT_NO_CREDITS;
        
        // reduzir numero de lugares no flightDAO
        if (!flightDAO.decreaseSeatNo(purchase.getFlights())){
            userDAO.rollback();
            flightDAO.rollback();
            return ERR_FLIGHT_NOT_AVAIL_SEATS;
        }
        
        userDAO.commit();
        flightDAO.commit();
        
        // definir a colecção de contains da compra
        TPurchase newPurchase = purchaseDAO.createRetId(mapDTOtoEntity(purchase));
        //TPurchase newPurchase = mapDTOtoEntity(purchase);
        
        // * Creates a list without duplicates
        Set<Integer> uniqueIds = new HashSet<>(purchase.getFlights());
        
        for (Integer f : uniqueIds){
            
            Tcontains itemEntry = new Tcontains(f,newPurchase.getIdPurchase());
            itemEntry.setTFlight(flightDAO.getFlightById(f));
            itemEntry.setTPurchase(newPurchase);
            
            // * Para cada item na lista unica, ver quantas vezes foi comprado.
            itemEntry.setQuant(Collections.frequency(purchase.getFlights(), f));
            
            itemEntryList.add(itemEntry);
        }
        
        newPurchase.setTcontainsCollection(itemEntryList);
        
        purchaseDAO.edit(newPurchase);
        return ALL_OK;        
    }
    
    /*private void addPurchase(DTOPurchase dto){
        
        purchaseDAO.create(e);
    }*/
    
    private TPurchase mapDTOtoEntity(DTOPurchase dto){
        
        TPurchase e = new TPurchase();
        
        e.setIdUser(userDAO.find(dto.getUserId()));
        //e.setTFlightCollection(flightDAO.getFlightsByIds(dto.getFlights()));
        e.setDatePurchase(new Date());
        return e;
    }
    
}
