/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.model;

/**
 *
 * @author kriss
 */
import java.util.Date;
 
public class UserSession {
    private User user;
    private Date loginTime;
    private String timeZone;
    
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
    public Date getLoginTime() {
        return loginTime;
    }
 
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    
}