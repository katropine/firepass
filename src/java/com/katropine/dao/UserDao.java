/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    public List<User> getAllUsers() {
        return this.em.createNamedQuery("User.getAll").getResultList();
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
