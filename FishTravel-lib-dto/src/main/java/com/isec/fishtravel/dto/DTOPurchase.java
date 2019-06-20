/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author LM
 */
public class DTOPurchase implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private List<Integer> flights;

    public DTOPurchase() {
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getFlights() {
        return flights;
    }

    public void setFlights(List<Integer> flights) {
        this.flights = flights;
    }
        
}
