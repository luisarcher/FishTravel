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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LM
 */
@Entity
@Table(name = "flight", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_flight"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TFlight.findAll", query = "SELECT t FROM TFlight t")
    , @NamedQuery(name = "TFlight.findByIdFlight", query = "SELECT t FROM TFlight t WHERE t.idFlight = :idFlight")
    , @NamedQuery(name = "TFlight.findByIdStatus", query = "SELECT t FROM TFlight t WHERE t.idStatus = :idStatus")
    , @NamedQuery(name = "TFlight.findByNameFlight", query = "SELECT t FROM TFlight t WHERE t.nameFlight = :nameFlight")
    , @NamedQuery(name = "TFlight.findByPrice", query = "SELECT t FROM TFlight t WHERE t.price = :price")
    , @NamedQuery(name = "TFlight.findByTimeDeparture", query = "SELECT t FROM TFlight t WHERE t.timeDeparture = :timeDeparture")
    , @NamedQuery(name = "TFlight.findByTimeArrival", query = "SELECT t FROM TFlight t WHERE t.timeArrival = :timeArrival")
    , @NamedQuery(name = "TFlight.findByMaxSeats", query = "SELECT t FROM TFlight t WHERE t.maxSeats = :maxSeats")
    , @NamedQuery(name = "TFlight.findByAvailSeats", query = "SELECT t FROM TFlight t WHERE t.availSeats = :availSeats")})
public class TFlight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_flight", nullable = false)
    private Integer idFlight;
    @Column(name = "id_status")
    private Integer idStatus;
    @Size(max = 64)
    @Column(name = "name_flight", length = 64)
    private String nameFlight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price", nullable = false)
    private float price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_departure", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeDeparture;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_arrival", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeArrival;
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_seats", nullable = false)
    private int maxSeats;
    @Basic(optional = false)
    @NotNull
    @Column(name = "avail_seats", nullable = false)
    private int availSeats;
    @JoinColumn(name = "from_airport", referencedColumnName = "id_airport")
    @ManyToOne
    private TAirport fromAirport;
    @JoinColumn(name = "to_airport", referencedColumnName = "id_airport")
    @ManyToOne
    private TAirport toAirport;
    @JoinColumn(name = "id_company", referencedColumnName = "id_company")
    @ManyToOne
    private TCompany idCompany;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tFlight")
    private Collection<Tcontains> tcontainsCollection;

    public TFlight() {
    }

    public TFlight(Integer idFlight) {
        this.idFlight = idFlight;
    }

    public TFlight(Integer idFlight, float price, Date timeDeparture, Date timeArrival, int maxSeats, int availSeats) {
        this.idFlight = idFlight;
        this.price = price;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.maxSeats = maxSeats;
        this.availSeats = availSeats;
    }

    public Integer getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(Integer idFlight) {
        this.idFlight = idFlight;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getNameFlight() {
        return nameFlight;
    }

    public void setNameFlight(String nameFlight) {
        this.nameFlight = nameFlight;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(Date timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public Date getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(Date timeArrival) {
        this.timeArrival = timeArrival;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public int getAvailSeats() {
        return availSeats;
    }

    public void setAvailSeats(int availSeats) {
        this.availSeats = availSeats;
    }

    public TAirport getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(TAirport fromAirport) {
        this.fromAirport = fromAirport;
    }

    public TAirport getToAirport() {
        return toAirport;
    }

    public void setToAirport(TAirport toAirport) {
        this.toAirport = toAirport;
    }

    public TCompany getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(TCompany idCompany) {
        this.idCompany = idCompany;
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
        hash += (idFlight != null ? idFlight.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TFlight)) {
            return false;
        }
        TFlight other = (TFlight) object;
        if ((this.idFlight == null && other.idFlight != null) || (this.idFlight != null && !this.idFlight.equals(other.idFlight))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.isec.fishtravel.jpa.TFlight[ idFlight=" + idFlight + " ]";
    }
    
}
