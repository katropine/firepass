package com.katropine.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kriss
 */
@Entity
@Table(name="user_group")
@NamedQueries({
    @NamedQuery(name="UserGroup.getAll", query="SELECT e FROM UserGroup e")
})
public class UserGroup implements Serializable{
    
    @Id
    @Column(name="id")
    private int id;
    
    @Column
    private String name;
    
    @JoinTable(name="user_usergroup",
        joinColumns = { 
            @JoinColumn(name = "group_id", referencedColumnName = "group_id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id")
        }
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;
    
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
    
    
}
