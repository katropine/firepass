/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.model.UserGroupResourceGroup;
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
    
}
