package com.katropine.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author kriss
 */
@Entity
@Table(name="user_group")
@NamedQueries({
    @NamedQuery(name="UserGroup.getAllForAdmin", query="SELECT e FROM UserGroup e"),
    @NamedQuery(name="UserGroup.getAll", query="SELECT e FROM UserGroup e WHERE e.id != 1")
})
public class UserGroup implements Serializable{
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String name;
    
    @Column(columnDefinition="BOOLEAN NOT NULL DEFAULT '0'")
    private boolean locked = false;
    
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="userGroup")
    private List<User> users;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userGroup", fetch=FetchType.EAGER, orphanRemoval=true)
    @JoinColumn(name = "usergroup_id") 
    private List<AccessControlList> acl;
    
    public UserGroup(){}
    
    public UserGroup(String name){
        this.name = name;
    }
    
    public UserGroup(int id, String name){
        this.id = id;
        this.name = name;
    }

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public List<AccessControlList> getAcl() {
        return acl;
    }

    public void setAcl(List<AccessControlList> acl) {
        this.acl = acl;
    }
    
    
}
