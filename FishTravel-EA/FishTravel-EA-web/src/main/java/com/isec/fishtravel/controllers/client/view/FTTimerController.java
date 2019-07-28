/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.controllers.client.view;

import com.isec.fishtravel.timer.MainTimerEJBLocal;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author LM
 */
@ManagedBean
@ViewScoped
public class FTTimerController implements Serializable{
    
    public static final long serialVersionUID = 1L;
    
    @EJB
    private MainTimerEJBLocal ftServer;
    private long serverTime;
    private Date serverFullDate;
    
    public void updateTime(){
        this.serverTime = ftServer.getUnixTime();
        this.serverFullDate = new Date((long)serverTime*1000);
    }

    public long getServerTime() {
        this.serverTime = ftServer.getUnixTime();
        return this.serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public MainTimerEJBLocal getFtServer() {
        return ftServer;
    }

    public void setFtServer(MainTimerEJBLocal ftServer) {
        this.ftServer = ftServer;
    }

    public Date getServerFullDate() {
        return serverFullDate;
    }

    public void setServerFullDate(Date serverFullDate) {
        this.serverFullDate = serverFullDate;
    }
    
    

}
