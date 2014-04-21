/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.model.Resource;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriss
 */
@Local
public interface ResourceDaoLocal {

    void addResource(Resource resource);

    void editResource(Resource resource);

    void deleteResource(int id);

    Resource getResource(int id);
    
    List<Resource> getAllResources();
    
    void getResource();
    
}
