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
