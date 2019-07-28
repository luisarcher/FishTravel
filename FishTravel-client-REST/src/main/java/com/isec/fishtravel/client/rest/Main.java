/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.client.rest;

import com.isec.fishtravel.dto.DTOFlight;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author LM
 */
public class Main {
    
    static GenericEntity<List<DTOFlight>> genericEntity = null;
    
    public static void main(String[] args) {
        
        ClientRS c = new ClientRS();
        Response r = c.getFlights(Response.class, null);
        System.out.println("Status: " + r.getStatus());
        //genericEntity = r.readEntity(GenericEntity<List<DTOFlight>>(){});
        //List<DTOFlight> flights = genericEntity.getEntity();
        List<DTOFlight> flights = r.readEntity(new GenericType<List<DTOFlight>>(){});
        
        listAllFlights(flights);      
        c.close();
        
    }
    
    public static void listAllFlights(List<DTOFlight> flights){
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");                       
        String leftAlignFormat = "| %-8s | %-18s | %-18s | %-4.2f | %-9s | %-9s | %-5s |%n";

        System.out.format("+----------+--------------------+--------------------+--------+------------------+------------------+-------+%n");
        System.out.format("| Flight   | FROM               | TO                 | PRICE  | DEPARTURE        | ARRIVAL          | SEATS |%n");
        System.out.format("+----------+--------------------+--------------------+--------+------------------+------------------+-------+%n");

        for (DTOFlight f : flights){
            System.out.format(leftAlignFormat,
                    f.getFlightName(),
                    f.getFrom(),
                    f.getTo(),
                    f.getPrice(),
                    sdf.format(f.getTimeDeparture()),
                    sdf.format(f.getTimeArrival()),
                    (String.valueOf(f.getAvailableSeats())  + "/" + String.valueOf(f.getMaxSeats()))
                );
        }

        System.out.format("+----------+--------------------+--------------------+--------+------------------+------------------+-------+%n");
        
    }
    
}
