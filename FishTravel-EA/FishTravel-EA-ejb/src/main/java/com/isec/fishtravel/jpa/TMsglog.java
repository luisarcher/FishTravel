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
@Table(name = "msglog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMsglog.findAll", query = "SELECT t FROM TMsglog t")
    , @NamedQuery(name = "TMsglog.findByIdMsg", query = "SELECT t FROM TMsglog t WHERE t.idMsg = :idMsg")
    , @NamedQuery(name = "TMsglog.findByMsg", query = "SELECT t FROM TMsglog t WHERE t.msg = :msg")
    , @NamedQuery(name = "TMsglog.findByDateMsg", query = "SELECT t FROM TMsglog t WHERE t.dateMsg = :dateMsg")})
public class TMsglog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_msg")
    private Integer idMsg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "msg")
    private String msg;
    @Column(name = "date_msg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMsg;

    public TMsglog() {
    }

    public TMsglog(Integer idMsg) {
        this.idMsg = idMsg;
    }

    public TMsglog(Integer idMsg, String msg) {
        this.idMsg = idMsg;
        this.msg = msg;
    }

    public Integer getIdMsg() {
        return idMsg;
    }

    public void setIdMsg(Integer idMsg) {
        this.idMsg = idMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDateMsg() {
        return dateMsg;
    }

    public void setDateMsg(Date dateMsg) {
        this.dateMsg = dateMsg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMsg != null ? idMsg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMsglog)) {
            return false;
        }
        TMsglog other = (TMsglog) object;
        if ((this.idMsg == null && other.idMsg != null) || (this.idMsg != null && !this.idMsg.equals(other.idMsg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.isec.fishtravel.common.TMsglog[ idMsg=" + idMsg + " ]";
    }
    
}
