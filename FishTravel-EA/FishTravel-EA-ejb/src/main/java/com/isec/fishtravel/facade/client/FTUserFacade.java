/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.facade.client;

import com.isec.fishtravel.common.Consts;
import com.isec.fishtravel.dao.TUserDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.isec.fishtravel.jpa.TUser;
import com.isec.fishtravel.dto.DTOUser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ljordao-dev
 */
@Stateless
public class FTUserFacade{
    
    @EJB
    private TUserDAO userDAO;
    
    @EJB
    private com.isec.fishtravel.facade.adm.TMsglogFacade dblog;
    // dblog.addMsg("msg");

    public FTUserFacade() {
    }
    
    public DTOUser getUserByCredentials(String login, String passwd){
        
        return mapEntityToDTO(userDAO.getUserByCredentials(login, passwd));
    }
    
    public void register(DTOUser newUser){
        
        userDAO.create(mapDTOtoEntity(newUser));
    }
    
    public List<DTOUser> getAllUsers(){
        return mapAllEntitiesToDTO(userDAO.findAll());
    }
    
    private DTOUser mapEntityToDTO(TUser e){
        
        if (e == null) return null;
        
        DTOUser dto = new DTOUser();
        
        dto.setId(e.getIdUser());
        dto.setLogin(e.getLogin());
        dto.setNameUser(e.getNameUser());
        dto.setCredits(e.getCredits());
        dto.setRole(e.getIdRole());
        dto.setBirthdate(e.getBirthdate());
        dto.setCreatedAt(e.getDateReg());
        dto.setRoleStr(Consts.getRoleById(e.getIdRole()));
        
        return dto;
    }
    
    private TUser mapDTOtoEntity(DTOUser dto){
        
        TUser e = new TUser();
        
        e.setLogin(dto.getLogin());
        e.setNameUser(dto.getNameUser());
        e.setBirthdate(dto.getBirthdate());
        e.setPasswd(dto.getPasswd());
        
        return e;
        
    }
    
    private List<DTOUser> mapAllEntitiesToDTO(List<TUser> list){
        
        List<DTOUser> entityDtoList = new ArrayList<>();
        Iterator<TUser> it = list.iterator();
        
        while(it.hasNext()){
            TUser f = it.next();
            entityDtoList.add(mapEntityToDTO(f));
        }
        
        return entityDtoList;
        
    }
}
