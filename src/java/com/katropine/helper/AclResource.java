/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.helper;

import com.katropine.dao.UserGroupResourceGroupDaoLocal;
import com.katropine.model.Resource;
import com.katropine.model.User;
import com.katropine.model.UserGroupResourceGroup;
import javax.ejb.EJB;

/**
 *
 * @author kriss
 */
public class AclResource {
    @EJB
    private final UserGroupResourceGroupDaoLocal usrResGroupDao;
    
    private User user = null;
    
    public AclResource(User user, UserGroupResourceGroupDaoLocal aclDao){
        this.user = user;
        this.usrResGroupDao = aclDao;
    }
    
    protected UserGroupResourceGroup getFromDao(Resource res) { 
        UserGroupResourceGroup acl = this.usrResGroupDao.getResource(this.user.getUserGroup().getId(), res.getId());
        return acl;
    }

    public boolean allowView(Resource res){
        return this.getFromDao(res).isCanView();
    }
    
    public boolean allowInsert(Resource res){
        return this.getFromDao(res).isCanInsert();
    }
    
    public boolean allowUpdate(Resource res){
        return this.getFromDao(res).isCanUpdate();
    }
    
    public boolean allowDelete(Resource res){
        return this.getFromDao(res).isCanDelete();
    }
   
}