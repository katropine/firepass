/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.model.UserGroup;
import com.katropine.model.UserSession;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author kriss
 */
@Stateless
public class UserGroupDao implements UserGroupDaoLocal {

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
    public UserGroup getUserGroup() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<UserGroup> getAllUserGroups(UserSession us, String search) {
        return this.getAllUserGroups(us, search, 0, 9999999);
    }
    
    @Override
    public List<UserGroup> getAllUserGroups(UserSession us, String search, int offset, int limit) {
        if(us.getUser().getUserGroup().getId() == 1){
            return this.em.createNamedQuery("UserGroup.getAllForAdmin")
                    .setParameter("name", "%"+search+"%")
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        }
        return this.em.createNamedQuery("UserGroup.getAll")
                .setParameter("name", "%"+search+"%")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    @Override 
    public int countAllUserGroups(UserSession us, String search){
        if(us.getUser().getUserGroup().getId() == 1){
            
            Query query = this.em.createNamedQuery("UserGroup.countAllForAdmin");
            if(search!=null && !search.isEmpty()){
                query.setParameter("name", "%"+search+"%");
            }else{
                query.setParameter("name", "%%");
            }
            return ((Number)query.getSingleResult()).intValue();
            
        }
        
        Query query = this.em.createNamedQuery("UserGroup.countAll");
        if(search!=null && !search.isEmpty()){
            query.setParameter("name", "%" + search + "%");
        }else{
            query.setParameter("name", "%%");
        }
        return ((Number) query.getSingleResult()).intValue();
    }

}
