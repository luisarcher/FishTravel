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
    public static final float DEFAULT_CLIENT_CREDITS = (float) 100.5;
    
    public static final int FS_TO_DEPART = 1;
    public static final int FS_DEPARTED = 2;
    public static final int FS_ARRIVED = 3;
    public static final int FS_DELAYED = 4;
    public static final int FS_CANCELLED = 5;
    
    public static String getFlightStatusById(int s){
        
        switch(s){
            
            case FS_TO_DEPART: return "To depart";
            case FS_DEPARTED: return "Departed";
            case FS_ARRIVED: return "Arrived";
            case FS_DELAYED: return "Delayed";
            case FS_CANCELLED: return "Cancelled";
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
