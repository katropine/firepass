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
    // ACL
    @Override
    public List<ResourceGroup> getAllowedResourceGroups(int userGrpId){
        return this.em.createNamedQuery("ResourceGroup.getAllCanView")
                .setParameter("userGroupId", userGrpId)
                .setParameter("canView", true)
                .getResultList();
        
    }
}
