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
    @NamedQuery(name="UserGroup.getAllForAdmin", query="SELECT e FROM UserGroup e WHERE e.name LIKE :name"),
    @NamedQuery(name="UserGroup.countAllForAdmin", query="SELECT COUNT(e) FROM UserGroup e WHERE e.name LIKE :name"),
    @NamedQuery(name="UserGroup.getAll", query="SELECT e FROM UserGroup e WHERE e.id != 1 AND e.name LIKE :name"),
    @NamedQuery(name="UserGroup.countAll", query="SELECT COUNT(e) FROM UserGroup e WHERE e.id != 1 AND e.name LIKE :name")
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
    
    @OneToMany(mappedBy="userGroup", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "usergroup_id") 
    private List<AccessControlList> acl;
    
    @OneToMany(mappedBy="userGroup", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "usergroup_id") 
    private List<UserGroupResourceGroup> aclUserResourceGroups;
    
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

    public List<UserGroupResourceGroup> getAclUserResourceGroups() {
        return aclUserResourceGroups;
    }

    public void setAclUserResourceGroups(List<UserGroupResourceGroup> aclUserResourceGroups) {
        this.aclUserResourceGroups = aclUserResourceGroups;
    }
    
}
