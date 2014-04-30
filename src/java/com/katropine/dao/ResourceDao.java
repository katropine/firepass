/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.model.Resource;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kriss
 */
@Stateless
public class ResourceDao implements ResourceDaoLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addResource(Resource resource) {
        this.em.persist(resource);
    }

    @Override
    public void editResource(Resource resource) {
        this.em.merge(resource);
    }

    @Override
    public void deleteResource(int id) {
        this.em.remove(this.getResource(id));
    }

    @Override
    public Resource getResource(int id) {
        return this.em.find(Resource.class, id);
    }
    
    
    
    @Override
    public List<Resource> getAllResources() {
        return this.em.createNamedQuery("Resource.getAll").getResultList();
    }

    @Override
    public void getResource() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Resource> getAllResourcesByGroup(int groupId) {
        List<Resource> res =this.em.createNamedQuery("Resource.getAllByGroup")
                    .setParameter("groupid", groupId)
                    .getResultList();
        return res;
    }
    
    
    
    
    
}
