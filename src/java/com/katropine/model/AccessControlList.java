/**
* @package FirePass - katropine
* @author Kristian Beres <kristian@katropine.com>
* @copyright Katropine (c) 2014, www.katropine.com
* @since March 24, 2014
* @licence MIT
*
* Copyright (c) 2014 Katropine - Kristian Beres, http://www.katropine.com/
*
* Permission is hereby granted, free of charge, to any person obtaining
* a copy of this software and associated documentation files (the
* "Software"), to deal in the Software without restriction, including
* without limitation the rights to use, copy, modify, merge, publish,
* distribute, sublicense, and/or sell copies of the Software, and to
* permit persons to whom the Software is furnished to do so, subject to
* the following conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
* MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
* LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
* OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
* WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.katropine.model;

import com.katropine.helper.Permission;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kriss
 */
@Entity
@Table(name="access_control_list")
@NamedQueries({
    @NamedQuery(name="AccessControlList.getAll", query="SELECT e FROM AccessControlList e"),
    @NamedQuery(name="AccessControlList.getAllByUserGroupId", query="SELECT e FROM AccessControlList e WHERE e.userGroup.id=:userGroupId ")
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
 
    @ManyToOne(targetEntity = UserGroup.class, cascade = CascadeType.ALL)
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