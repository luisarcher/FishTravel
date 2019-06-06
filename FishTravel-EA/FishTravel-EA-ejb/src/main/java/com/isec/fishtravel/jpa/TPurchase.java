/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LM
 */
@Entity
@Table(name = "purchase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPurchase.findAll", query = "SELECT t FROM TPurchase t")
    , @NamedQuery(name = "TPurchase.findByIdPurchase", query = "SELECT t FROM TPurchase t WHERE t.idPurchase = :idPurchase")
    , @NamedQuery(name = "TPurchase.findByDatePurchase", query = "SELECT t FROM TPurchase t WHERE t.datePurchase = :datePurchase")})
public class TPurchase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_purchase")
    private Integer idPurchase;
    @Column(name = "date_purchase")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePurchase;
    @JoinTable(name = "tcontains", joinColumns = {
        @JoinColumn(name = "id_purchase", referencedColumnName = "id_purchase")}, inverseJoinColumns = {
        @JoinColumn(name = "id_flight", referencedColumnName = "id_flight")})
    @ManyToMany
    private Collection<TFlight> tFlightCollection;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private TUser idUser;

    public TPurchase() {
    }

    public TPurchase(Integer idPurchase) {
        this.idPurchase = idPurchase;
    }

    public Integer getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(Integer idPurchase) {
        this.idPurchase = idPurchase;
    }

    public Date getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Date datePurchase) {
        this.datePurchase = datePurchase;
    }

    @XmlTransient
    public Collection<TFlight> getTFlightCollection() {
        return tFlightCollection;
    }

    public void setTFlightCollection(Collection<TFlight> tFlightCollection) {
        this.tFlightCollection = tFlightCollection;
    }

    public TUser getIdUser() {
        return idUser;
    }

    public void setIdUser(TUser idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPurchase != null ? idPurchase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPurchase)) {
            return false;
        }
        TPurchase other = (TPurchase) object;
        if ((this.idPurchase == null && other.idPurchase != null) || (this.idPurchase != null && !this.idPurchase.equals(other.idPurchase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.isec.fishtravel.common.TPurchase[ idPurchase=" + idPurchase + " ]";
    }
    
}
