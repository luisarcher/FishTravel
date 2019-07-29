/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.mdbproducer;

import javax.ejb.Remote;

/**
 *
 * @author LM
 */
@Remote
public interface MessageSenderRemote {
    
    void sendToQueue(String text);
    
}
