package com.isec.fishtravel.mdbproducer;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 *
 * @author LM
 */

@Singleton
public class MessageSender implements MessageSenderRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Resource(mappedName = "jms/ftQueue")
    Queue ftQueue;
    
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Override
    public void sendToQueue(String text) {
        try {
            TextMessage mymsg = context.createTextMessage();
            mymsg.setText(text);
            context.createProducer().send(ftQueue, mymsg);
        } catch(Exception e) {
            System.out.println("E: " + e.getMessage());
        }
    }
}
