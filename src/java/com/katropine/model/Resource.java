/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author kriss
 */
@Entity
@Table(name="resource")
@NamedQueries({
    @NamedQuery(name="Resource.getAll", query="SELECT e FROM Resource e")
})
public class Resource implements Serializable{
    
    @Id
    private int id;
    
    @Column(unique=true)
    private String title;
    
    @Column(length=100000)
    private byte[] body;
    
    @Column(name="body_iv", length=100000)
    private byte[] bodyIv;
    
    // just for presentation
    @Transient
    private String bodyMessage; 
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date created = new Date();
    
    @ManyToOne
    private ResourceGroup group = new ResourceGroup();

    public Resource(int id, String title) {
        this.id = id;
        this.title = title;
    }
    
    public Resource(String title) {
        this.title = title;
    }
    
    public Resource() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getBody() {
        return this.body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public ResourceGroup getGroup() {
        return group;
    }

    public void setGroup(ResourceGroup group) {
        this.group = group;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public byte[] getBodyIv() {
        return bodyIv;
    }

    public void setBodyIv(byte[] bodyIv) {
        this.bodyIv = bodyIv;
    }

    public String getBodyMessage() {
        return bodyMessage;
    }

    public void setBodyMessage(String bodyMessage) {
        this.bodyMessage = bodyMessage;
    }
    
    
    
}
