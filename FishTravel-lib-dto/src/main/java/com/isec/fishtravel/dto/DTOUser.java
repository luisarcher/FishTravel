/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ljordao-dev
 */
public class DTOUser implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String login;
    private String nameUser;
    private float credits;
    private Integer role;
    private Date birthdate;
    private Date createdAt;
    private String passwd;
    private String roleStr;

    public DTOUser(Integer id, 
            String login, 
            String name, 
            float credits, 
            Integer role, 
            Date birthDate, 
            Date createdAt, 
            String passwd,
            String roleStr) {
        
        this.id = id;
        this.login = login;
        this.nameUser = name;
        this.credits = credits;
        this.role = role;
        this.birthdate = birthDate;
        this.createdAt = createdAt;
        this.passwd = passwd;
        this.roleStr = roleStr;
    }
    
    @Override
    public String toString(){
        return this.nameUser;
    }

    public DTOUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String name) {
        this.nameUser = name;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthDate) {
        this.birthdate = birthDate;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRoleStr() {
        return roleStr;
    }

    public void setRoleStr(String roleStr) {
        this.roleStr = roleStr;
    }
        
}
