/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.helper.Permission;
import com.katropine.model.AccessControlList;
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
 
}
