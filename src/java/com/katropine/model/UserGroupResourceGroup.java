/**
* @package FirePass - katropine
* @author Kristian Beres <kristian@katropine.com>
* @copyright Katropine (c) 2014, www.katropine.com
* @since May 23, 2014
* @licence MIT
*
* Copyright (c) 2014 Katropine, http://www.katropine.com/
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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * @author kriss
 */
@Entity
@Table(name="user_group_resource_group")
public class UserGroupResourceGroup implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(targetEntity = UserGroup.class, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="usergroup_id")
    private UserGroup userGroup;
    
    @ManyToOne(targetEntity = ResourceGroup.class, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="resourcegroup_id")
    private ResourceGroup resourceGroup;
    
    @Column(name="can_view")
    private boolean canView;
    
    @Column(name="can_insert")
    private boolean canInsert;
    
    @Column(name="can_update")
    private boolean canUpdate;
    
    @Column(name="can_delete")
    private boolean canDelete;

    public UserGroupResourceGroup() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public ResourceGroup getResourceGroup() {
        return resourceGroup;
    }

    public void setResourceGroup(ResourceGroup resourceGroup) {
        this.resourceGroup = resourceGroup;
    }

    public boolean isCanView() {
        return canView;
    }

    public void setCanView(boolean canView) {
        this.canView = canView;
    }

    public boolean isCanInsert() {
        return canInsert;
    }

    public void setCanInsert(boolean canInsert) {
        this.canInsert = canInsert;
    }

    public boolean isCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
    
    @Override
    public String toString(){
        return "id: "+id+" userGroup_id: "+userGroup.getId()+" resourceGroup_id: "+resourceGroup.getId()+" cv: "+canView+" ci: "+canInsert+" cu: "+canUpdate+" cd: "+canDelete;
    }
}
