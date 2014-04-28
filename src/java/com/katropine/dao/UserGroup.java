/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kriss
 */
@Stateless
public class UserGroup implements UserGroupLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUserGroup(UserGroup group) {
        this.em.persist(group);
    }

    @Override
    public void editUserGroup(UserGroup group) {
        this.em.merge(group);
    }

    @Override
    public void deleteUserGroup(int id) {
        this.em.remove(this.getUserGroup(id));
    }

    @Override
    public UserGroup getUserGroup(int id) {
        return this.em.find(UserGroup.class, id);
    }

    @Override
    public List<UserGroup> getAllUserGroups() {
        return this.em.createNamedQuery("UserGroup.getAll").getResultList();
    }
    

}
