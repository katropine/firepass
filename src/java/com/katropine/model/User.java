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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name="User.authenticate", query="SELECT e FROM User e WHERE e.email=:email"),
    @NamedQuery(name="User.searchAll", query="SELECT e FROM User e WHERE e.firstname LIKE :fname OR e.lastname LIKE :lname OR e.email LIKE :email"),
    @NamedQuery(name="User.countAll", query="SELECT COUNT(e) FROM User e WHERE e.firstname LIKE :fname OR e.lastname LIKE :lname OR e.email LIKE :email")
})
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    @Column(name="time_zone")
    private String timeZone = "Etc/UTC";
    @Column(name="language", columnDefinition="VARCHAR(64) NOT NULL DEFAULT 'Etc/UTC'")
    private String lng;
    
    
    @Transient
    private String candidatePassword;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date created = new Date();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usergroup_id")
    private UserGroup userGroup = new UserGroup();
    
    
    public User(int id, String firstname, String lastname, String email, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());   
        // user
        this.userGroup.setId(3);
        this.lng = (new Language()).getCode();
        this.timeZone = "Etc/UTC";
    }
    
    public User(){
        // user
        this.userGroup.setId(3);
        this.lng = (new Language()).getCode();
        this.timeZone = "Etc/UTC";
    }

    
    
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

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroups(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLanguage() {
        return lng;
    }

    public void setLanguage(String language) {
        this.lng = language;
    }    
    
    @Override
    public String toString(){
        return this.firstname+" "+this.lastname;
    }
    
    
}
