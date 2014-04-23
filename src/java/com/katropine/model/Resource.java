/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.model;

import java.io.Serializable;
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
    
    @Column
    private String body = "";
    
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ResourceGroup getGroup() {
        return group;
    }

    public void setGroup(ResourceGroup group) {
        this.group = group;
    }
    
    
    
}
