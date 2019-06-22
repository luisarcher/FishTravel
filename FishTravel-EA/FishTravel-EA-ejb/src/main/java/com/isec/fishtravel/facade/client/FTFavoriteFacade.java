/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.client;

import com.isec.fishtravel.dao.TFavoriteDAO;
import com.isec.fishtravel.dao.TFlightDAO;
import com.isec.fishtravel.dao.TUserDAO;
import com.isec.fishtravel.jpa.TFavorite;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class FTFavoriteFacade {

    @EJB
    private TFavoriteDAO favoriteDAO;
    
    @EJB
    private TUserDAO userDAO;
    
    @EJB
    private TFlightDAO flightDAO;
    
    
    public Boolean addToFavorites(Integer userId, Integer flightId){
        
        TFavorite e = new TFavorite();
        
        e.setIdUser(userDAO.find(userId));
        e.setIdFlight(flightDAO.find(flightId));
        e.setDateFavorite(new Date());
        
        favoriteDAO.create(e);
        return true;
    }
}
