/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.adm;

import com.isec.fishtravel.dao.AbstractDAO;
import com.isec.fishtravel.dao.TUserDAO;
import javax.ejb.Stateless;
import com.isec.fishtravel.jpa.TUser;
import javax.ejb.EJB;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TUserFacade extends AbstractFacade<TUser> {

    @EJB
    private TUserDAO dao;

    public TUserFacade() {
    }

    @Override
    protected AbstractDAO<TUser> getDAO() {
        return dao;
    }
    
}
