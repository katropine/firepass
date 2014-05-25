/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.model.ResourceGroup;
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
    
}
