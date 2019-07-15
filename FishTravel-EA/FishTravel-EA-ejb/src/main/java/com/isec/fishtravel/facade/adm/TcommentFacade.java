/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.adm;

import com.isec.fishtravel.dao.AbstractDAO;
import com.isec.fishtravel.dao.TcommentDAO;
import javax.ejb.Stateless;
import com.isec.fishtravel.jpa.Tcomment;
import javax.ejb.EJB;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TcommentFacade extends AbstractFacade<Tcomment> {

    @EJB
    private TcommentDAO dao;

    public TcommentFacade() {
    }

    @Override
    protected AbstractDAO<Tcomment> getDAO() {
        return dao;
    }
    
}
