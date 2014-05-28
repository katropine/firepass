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
