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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ljordao-dev
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUser.findAll", query = "SELECT t FROM TUser t")
    , @NamedQuery(name = "TUser.findByIdUser", query = "SELECT t FROM TUser t WHERE t.idUser = :idUser")
    , @NamedQuery(name = "TUser.findByLogin", query = "SELECT t FROM TUser t WHERE t.login = :login")
    , @NamedQuery(name = "TUser.findByPasswd", query = "SELECT t FROM TUser t WHERE t.passwd = :passwd")
    , @NamedQuery(name = "TUser.findByBirthdate", query = "SELECT t FROM TUser t WHERE t.birthdate = :birthdate")
    , @NamedQuery(name = "TUser.findByCredits", query = "SELECT t FROM TUser t WHERE t.credits = :credits")
    , @NamedQuery(name = "TUser.findByDateReg", query = "SELECT t FROM TUser t WHERE t.dateReg = :dateReg")
    , @NamedQuery(name = "TUser.findByNameUser", query = "SELECT t FROM TUser t WHERE t.nameUser = :nameUser")
        
    , @NamedQuery(name = "TUser.validateUser", query = "SELECT t FROM TUser t WHERE t.login = :login AND t.passwd = :passwd")
})
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Basic(optional = false)
    private String login;
    @Basic(optional = false)
    private String passwd;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Basic(optional = false)
    private float credits = 50;
    @Column(name = "date_reg", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReg;
    @Column(name = "name_user")
    private String nameUser;
    @OneToMany(mappedBy = "idUser")
    private Collection<Tcomment> tcommentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<TLuggage> tLuggageCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<TPurchase> tPurchaseCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<TRating> tRatingCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<TFavorite> tFavoriteCollection;
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    @ManyToOne
    private TRoleuser idRole;
    
    public TUser() {
    }

    public TUser(Integer idUser) {
        this.idUser = idUser;
    }

    public TUser(Integer idUser, String login, String passwd, Date birthdate, float credits) {
        this.idUser = idUser;
        this.login = login;
        this.passwd = passwd;
        this.birthdate = birthdate;
        this.credits = credits;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    @XmlTransient
    public Collection<Tcomment> getTcommentCollection() {
        return tcommentCollection;
    }

    public void setTcommentCollection(Collection<Tcomment> tcommentCollection) {
        this.tcommentCollection = tcommentCollection;
    }

    @XmlTransient
    public Collection<TLuggage> getTLuggageCollection() {
        return tLuggageCollection;
    }

    public void setTLuggageCollection(Collection<TLuggage> tLuggageCollection) {
        this.tLuggageCollection = tLuggageCollection;
    }

    @XmlTransient
    public Collection<TPurchase> getTPurchaseCollection() {
        return tPurchaseCollection;
    }

    public void setTPurchaseCollection(Collection<TPurchase> tPurchaseCollection) {
        this.tPurchaseCollection = tPurchaseCollection;
    }

    @XmlTransient
    public Collection<TRating> getTRatingCollection() {
        return tRatingCollection;
    }

    public void setTRatingCollection(Collection<TRating> tRatingCollection) {
        this.tRatingCollection = tRatingCollection;
    }

    @XmlTransient
    public Collection<TFavorite> getTFavoriteCollection() {
        return tFavoriteCollection;
    }

    public void setTFavoriteCollection(Collection<TFavorite> tFavoriteCollection) {
        this.tFavoriteCollection = tFavoriteCollection;
    }

    public TRoleuser getIdRole() {
        return idRole;
    }

    public void setIdRole(TRoleuser idRole) {
        this.idRole = idRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUser)) {
            return false;
        }
        TUser other = (TUser) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idUser + " " + login + " " + nameUser;
    }

    
}
