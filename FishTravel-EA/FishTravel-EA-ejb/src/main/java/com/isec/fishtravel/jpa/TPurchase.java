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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LM
 */
@Entity
@Table(name = "purchase", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_purchase"})})
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
    @Column(name = "id_purchase", nullable = false)
    private Integer idPurchase;
    @Column(name = "date_purchase")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePurchase;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private TUser idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tPurchase")
    private Collection<Tcontains> tcontainsCollection;

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

    public TUser getIdUser() {
        return idUser;
    }

    public void setIdUser(TUser idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public Collection<Tcontains> getTcontainsCollection() {
        return tcontainsCollection;
    }

    public void setTcontainsCollection(Collection<Tcontains> tcontainsCollection) {
        this.tcontainsCollection = tcontainsCollection;
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
        return "com.isec.fishtravel.jpa.TPurchase[ idPurchase=" + idPurchase + " ]";
    }
    
}
