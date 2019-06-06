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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LM
 */
@Entity
@Table(name = "tcomment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcomment.findAll", query = "SELECT t FROM Tcomment t")
    , @NamedQuery(name = "Tcomment.findByIdComment", query = "SELECT t FROM Tcomment t WHERE t.idComment = :idComment")
    , @NamedQuery(name = "Tcomment.findByTxt", query = "SELECT t FROM Tcomment t WHERE t.txt = :txt")
    , @NamedQuery(name = "Tcomment.findByDateComment", query = "SELECT t FROM Tcomment t WHERE t.dateComment = :dateComment")})
public class Tcomment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comment")
    private Integer idComment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "txt")
    private String txt;
    @Column(name = "date_comment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateComment;
    @JoinColumn(name = "id_flight", referencedColumnName = "id_flight")
    @ManyToOne
    private TFlight idFlight;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private TUser idUser;

    public Tcomment() {
    }

    public Tcomment(Integer idComment) {
        this.idComment = idComment;
    }

    public Tcomment(Integer idComment, String txt) {
        this.idComment = idComment;
        this.txt = txt;
    }

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
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
        hash += (idComment != null ? idComment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tcomment)) {
            return false;
        }
        Tcomment other = (Tcomment) object;
        if ((this.idComment == null && other.idComment != null) || (this.idComment != null && !this.idComment.equals(other.idComment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.isec.fishtravel.common.Tcomment[ idComment=" + idComment + " ]";
    }
    
}
