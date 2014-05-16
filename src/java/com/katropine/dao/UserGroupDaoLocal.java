/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.model.UserGroup;
import com.katropine.model.UserSession;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriss
 */
@Local
public interface UserGroupDaoLocal {

    void addUserGroup(UserGroup group);

    void editUserGroup(UserGroup group);

    void deleteUserGroup(int id);

    UserGroup getUserGroup(int id);
    
    UserGroup getUserGroup();
    
    List<UserGroup> getAllUserGroups(UserSession us, String search);
    
    List<UserGroup> getAllUserGroups(UserSession us, String search, int offset, int limit);
    
    int countAllUserGroups(UserSession us, String search);
    
    
}
