/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.remote;

import com.isec.fishtravel.dto.DTOFlight;
import com.isec.fishtravel.dto.DTOUser;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author LM
 */
@Remote
public interface ClientAccessRemote {
    
    boolean userLogin(String login, String passwd);
    void logout();
    
    //List<String> getMessageList();
    List<DTOUser> getUserList();
    List<DTOFlight> getFlightList();
    
    void setTimeVal(long val);
    void setVelocity(int val);
    void stopTimer();
    void restartTimer();
    
}

