/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.client;

import com.isec.fishtravel.dao.TFlightDAO;
import com.isec.fishtravel.dao.TPurchaseDAO;
import com.isec.fishtravel.dao.TUserDAO;
import com.isec.fishtravel.dto.DTOPurchase;
import com.isec.fishtravel.jpa.TPurchase;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class FTPurchaseFacade {

    /**
     * Main DAO of this facade
     */
    @EJB
    private TPurchaseDAO purchaseDAO;
    
    @EJB
    private TUserDAO userDAO;
    
    @EJB
    private TFlightDAO flightDAO;
    
    
    public void addPurchase(DTOPurchase dto){
        
        TPurchase e = new TPurchase();
        
        e.setIdUser(userDAO.find(dto.getUserId()));
        e.setTFlightCollection(flightDAO.getFlightsByIds(dto.getFlights()));
        e.setDatePurchase(new Date());
        
        purchaseDAO.create(e);
    }
}
