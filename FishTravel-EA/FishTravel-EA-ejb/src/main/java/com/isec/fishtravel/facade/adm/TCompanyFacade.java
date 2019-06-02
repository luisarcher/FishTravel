/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.adm;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.isec.fishtravel.jpa.TCompany;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TCompanyFacade extends AbstractFacade<TCompany> {

    @PersistenceContext(unitName = "FishTravel-ea-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TCompanyFacade() {
        super(TCompany.class);
    }
    
}