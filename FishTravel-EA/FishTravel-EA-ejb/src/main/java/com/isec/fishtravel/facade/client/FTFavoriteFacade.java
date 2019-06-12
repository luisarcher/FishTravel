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
    
    
    public void addToFavorites(Integer userId, Integer flightId){
        
        TFavorite f = new TFavorite();
        
        f.setIdUser(userDAO.find(userId));
        f.setIdFlight(flightDAO.find(flightId));
        f.setDateFavorite(new Date());
        
        favoriteDAO.create(f);
    }
}
