/**
* @package FirePass - katropine
* @author Kristian Beres <kristian@katropine.com>
* @copyright Katropine (c) 2014, www.katropine.com
* @since March 24, 2014
* @licence MIT
*
* Copyright (c) 2014 Katropine, http://www.katropine.com/
*
* Permission is hereby granted, free of charge, to any person obtaining
* a copy of this software and associated documentation files (the
* "Software"), to deal in the Software without restriction, including
* without limitation the rights to use, copy, modify, merge, publish,
* distribute, sublicense, and/or sell copies of the Software, and to
* permit persons to whom the Software is furnished to do so, subject to
* the following conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
* MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
* LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
* OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
* WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
