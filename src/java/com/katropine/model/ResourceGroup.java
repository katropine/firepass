/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kriss
 */
@Entity
@Table(name="resource_group")
@NamedQueries({
    @NamedQuery(name="ResourceGroup.getAll", query="SELECT e FROM ResourceGroup e")
})
public class ResourceGroup  implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(unique=true)
    private String name;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public ResourceGroup(String name) {
        this.name = name;
    }
    
    public ResourceGroup(){}
    
}
