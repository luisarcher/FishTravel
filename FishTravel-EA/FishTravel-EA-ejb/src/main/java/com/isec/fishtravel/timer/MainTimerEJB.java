/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.timer;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author LM
 */
@Startup
@Singleton
public class MainTimerEJB implements MainTimerEJBLocal {
    
    private static long unixTime;
    private int velocity = 1;
       
    @PostConstruct
    public void init() {
        System.out.println("MainTimerEJB started!");
        setDefaultTimer();
    }
    
    public void setDefaultTimer(){
        unixTime = System.currentTimeMillis() / 1000L;
    }
    
    @Schedule(second = "*", minute = "*", hour = "*")
    public void updateTimer(){
        
        unixTime = unixTime + (1 * this.velocity);
        
        System.err.println("System time: " + unixTime);
    }
    
    /* - - - - - Getters and Setters - - - - - */

    @Override
    public long getUnixTime() {
        return unixTime;
    }

    @Override
    public void setUnixTime(long aUnixTime) {
        unixTime = aUnixTime;
    }
    
    @Override
    public int getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

}
