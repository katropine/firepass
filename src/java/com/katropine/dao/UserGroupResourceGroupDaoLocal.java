/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.model.UserGroupResourceGroup;
import javax.ejb.Local;

/**
 *
 * @author kriss
 */
@Local
public interface UserGroupResourceGroupDaoLocal {
    
    UserGroupResourceGroup getUserGroupResourceGroup(int id);
    
}
