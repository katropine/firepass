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
import java.util.ArrayList;
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
@Table(name="resource_group")
@NamedQueries({
    @NamedQuery(name="ResourceGroup.getAll", query="SELECT e FROM ResourceGroup e WHERE e.name LIKE :name"),
    @NamedQuery(name="ResourceGroup.countAll", query="SELECT COUNT(e) FROM ResourceGroup e WHERE e.name LIKE :name"),
    //@NamedQuery(name="ResourceGroup.getAllCanView", query="SELECT e FROM ResourceGroup e WHERE e.aclUserResourceGroups.userGroup=:userGroup AND e.aclUserResourceGroups.canView=:canView"),
    @NamedQuery(name="ResourceGroup.getAllCanView", query="SELECT e FROM ResourceGroup e INNER JOIN e.aclUserResourceGroups acl WHERE acl.userGroup=:userGroup AND acl.canView=:canView")
})
public class ResourceGroup  implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(unique=true)
    private String name;
    
    @OneToMany(mappedBy = "resourceGroup", cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    @JoinColumn(name = "resourcegroup_id")
    private List<UserGroupResourceGroup> aclUserResourceGroups;
    
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="group")
    private List<Resource> resources;
    
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

    public List<UserGroupResourceGroup> getAclUserResourceGroups() {
        return aclUserResourceGroups;
    }

    public void setAclUserResourceGroups(List<UserGroupResourceGroup> aclUserResourceGroups) {
        this.aclUserResourceGroups = aclUserResourceGroups;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
    
    
    
    
}
