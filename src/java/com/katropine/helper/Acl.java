/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.helper;

import com.katropine.dao.AccessControlListDaoLocal;
import com.katropine.model.AccessControlList;
import com.katropine.model.User;
import javax.ejb.EJB;

/**
 *
 * @author kriss
 */
public class Acl {
    
    
    @EJB
    private final AccessControlListDaoLocal aclDao;
    
    private User user = null;
    
    public Acl(User user, AccessControlListDaoLocal aclDao){
        this.user = user;
        this.aclDao = aclDao;
        int userId = this.user.getId();
        System.out.println("acl_user_id: "+userId);
    }
    
    protected AccessControlList getFromDao(Permission permission) {
        AccessControlList acl = aclDao.getByUserGroupAndPermission(user.getUserGroup().getId(), permission);
        return acl;
    }

    public boolean allowView(Permission permission){
        return this.getFromDao(permission).isCanView();
    }
    
    public boolean allowInsert(Permission permission){
        return this.getFromDao(permission).isCanInsert();
    }
    
    public boolean allowUpdate(Permission permission){
        return this.getFromDao(permission).isCanUpdate();
    }
    
    public boolean allowDelete(Permission permission){
        return this.getFromDao(permission).isCanDelete();
    }
}
