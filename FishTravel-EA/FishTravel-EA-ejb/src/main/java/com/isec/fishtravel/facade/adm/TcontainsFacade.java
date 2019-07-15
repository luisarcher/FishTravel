/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.adm;

import com.isec.fishtravel.dao.AbstractDAO;
import com.isec.fishtravel.dao.TcontainsDAO;
import com.isec.fishtravel.jpa.Tcontains;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author LM
 */
@Stateless
public class TcontainsFacade extends AbstractFacade<Tcontains> {

    @EJB
    private TcontainsDAO dao;

    public TcontainsFacade() {
    }

    @Override
    protected AbstractDAO<Tcontains> getDAO() {
        return dao;
    }
    
}
