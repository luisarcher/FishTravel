/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.dao;

import com.isec.fishtravel.common.Consts;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.isec.fishtravel.jpa.TUser;
import com.isec.fishtravel.mdbproducer.MessageSenderRemote;
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
    private MessageSenderRemote msgSender;

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
        //dblog.addMsg("Creating user: " + entity.getNameUser());
        // Validate login
        getEntityManager().persist(setDefaults(entity));
    }
    
    public Boolean hasCredits(Integer userId, Float credits){
        
        return this.find(userId).getCredits() >= credits;
    }
    
    public void decreaseCredits(Integer userId, Float credits){
        
        TUser u = this.find(userId);        
        u.setCredits(u.getCredits() - credits);
        this.edit(u);
    }
        
    // Login
    public TUser getUserByCredentials(String login, String passwd){
        
        Query query = em.createQuery("SELECT t FROM TUser t WHERE t.login = :login AND t.passwd = :passwd");
        query.setParameter("login", login);
        query.setParameter("passwd", passwd);
        
        TUser user = null;
        
        try {
            //dblog.addMsg(TUser.class.getName() + ": User login: " + login);
            user = (TUser)query.getSingleResult();
            
            if (user != null){
                msgSender.sendToQueue("User logged in: " + login);
            }
            
        } catch (NoResultException e){
            System.err.println("TUserDAO : ERROR : No result for (" +login + ","+ passwd +") : " + e.getMessage());
        } catch (NonUniqueResultException e){
            //dblog.addMsg(TUser.class.getName() + ": Non-Unique result on user login");
            System.err.println("TUserDAO : ERROR : Multiple results for (" +login + ","+ passwd +") : " + e.getMessage());
        }
        catch (Exception e){
            //dblog.addMsg(TUser.class.getName() + ": Exception while user log");
            System.err.println("TUserDAO : ERROR : (" +login + ","+ passwd +") : " + e.getMessage());
        
        }
        return user;
    }
}
