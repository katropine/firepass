/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.model.ResourceGroup;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kriss
 */
@Stateless
public class ResourceGroupDao implements ResourceGroupDaoLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addResourceGroup(ResourceGroup resourceGroup) {
        this.em.persist(resourceGroup);
    }
    
    @Override
    public void editResourceGroup(ResourceGroup resourceGroup) {
        this.em.merge(resourceGroup);
    }
    
    @Override
    public void deleteResourceGroup(int id) {
        this.em.remove(this.getResourceGroup(id));
    }

    @Override
    public ResourceGroup getResourceGroup(int id) {
        return this.em.find(ResourceGroup.class, id);
    }
  
    @Override
    public List<ResourceGroup> getAllResourceGroup() {
        return this.em.createNamedQuery("ResourceGroup.getAll").getResultList();
    }

    @Override
    public void getResourceGroup() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
