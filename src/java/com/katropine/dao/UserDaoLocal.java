/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.model.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriss
 */
@Local
public interface UserDaoLocal {

    void addUser(User user);

    void editUser(User user);

    void deleteUser(int id);

    void getUser();

    User getUser(int id);    

    List<User> getAllUsers(String search);

    User authenticate(User user);

    
}
