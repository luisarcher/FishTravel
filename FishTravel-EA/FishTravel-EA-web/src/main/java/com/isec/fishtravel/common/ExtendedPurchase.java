/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.common;

import java.io.Serializable;

/**
 *
 * @author LM
 */
public class ExtendedPurchase implements Serializable{
    
    private Integer userId;
    private Integer purchaseId;
    private Integer flightId;
    private String flightName;
    private String flightDetails;
    private Integer quant;
    
    public ExtendedPurchase() {
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

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(String flightDetails) {
        this.flightDetails = flightDetails;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }
    
    
            
    
}
