/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.dto;

import java.io.Serializable;

/**
 *
 * @author LM
 */
public class DTOLuggage implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer userId;
    private Integer flightId;
    private Float kg;

    public DTOLuggage() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Float getKg() {
        return kg;
    }

    public void setKg(Float kg) {
        this.kg = kg;
    }
    
    
    
}
