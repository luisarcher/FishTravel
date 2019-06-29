/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.dao;

import com.isec.fishtravel.jpa.Tcontains;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LM
 */
@Stateless
public class TcontainsDAO extends AbstractDAO<Tcontains> {

    @PersistenceContext(unitName = "FishTravel-ea-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TcontainsDAO() {
        super(Tcontains.class);
    }
    
}
