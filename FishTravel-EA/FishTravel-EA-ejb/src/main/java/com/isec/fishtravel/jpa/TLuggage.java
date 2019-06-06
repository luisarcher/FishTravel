/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isec.fishtravel.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LM
 */
@Entity
@Table(name = "luggage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TLuggage.findAll", query = "SELECT t FROM TLuggage t")
    , @NamedQuery(name = "TLuggage.findByIdLuggage", query = "SELECT t FROM TLuggage t WHERE t.idLuggage = :idLuggage")
    , @NamedQuery(name = "TLuggage.findByKg", query = "SELECT t FROM TLuggage t WHERE t.kg = :kg")
    , @NamedQuery(name = "TLuggage.findByDateReg", query = "SELECT t FROM TLuggage t WHERE t.dateReg = :dateReg")})
public class TLuggage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_luggage")
    private Integer idLuggage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kg")
    private float kg;
    @Column(name = "date_reg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReg;
    @JoinColumn(name = "id_flight", referencedColumnName = "id_flight")
    @ManyToOne(optional = false)
    private TFlight idFlight;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private TUser idUser;

    public TLuggage() {
    }

    public TLuggage(Integer idLuggage) {
        this.idLuggage = idLuggage;
    }

    public TLuggage(Integer idLuggage, float kg) {
        this.idLuggage = idLuggage;
        this.kg = kg;
    }

    public Integer getIdLuggage() {
        return idLuggage;
    }

    public void setIdLuggage(Integer idLuggage) {
        this.idLuggage = idLuggage;
    }

    public float getKg() {
        return kg;
    }

    public void setKg(float kg) {
        this.kg = kg;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public TFlight getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(TFlight idFlight) {
        this.idFlight = idFlight;
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
        hash += (idLuggage != null ? idLuggage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TLuggage)) {
            return false;
        }
        TLuggage other = (TLuggage) object;
        if ((this.idLuggage == null && other.idLuggage != null) || (this.idLuggage != null && !this.idLuggage.equals(other.idLuggage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.isec.fishtravel.common.TLuggage[ idLuggage=" + idLuggage + " ]";
    }
    
}
