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
 * @author LM
 */
@Entity
@Table(name = "favorite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TFavorite.findAll", query = "SELECT t FROM TFavorite t")
    , @NamedQuery(name = "TFavorite.findByIdFavorite", query = "SELECT t FROM TFavorite t WHERE t.idFavorite = :idFavorite")
    , @NamedQuery(name = "TFavorite.findByDateFavorite", query = "SELECT t FROM TFavorite t WHERE t.dateFavorite = :dateFavorite")})
public class TFavorite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_favorite")
    private Integer idFavorite;
    @Column(name = "date_favorite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFavorite;
    @JoinColumn(name = "id_flight", referencedColumnName = "id_flight")
    @ManyToOne
    private TFlight idFlight;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private TUser idUser;

    public TFavorite() {
    }

    public TFavorite(Integer idFavorite) {
        this.idFavorite = idFavorite;
    }

    public Integer getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(Integer idFavorite) {
        this.idFavorite = idFavorite;
    }

    public Date getDateFavorite() {
        return dateFavorite;
    }

    public void setDateFavorite(Date dateFavorite) {
        this.dateFavorite = dateFavorite;
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
        hash += (idFavorite != null ? idFavorite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TFavorite)) {
            return false;
        }
        TFavorite other = (TFavorite) object;
        if ((this.idFavorite == null && other.idFavorite != null) || (this.idFavorite != null && !this.idFavorite.equals(other.idFavorite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.isec.fishtravel.common.TFavorite[ idFavorite=" + idFavorite + " ]";
    }
    
}
