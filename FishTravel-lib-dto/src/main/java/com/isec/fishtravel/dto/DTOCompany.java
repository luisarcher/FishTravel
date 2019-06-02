/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author ljordao-dev
 */
public class DTOCompany implements Serializable{
    
    private Integer id;
    private String nameCompany;

    public DTOCompany() {
    }

    public DTOCompany(Integer id, String nameCompany) {
        this.id = id;
        this.nameCompany = nameCompany;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }
    
    
    
}
