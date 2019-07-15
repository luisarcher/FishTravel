/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.adm;

import com.isec.fishtravel.dao.AbstractDAO;
import com.isec.fishtravel.dao.TAirportDAO;
import javax.ejb.Stateless;
import com.isec.fishtravel.jpa.TAirport;
import javax.ejb.EJB;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TAirportFacade extends AbstractFacade<TAirport> {
   
    @EJB
    private TAirportDAO airportDAO;

    public TAirportFacade() {
    }

    @Override
    protected AbstractDAO<TAirport> getDAO() {
        return airportDAO;
    }
    
}
