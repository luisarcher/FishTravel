/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.remote;

import com.isec.fishtravel.dto.DTOFlight;
import com.isec.fishtravel.dto.DTOUser;
import com.isec.fishtravel.facade.client.FTFlightFacade;
import com.isec.fishtravel.facade.client.FTUserFacade;
import com.isec.fishtravel.remote.ClientAccessRemote;
import com.isec.fishtravel.timer.MainTimerEJBLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author LM
 */
@Stateful
public class ClientAccess implements ClientAccessRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    MainTimerEJBLocal timerEJB;
    
    @EJB
    FTUserFacade facadeUser;
    
    @EJB
    FTFlightFacade facadeFlight;
    
    /*@EJB
    TMsglogDAO daoMsgLog;*/
   
    DTOUser user;
    
    @Override
    public boolean userLogin(String login, String passwd) {
        
        user = facadeUser.getUserByCredentials(login, passwd);
        return (user != null);
    }

    @Override
    public void logout() {
        this.user = null;
    }
    
    @Override
    public void setTimeVal(long val) {
        timerEJB.setUnixTime(val);
    }

    @Override
    public void setVelocity(int val) {
        timerEJB.setVelocity(val);
    }
    
    @Override
    public void stopTimer() {
        timerEJB.setVelocity(0);
    }

    @Override
    public void restartTimer() {
        timerEJB.setDefaultTimer();
    }
    
    /*@Override
    public List<String> getMessageList() {
        //return daoMsgLog.getMessageList();
        return null;
    }*/

    @Override
    public List<DTOFlight> getFlightList() {
        return facadeFlight.getAllFlights();
    }

    @Override
    public List<DTOUser> getUserList() {
        return facadeUser.getAllUsers();
    }

}
