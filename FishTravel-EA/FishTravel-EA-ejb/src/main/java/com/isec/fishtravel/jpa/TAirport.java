/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LM
 */
@Entity
@Table(name = "airport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAirport.findAll", query = "SELECT t FROM TAirport t")
    , @NamedQuery(name = "TAirport.findByIdAirport", query = "SELECT t FROM TAirport t WHERE t.idAirport = :idAirport")
    , @NamedQuery(name = "TAirport.findByCode", query = "SELECT t FROM TAirport t WHERE t.code = :code")
    , @NamedQuery(name = "TAirport.findByNameAirport", query = "SELECT t FROM TAirport t WHERE t.nameAirport = :nameAirport")})
public class TAirport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_airport")
    private Integer idAirport;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "name_airport")
    private String nameAirport;
    @OneToMany(mappedBy = "fromAirport")
    private Collection<TFlight> tFlightCollection;
    @OneToMany(mappedBy = "toAirport")
    private Collection<TFlight> tFlightCollection1;

    public TAirport() {
    }

    public TAirport(Integer idAirport) {
        this.idAirport = idAirport;
    }

    public TAirport(Integer idAirport, String code, String nameAirport) {
        this.idAirport = idAirport;
        this.code = code;
        this.nameAirport = nameAirport;
    }

    public Integer getIdAirport() {
        return idAirport;
    }

    public void setIdAirport(Integer idAirport) {
        this.idAirport = idAirport;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameAirport() {
        return nameAirport;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }

    @XmlTransient
    public Collection<TFlight> getTFlightCollection() {
        return tFlightCollection;
    }

    public void setTFlightCollection(Collection<TFlight> tFlightCollection) {
        this.tFlightCollection = tFlightCollection;
    }

    @XmlTransient
    public Collection<TFlight> getTFlightCollection1() {
        return tFlightCollection1;
    }

    public void setTFlightCollection1(Collection<TFlight> tFlightCollection1) {
        this.tFlightCollection1 = tFlightCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAirport != null ? idAirport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAirport)) {
            return false;
        }
        TAirport other = (TAirport) object;
        if ((this.idAirport == null && other.idAirport != null) || (this.idAirport != null && !this.idAirport.equals(other.idAirport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.code + ":" + this.nameAirport;
    }
    
}
