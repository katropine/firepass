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
import javax.persistence.Query;

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
    public List<Resource> getAllResources(String search, int offset, int limit) {
        Query query = this.em.createNamedQuery("Resource.getAll");
        if(search!=null && !search.isEmpty()){
            query.setParameter("title", "%" + search + "%");
        }else{
            query.setParameter("title", "%%");
        }
        
        return query.setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    @Override
    public int countAllResources(String search) {
        Query query = this.em.createNamedQuery("Resource.countAll");
        if(search!=null && !search.isEmpty()){
            query.setParameter("title", "%" + search + "%");
        }else{
            query.setParameter("title", "%%");
        }
        return ((Number) query.getSingleResult()).intValue();
    }

    @Override
    public void getResource() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Resource> getAllResourcesByGroup(int groupId, int offset, int limit) {
        List<Resource> res =this.em.createNamedQuery("Resource.getAllByGroup")
                .setParameter("groupid", groupId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        return res;
    }
    @Override
    public int countAllResourcesByGroup(int groupId){
        
        Query query = this.em.createNamedQuery("Resource.countAllByGroup");
        query.setParameter("groupid", groupId);
       
        return ((Number) query.getSingleResult()).intValue();
    }
    
    
    
}
