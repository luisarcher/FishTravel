/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.adm;

import com.isec.fishtravel.dao.AbstractDAO;
import com.isec.fishtravel.dao.TPurchaseDAO;
import javax.ejb.Stateless;
import com.isec.fishtravel.jpa.TPurchase;
import javax.ejb.EJB;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TPurchaseFacade extends AbstractFacade<TPurchase> {

    @EJB
    private TPurchaseDAO dao;

    public TPurchaseFacade() {
    }

    @Override
    protected AbstractDAO<TPurchase> getDAO() {
        return dao;
    }
    
}
