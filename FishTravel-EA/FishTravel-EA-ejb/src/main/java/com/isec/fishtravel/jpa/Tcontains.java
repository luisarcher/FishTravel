/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LM
 */
@Entity
@Table(name = "tcontains", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_flight", "id_purchase"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcontains.findAll", query = "SELECT t FROM Tcontains t")
    , @NamedQuery(name = "Tcontains.findByIdFlight", query = "SELECT t FROM Tcontains t WHERE t.tcontainsPK.idFlight = :idFlight")
    , @NamedQuery(name = "Tcontains.findByIdPurchase", query = "SELECT t FROM Tcontains t WHERE t.tcontainsPK.idPurchase = :idPurchase")
    , @NamedQuery(name = "Tcontains.findByQuant", query = "SELECT t FROM Tcontains t WHERE t.quant = :quant")})
public class Tcontains implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TcontainsPK tcontainsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quant", nullable = false)
    private int quant;
    @JoinColumn(name = "id_flight", referencedColumnName = "id_flight", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TFlight tFlight;
    @JoinColumn(name = "id_purchase", referencedColumnName = "id_purchase", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TPurchase tPurchase;

    public Tcontains() {
    }

    public Tcontains(TcontainsPK tcontainsPK) {
        this.tcontainsPK = tcontainsPK;
    }

    public Tcontains(TcontainsPK tcontainsPK, int quant) {
        this.tcontainsPK = tcontainsPK;
        this.quant = quant;
    }

    public Tcontains(int idFlight, int idPurchase) {
        this.tcontainsPK = new TcontainsPK(idFlight, idPurchase);
    }

    public TcontainsPK getTcontainsPK() {
        return tcontainsPK;
    }

    public void setTcontainsPK(TcontainsPK tcontainsPK) {
        this.tcontainsPK = tcontainsPK;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public TFlight getTFlight() {
        return tFlight;
    }

    public void setTFlight(TFlight tFlight) {
        this.tFlight = tFlight;
    }

    public TPurchase getTPurchase() {
        return tPurchase;
    }

    public void setTPurchase(TPurchase tPurchase) {
        this.tPurchase = tPurchase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tcontainsPK != null ? tcontainsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcontains)) {
            return false;
        }
        Tcontains other = (Tcontains) object;
        if ((this.tcontainsPK == null && other.tcontainsPK != null) || (this.tcontainsPK != null && !this.tcontainsPK.equals(other.tcontainsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.isec.fishtravel.jpa.Tcontains[ tcontainsPK=" + tcontainsPK + " ]";
    }
    
}
