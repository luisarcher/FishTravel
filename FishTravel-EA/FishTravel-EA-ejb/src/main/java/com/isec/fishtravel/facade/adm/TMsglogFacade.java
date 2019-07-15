/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.adm;

import com.isec.fishtravel.dao.AbstractDAO;
import com.isec.fishtravel.dao.TMsglogDAO;
import javax.ejb.Stateless;
import com.isec.fishtravel.jpa.TMsglog;
import javax.ejb.EJB;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TMsglogFacade extends AbstractFacade<TMsglog> {

    @EJB
    private TMsglogDAO dao;

    public TMsglogFacade() {
    }
    
    public void addMsg(String msg){
        dao.addMsg(msg);
    }
    
    @Override
    protected AbstractDAO<TMsglog> getDAO() {
        return dao;
    }
    
}
