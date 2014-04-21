/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.model.ResourceGroup;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriss
 */
@Local
public interface ResourceGroupDaoLocal {

    void addResourceGroup(ResourceGroup resourceGroup);
    
    void editResourceGroup(ResourceGroup resourceGroup);
    
    void deleteResourceGroup(int id);
    
    ResourceGroup getResourceGroup(int id);
    
    List<ResourceGroup> getAllResourceGroup();
    
    void getResourceGroup();
    
}
