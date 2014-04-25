package com.katropine.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.katropine.libs.BCrypt;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;
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
import javax.persistence.Transient;
/**
 *
 * @author kriss
 */
@Entity
@Table(name="user")
@NamedQueries({
    @NamedQuery(name="User.getAll", query="SELECT e FROM User e"), 
    @NamedQuery(name="User.authenticate", query="SELECT e FROM User e WHERE e.email=:email")
})
public class User implements Serializable{
    
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private String password;
    
    @Transient
    private String candidatePassword;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date created = new Date();

    public User(int id, String firstname, String lastname, String email, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());    
    }
    
    public User(){}

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = hashed;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCandidatePassword() {
        return candidatePassword;
    }

    public void setCandidatePassword(String candidatePassword) {
        this.candidatePassword = candidatePassword;
    }
    
    
    
    @Override
    public String toString(){
        return this.firstname+" "+this.lastname;
    }
    
    
}
