/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.timer;

import com.isec.fishtravel.common.Consts;
import com.isec.fishtravel.dao.TFlightDAO;
import com.isec.fishtravel.jpa.TFlight;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author LM
 */
@Startup
@Singleton
public class FlightArrivedTimer {
    
    @EJB
    private TFlightDAO flightDAO;
    
    @EJB
    private MainTimerEJBLocal serverTimer;
        
    @PostConstruct
    public void init() {
        System.out.println("FlightArrivedTimer started!");
    }
    
    @Schedule(second="30", minute = "*", hour = "*")
    public void execute(){
        
        List<TFlight> flights = flightDAO.getArrivedFlightsGivenTime(new Date((long)serverTimer.getUnixTime()*1000));
        
        if (!flights.isEmpty()){
            for (TFlight f : flights){
                f.setIdStatus(Consts.FS_ARRIVED);
                flightDAO.edit(f);
            }
        }
        
    }
    
}
