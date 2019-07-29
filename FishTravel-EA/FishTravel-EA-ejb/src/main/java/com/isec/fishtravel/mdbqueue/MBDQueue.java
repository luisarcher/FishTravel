/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.mdbqueue;

import com.isec.fishtravel.facade.adm.TMsglogFacade;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author LM
 */

@MessageDriven(
        activationConfig = {
            @ActivationConfigProperty(
                    propertyName = "destinationType",
                    propertyValue = "javax.jms.Queue"),
            @ActivationConfigProperty(
                    propertyName = "destinationLookup",
                    propertyValue = "jms/ftQueue"
            )
        }
    )
public class MBDQueue implements MessageListener{
    
    @Resource
    private MessageDrivenContext mdc;
    
    @EJB
    TMsglogFacade msgLog;

    @Override
    public void onMessage(Message message) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
        
        // Envia mensagem para o log do servidor
        System.out.println("\n\n--- MSG MSG MSG ---\n\n");
        TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                System.out.println("Message queue: " + msg.getText());
                msgLog.addMsg(msg.getText());
                
            }
        } catch (Throwable te) {
            te.printStackTrace();
        }
        
    }
}
