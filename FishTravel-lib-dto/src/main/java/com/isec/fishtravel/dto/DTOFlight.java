/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.dto;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Flight")
public class DTOFlight implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String company;
    private String from;
    private String to;
    private String flightName;
    private float price;
    private Date timeDeparture;
    private Date timeArrival;
    private Integer maxSeats;
    private Integer availableSeats;

    public DTOFlight() {
    }

    public DTOFlight(Integer id, String company, String from, String to, String flightName, float price, Date timeDeparture, Date timeArrival, Integer maxSeats, Integer availableSeats) {
        this.id = id;
        this.company = company;
        this.from = from;
        this.to = to;
        this.flightName = flightName;
        this.price = price;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.maxSeats = maxSeats;
        this.availableSeats = availableSeats;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(Date timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }  

    public Date getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(Date timeArrival) {
        this.timeArrival = timeArrival;
    }

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
    
}
