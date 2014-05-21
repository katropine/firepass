/**
* @package FirePass - katropine
* @author Kristian Beres <kristian@katropine.com>
* @copyright Katropine (c) 2014, www.katropine.com
* @since March 24, 2014
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

package com.katropine.dao;

import com.katropine.helper.Permission;
import com.katropine.model.AccessControlList;
import com.katropine.model.UserGroup;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author kriss
 */
@Stateless(name="AccessControlListDao", mappedName="AccessControlListDao")
public class AccessControlListDao implements AccessControlListDaoLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public AccessControlList getByUserGroupAndPermission(int groupId, Permission permission) {
        System.out.println("groupId: "+groupId+" permission: "+permission);
        StringBuffer query = new StringBuffer("from AccessControlList acl ");
        query.append("where acl.userGroup.id=:userGroupId and acl.permission=:permission");
        
        Integer gid = new Integer(groupId); 
        
        List<AccessControlList> aclList = em.createQuery(query.toString())
                .setParameter("userGroupId", gid)
                .setParameter("permission", permission)
                .getResultList();
        
        AccessControlList acl = new AccessControlList();
        if(!aclList.isEmpty()){
            acl = (AccessControlList) aclList.get(0);
        }
 
        return acl; 
    }

    @Override
    public List<AccessControlList> getAclByUserGroup(int userGroupId) {
        return this.em.createNamedQuery("AccessControlList.getAllByUserGroupId")
                .setParameter("userGroupId", userGroupId)
                .getResultList();
    }

    @Override
    public void editAclByUserGroup(UserGroup userGroup) {
        
    }

    @Override
    public void addAclList(ArrayList<AccessControlList> acls) {

        for(AccessControlList acl : acls){
            this.em.persist(acl);
            this.em.flush();
        }
        this.em.clear();
        
        
    }
    
    
    
    
 
}
