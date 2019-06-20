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
@Table(name = "company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCompany.findAll", query = "SELECT t FROM TCompany t")
    , @NamedQuery(name = "TCompany.findByIdCompany", query = "SELECT t FROM TCompany t WHERE t.idCompany = :idCompany")
    , @NamedQuery(name = "TCompany.findByNameCompany", query = "SELECT t FROM TCompany t WHERE t.nameCompany = :nameCompany")})
public class TCompany implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_company")
    private Integer idCompany;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "name_company")
    private String nameCompany;
    @OneToMany(mappedBy = "idCompany")
    private Collection<TFlight> tFlightCollection;

    public TCompany() {
    }

    public TCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public TCompany(Integer idCompany, String nameCompany) {
        this.idCompany = idCompany;
        this.nameCompany = nameCompany;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    @XmlTransient
    public Collection<TFlight> getTFlightCollection() {
        return tFlightCollection;
    }

    public void setTFlightCollection(Collection<TFlight> tFlightCollection) {
        this.tFlightCollection = tFlightCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompany != null ? idCompany.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCompany)) {
            return false;
        }
        TCompany other = (TCompany) object;
        if ((this.idCompany == null && other.idCompany != null) || (this.idCompany != null && !this.idCompany.equals(other.idCompany))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nameCompany;
    }
    
}
