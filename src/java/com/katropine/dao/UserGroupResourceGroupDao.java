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


package com.katropine.dao;

import com.katropine.model.UserGroupResourceGroup;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kriss
 */
@Stateless
public class UserGroupResourceGroupDao implements UserGroupResourceGroupDaoLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public UserGroupResourceGroup getUserGroupResourceGroup(int id) {
        return this.em.find(UserGroupResourceGroup.class, id);
    }
    @Override
    public UserGroupResourceGroup getIdByUserId(int userGrpId, int resGrpId) {
        StringBuffer query = new StringBuffer("from UserGroupResourceGroup acl ");
        query.append("where acl.userGroup.id=:userGroupId and acl.resourceGroup.id=:resourceGroupId");
        
        List<UserGroupResourceGroup> aclList = em.createQuery(query.toString())
                .setParameter("userGroupId", userGrpId)
                .setParameter("resourceGroupId", resGrpId)
                .getResultList();
        
        UserGroupResourceGroup acl = new UserGroupResourceGroup();
        if(!aclList.isEmpty()){
            acl = (UserGroupResourceGroup) aclList.get(0);
        }
                
        return acl; 
    }
    @Override
    public UserGroupResourceGroup getResource(int userGroupId, int resourceId){
        StringBuffer query = new StringBuffer("from UserGroupResourceGroup acl INNER JOIN acl.resourceGroup.resources r ");
        query.append("where acl.userGroup.id=:userGroupId AND r.id=:resourceId");
        
        List<UserGroupResourceGroup> aclList = em.createQuery(query.toString())
                .setParameter("userGroupId", userGroupId)
                .setParameter("resourceId", resourceId)
                .getResultList();
        
        UserGroupResourceGroup acl = new UserGroupResourceGroup();
        if(!aclList.isEmpty()){
            acl = (UserGroupResourceGroup) aclList.get(0);
        }
             
            System.out.println("acl: "+ acl.toString() );
        
        
        return acl; 
    }
}
