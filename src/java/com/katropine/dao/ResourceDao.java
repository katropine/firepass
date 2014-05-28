/**
* @package FirePass - katropine
* @author Kristian Beres <kristian@katropine.com>
* @copyright Katropine (c) 2014, www.katropine.com
* @since March 24, 2014
* @licence MIT
*
* Copyright (c) 2014 Katropine - Kristian Beres, http://www.katropine.com/
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

import com.katropine.helper.AclResourceGroup;
import com.katropine.model.Resource;
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
public class ResourceDao implements ResourceDaoLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addResource(AclResourceGroup aclResGrp, Resource resource) {
        if(aclResGrp.allowInsert()){
            this.em.persist(resource);
        }
    }

    @Override
    public void editResource(AclResourceGroup aclResGrp, Resource resource) {
        if(aclResGrp.allowUpdate()){
            this.em.merge(resource);
        }
    }

    @Override
    public void deleteResource(AclResourceGroup aclResGrp, int id) {
        Resource res = this.em.find(Resource.class, id);
        if(aclResGrp.allowDelete()){
            this.em.remove(res);
        }
    }

    @Override
    public Resource getResource(AclResourceGroup aclResGrp, int id) {
        Resource res = this.em.find(Resource.class, id);
        if(aclResGrp.allowView()){
            return res;
        }
        return null;
    }

    @Override
    public List<Resource> getAllResources(List<ResourceGroup> aclList, String search, int offset, int limit) {
        Query query = this.em.createNamedQuery("Resource.getAll");
        if(search!=null && !search.isEmpty()){
            query.setParameter("title", "%" + search + "%");
        }else{
            query.setParameter("title", "%%");
        }

        return query.setFirstResult(offset)
                .setParameter("resourceGroup", aclList)
                .setMaxResults(limit)
                .getResultList();
    }
    @Override
    public int countAllResources(List<ResourceGroup> aclList, String search) {
        Query query = this.em.createNamedQuery("Resource.countAll");
        if(search!=null && !search.isEmpty()){
            query.setParameter("title", "%" + search + "%");
        }else{
            query.setParameter("title", "%%");
        }

        return ((Number) query.setParameter("resourceGroup", aclList).getSingleResult()).intValue();
    }

    @Override
    public void getResource() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Resource> getAllResourcesByGroup(List<ResourceGroup> aclList, int groupId, int offset, int limit) {

        List<Resource> res =this.em.createNamedQuery("Resource.getAllByGroup")
                .setParameter("groupid", groupId)
                .setParameter("resourceGroup", aclList)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        return res;
    }
    @Override
    public int countAllResourcesByGroup(List<ResourceGroup> aclList, int groupId){
        
        Query query = this.em.createNamedQuery("Resource.countAllByGroup");
        query.setParameter("groupid", groupId);

        return ((Number) query.setParameter("resourceGroup", aclList).getSingleResult()).intValue();
    }
    
    
    
    
}
