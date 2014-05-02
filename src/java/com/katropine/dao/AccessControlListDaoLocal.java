/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.helper.Permission;
import com.katropine.model.AccessControlList;
import com.katropine.model.UserGroup;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriss
 */
@Local
public interface AccessControlListDaoLocal {
    AccessControlList getByUserGroupAndPermission(int groupId, Permission permission);

    List<AccessControlList> getAclByUserGroup(int userGroupId);

    void editAclByUserGroup(UserGroup userGroup);

    void addAclList(ArrayList<AccessControlList> acls);
}
