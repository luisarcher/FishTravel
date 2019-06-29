/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author LM
 */
@Embeddable
public class TcontainsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_flight", nullable = false)
    private int idFlight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_purchase", nullable = false)
    private int idPurchase;

    public TcontainsPK() {
    }

    public TcontainsPK(int idFlight, int idPurchase) {
        this.idFlight = idFlight;
        this.idPurchase = idPurchase;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFlight;
        hash += (int) idPurchase;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcontainsPK)) {
            return false;
        }
        TcontainsPK other = (TcontainsPK) object;
        if (this.idFlight != other.idFlight) {
            return false;
        }
        if (this.idPurchase != other.idPurchase) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.isec.fishtravel.jpa.TcontainsPK[ idFlight=" + idFlight + ", idPurchase=" + idPurchase + " ]";
    }
    
}
