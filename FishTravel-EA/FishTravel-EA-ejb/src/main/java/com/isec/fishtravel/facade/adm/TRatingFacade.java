/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.adm;

import com.isec.fishtravel.dao.AbstractDAO;
import com.isec.fishtravel.dao.TRatingDAO;
import javax.ejb.Stateless;
import com.isec.fishtravel.jpa.TRating;
import javax.ejb.EJB;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TRatingFacade extends AbstractFacade<TRating> {

    @EJB
    private TRatingDAO dao;

    public TRatingFacade() {
    }

    @Override
    protected AbstractDAO<TRating> getDAO() {
        return dao;
    }
    
}
