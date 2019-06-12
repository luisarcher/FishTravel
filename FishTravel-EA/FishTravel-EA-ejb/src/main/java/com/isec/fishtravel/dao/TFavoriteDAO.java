/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.isec.fishtravel.jpa.TFavorite;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TFavoriteDAO extends AbstractDAO<TFavorite> {

    @PersistenceContext(unitName = "FishTravel-ea-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TFavoriteDAO() {
        super(TFavorite.class);
    }
    
}
