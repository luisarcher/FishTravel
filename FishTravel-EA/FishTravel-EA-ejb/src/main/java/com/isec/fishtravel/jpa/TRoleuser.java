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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ljordao-dev
 */
@Entity
@Table(name = "roleuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRoleuser.findAll", query = "SELECT t FROM TRoleuser t")
    , @NamedQuery(name = "TRoleuser.findByIdRole", query = "SELECT t FROM TRoleuser t WHERE t.idRole = :idRole")
    , @NamedQuery(name = "TRoleuser.findByDescription", query = "SELECT t FROM TRoleuser t WHERE t.description = :description")})
public class TRoleuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_role")
    private Integer idRole;
    @Basic(optional = false)
    private String description;
    @OneToMany(mappedBy = "idRole")
    private Collection<TUser> tUserCollection;

    public TRoleuser() {
    }

    public TRoleuser(Integer idRole) {
        this.idRole = idRole;
    }

    public TRoleuser(Integer idRole, String description) {
        this.idRole = idRole;
        this.description = description;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<TUser> getTUserCollection() {
        return tUserCollection;
    }

    public void setTUserCollection(Collection<TUser> tUserCollection) {
        this.tUserCollection = tUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRole != null ? idRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRoleuser)) {
            return false;
        }
        TRoleuser other = (TRoleuser) object;
        if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.TRoleuser[ idRole=" + idRole + " ]";
    }
    
}
