/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.dao;

import com.isec.fishtravel.common.Consts;
import com.isec.fishtravel.facade.adm.TMsglogFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.isec.fishtravel.jpa.TUser;
import java.util.Date;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class TUserDAO extends AbstractDAO<TUser> {

    @PersistenceContext(unitName = "FishTravel-ea-ejbPU")
    private EntityManager em;
    
    @EJB
    private TMsglogFacade dblog;
    // dblog.addMsg("msg");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        
    public TUserDAO() {
        super(TUser.class);
    }
    
    private TUser setDefaults(TUser e){
        
        e.setCredits(Consts.DEFAULT_CLIENT_CREDITS);
        e.setIdRole(Consts.DEFAULT_CLIENT_ROLE);
        e.setDateReg(new Date());
            
        return e;
    }
    
    @Override
    public void create(TUser entity) {
        dblog.addMsg("Creating user: " + entity.getNameUser());
        // Validate login
        getEntityManager().persist(setDefaults(entity));
    }
    
    public Boolean hasCredits(Integer userId, Integer credits){
        
        return this.find(userId).getCredits() >= credits;
    }
    
    public Boolean decreaseCredits(Integer userId, Float credits){
        
        TUser u = this.find(userId);
        if (u.getCredits() < credits)
            return false;
        
        u.setCredits(u.getCredits() - credits);
        this.edit(u);
        return true;
    }
        
    // Login
    public TUser getUserByCredentials(String login, String passwd){
        
        Query query = em.createQuery("SELECT t FROM TUser t WHERE t.login = :login AND t.passwd = :passwd");
        query.setParameter("login", login);
        query.setParameter("passwd", passwd);
        
        try {
            dblog.addMsg(TUser.class.getName() + ": User login: " + login);
            return (TUser)query.getSingleResult();
        } catch (NoResultException e){
            return null;
        } catch (NonUniqueResultException e){
            dblog.addMsg(TUser.class.getName() + ": Non-Unique result on user login");
            return null;
        }
        catch (Exception e){
            dblog.addMsg(TUser.class.getName() + ": Exception while user log");
            return null;
        }
    }
}
