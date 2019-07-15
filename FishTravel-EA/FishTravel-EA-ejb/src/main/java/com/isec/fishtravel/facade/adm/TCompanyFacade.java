/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.adm;

import com.isec.fishtravel.dao.AbstractDAO;
import com.isec.fishtravel.dao.TCompanyDAO;
import javax.ejb.Stateless;
import com.isec.fishtravel.jpa.TCompany;
import javax.ejb.EJB;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TCompanyFacade extends AbstractFacade<TCompany> {

    @EJB
    private TCompanyDAO companyDAO;

    public TCompanyFacade() {
    }

    @Override
    protected AbstractDAO<TCompany> getDAO() {
        return companyDAO;
    }
    
}
