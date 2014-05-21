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

import com.katropine.libs.BCrypt;
import com.katropine.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kriss
 */
@Stateless
public class UserDao implements UserDaoLocal {
    
    @PersistenceContext
    private EntityManager em;
  
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addUser(User user) {
        this.em.persist(user);
    }

    @Override
    public void editUser(User user) {
        this.em.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        this.em.remove(this.getUser(id));
    }

    @Override
    public User getUser(int id) {
        return this.em.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers(String search) {
        if(search == null){
            return this.em.createNamedQuery("User.getAll").getResultList();
            
        }
        return this.em.createNamedQuery("User.searchAll")
                    .setParameter("fname", "%"+search+"%")
                    .setParameter("lname", "%"+search+"%")
                    .setParameter("email", "%"+search+"%")
                    .getResultList();
    }
    @Override
    public List<User> getAllUsers(String search, int offset, int limit) {
        if(search == null){
            return this.em.createNamedQuery("User.getAll").getResultList();
            
        }
        return this.em.createNamedQuery("User.searchAll")
                    .setParameter("fname", "%"+search+"%")
                    .setParameter("lname", "%"+search+"%")
                    .setParameter("email", "%"+search+"%")
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
    }
    
    @Override 
    public int countAllUsers(String search){
        if(search == null){
            return this.em.createNamedQuery("User.getAll").getResultList().size();
            
        }
        return ((Number) this.em.createNamedQuery("User.countAll")
                    .setParameter("fname", "%"+search+"%")
                    .setParameter("lname", "%"+search+"%")
                    .setParameter("email", "%"+search+"%")
                    .getSingleResult()).intValue();
    }
    
    @Override
    public void getUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User authenticate(User user) {
        List<User> users = this.em.createNamedQuery("User.authenticate")
                    .setParameter("email", user.getEmail())
                    .getResultList(); 
        if (!users.isEmpty() && users.get(0).getId() > 0 && BCrypt.checkpw(user.getCandidatePassword(), users.get(0).getPassword())){
            return users.get(0);
        }
        return new User(); 
    }
    
    
 
}
