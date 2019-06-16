/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.common;

/**
 *
 * @author ljordao-dev
 */
public abstract class Consts {
    
    public static final int DEFAULT_CLIENT_ROLE = 1;
    public static final float DEFAULT_CLIENT_CREDITS = 50;
    
    public static String getFlightStatusById(int s){
        
        switch(s){
            
            case 1: return "To depart";
            case 2: return "Departed";
            case 3: return "Delayed";
            case 4: return "Cancelled";
            default: return "Undefined Status";
        }
    }
    
    public static String getRoleById(int r){
        
        switch(r){
            
            case 1: return "Visitor";
            case 2: return "Client";
            case 3: return "Operator";
            default: return "Undefined Role";
        }
        
    }
    
}
