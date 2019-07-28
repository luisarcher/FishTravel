/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.timer;

/**
 *
 * @author LM
 */
public interface MainTimerEJBLocal {
    
    long getUnixTime();

    void setUnixTime(long aUnixTime);
    
    int getVelocity();

    void setVelocity(int velocity);
    
    void setDefaultTimer();

}
