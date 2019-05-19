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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ljordao-dev
 */
@Entity
@Table(name = "rating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRating.findAll", query = "SELECT t FROM TRating t")
    , @NamedQuery(name = "TRating.findByIdRating", query = "SELECT t FROM TRating t WHERE t.idRating = :idRating")
    , @NamedQuery(name = "TRating.findByRateDestination", query = "SELECT t FROM TRating t WHERE t.rateDestination = :rateDestination")
    , @NamedQuery(name = "TRating.findByRateCompany", query = "SELECT t FROM TRating t WHERE t.rateCompany = :rateCompany")
    , @NamedQuery(name = "TRating.findByDateRate", query = "SELECT t FROM TRating t WHERE t.dateRate = :dateRate")})
public class TRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rating")
    private Integer idRating;
    @Column(name = "rate_destination")
    private Short rateDestination;
    @Column(name = "rate_company")
    private Short rateCompany;
    @Column(name = "date_rate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRate;
    @JoinColumn(name = "id_flight", referencedColumnName = "id_flight")
    @ManyToOne
    private TFlight idFlight;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private TUser idUser;

    public TRating() {
    }

    public TRating(Integer idRating) {
        this.idRating = idRating;
    }

    public Integer getIdRating() {
        return idRating;
    }

    public void setIdRating(Integer idRating) {
        this.idRating = idRating;
    }

    public Short getRateDestination() {
        return rateDestination;
    }

    public void setRateDestination(Short rateDestination) {
        this.rateDestination = rateDestination;
    }

    public Short getRateCompany() {
        return rateCompany;
    }

    public void setRateCompany(Short rateCompany) {
        this.rateCompany = rateCompany;
    }

    public Date getDateRate() {
        return dateRate;
    }

    public void setDateRate(Date dateRate) {
        this.dateRate = dateRate;
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
        hash += (idRating != null ? idRating.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRating)) {
            return false;
        }
        TRating other = (TRating) object;
        if ((this.idRating == null && other.idRating != null) || (this.idRating != null && !this.idRating.equals(other.idRating))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.TRating[ idRating=" + idRating + " ]";
    }
    
}
