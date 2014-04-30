/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.katropine.helper.Permission;
import java.io.Serializable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author kriss
 */
@Entity
@Table(name="access_control_list")
@NamedQueries({
    @NamedQuery(name="AccessControlList.getAll", query="SELECT e FROM AccessControlList e")
})
public class AccessControlList implements Serializable{
    
    private int id;
    private Permission permission;
    private UserGroup userGroup;
    private boolean canView;
    private boolean canInsert;
    private boolean canUpdate;
    private boolean canDelete;
 
    @Id
    @GeneratedValue
    @Column(name="id")
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    @Enumerated(value = EnumType.STRING)
    @Column(name="permission")
    public Permission getPermission() {
        return permission;
    }
 
    public void setPermission(Permission permission) {
        this.permission = permission;
    }
 
    @ManyToOne(targetEntity = UserGroup.class)
    @JoinColumn(name="usergroup_id")
    public UserGroup getUserGroup() {
        return userGroup;
    }
 
    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
 
    @Basic
    @Column(name="can_view")
    public boolean isCanView() {
        return canView;
    }
 
    public void setCanView(boolean canView) {
        this.canView = canView;
    }
 
    @Basic
    @Column(name="can_insert")
    public boolean isCanInsert() {
        return canInsert;
    }
 
    public void setCanInsert(boolean canInsert) {
        this.canInsert = canInsert;
    }
 
    @Basic
    @Column(name="can_update")
    public boolean isCanUpdate() {
        return canUpdate;
    }
 
    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }
 
    @Basic
    @Column(name="can_delete")
    public boolean isCanDelete() {
        return canDelete;
    }
 
    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
    
    public AccessControlList(){}
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[ID=" + this.getId());
        sb.append(", PERMISSION=" + this.getPermission());
        sb.append(", USERTYPE=" + this.getUserGroup().getName());
        sb.append(", CAN_VIEW=" + this.isCanView());
        sb.append(", CAN_INSERT=" + this.isCanInsert());
        sb.append(", CAN_UPDATE=" + this.isCanUpdate());
        sb.append(", CAN_DELETE=" + this.isCanDelete());
        sb.append("]");
 
        return sb.toString();
    }
}