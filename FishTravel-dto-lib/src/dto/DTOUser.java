/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

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
    private String name;
    private float credits;
    private String role;
    private Date birthDate;
    private Date createdAt;

    public DTOUser(Integer id, String login, String name, float credits, String role, Date birthDate, Date createdAt) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.credits = credits;
        this.role = role;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }   
    
}
