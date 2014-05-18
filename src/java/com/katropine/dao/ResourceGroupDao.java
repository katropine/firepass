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
import javax.persistence.Query;

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
    public List<ResourceGroup> getAllResourceGroup(){
        return this.em.createNamedQuery("ResourceGroup.getAll").setParameter("name", "%%").getResultList();
    }
    
    @Override
    public List<ResourceGroup> getAllResourceGroup(String search, int offset, int limit) {
        Query query = this.em.createNamedQuery("ResourceGroup.getAll");
        if(search != null && search instanceof String){
            query.setParameter("name", "%"+search+"%");
        }else{
            query.setParameter("name", "%%");
        }
        return query.setFirstResult(offset).setMaxResults(limit).getResultList(); 
    }
    
    @Override
    public int countAllResourceGroup(String search){
        Query query = this.em.createNamedQuery("ResourceGroup.countAll");
        if(search != null && search instanceof String){
            query.setParameter("name", "%"+search+"%");
        }else{
            query.setParameter("name", "%%");
        }
        return ((Number)query.getSingleResult()).intValue();
    }        
    
    @Override
    public void getResourceGroup() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
