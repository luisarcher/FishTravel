/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ljordao-dev
 */
@Entity
@Table(name = "flightstatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TFlightstatus.findAll", query = "SELECT t FROM TFlightstatus t")
    , @NamedQuery(name = "TFlightstatus.findByIdStatus", query = "SELECT t FROM TFlightstatus t WHERE t.idStatus = :idStatus")
    , @NamedQuery(name = "TFlightstatus.findByDescription", query = "SELECT t FROM TFlightstatus t WHERE t.description = :description")})
public class TFlightstatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_status")
    private Integer idStatus;
    private String description;

    public TFlightstatus() {
    }

    public TFlightstatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatus != null ? idStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TFlightstatus)) {
            return false;
        }
        TFlightstatus other = (TFlightstatus) object;
        if ((this.idStatus == null && other.idStatus != null) || (this.idStatus != null && !this.idStatus.equals(other.idStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.TFlightstatus[ idStatus=" + idStatus + " ]";
    }
    
}
