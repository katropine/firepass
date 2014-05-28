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

package com.katropine.controller;

import com.katropine.dao.AccessControlListDaoLocal;
import com.katropine.dao.ResourceGroupDaoLocal;
import com.katropine.dao.UserGroupDaoLocal;
import com.katropine.helper.Pagination;
import com.katropine.helper.PaginationResource;
import com.katropine.helper.Permission;
import com.katropine.model.AccessControlList;
import com.katropine.model.ResourceGroup;
import com.katropine.model.UserGroup;
import com.katropine.model.UserGroupResourceGroup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kriss
 */
@WebServlet(name = "UserGroupServlet", urlPatterns = {"/secure/usergroup"})
public class UserGroupServlet extends CoreServlet {
    
    @EJB
    private UserGroupDaoLocal usrGrpDao;
    
    @EJB
    private ResourceGroupDaoLocal groupDao;
    
    @EJB
    private AccessControlListDaoLocal aclDao;
    
    private String requestMethod = null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
        
        
        String action = request.getParameter("action");
        String usergroupIdStr = request.getParameter("id");
        String q = request.getParameter("q");
        String pageStr = request.getParameter("page");
        int page = 0;
        if(pageStr != null && !pageStr.equals("")){
            page = Integer.parseInt(pageStr);
        }
        
        int usergroupId = 0;
        if(usergroupIdStr != null && !usergroupIdStr.equals("")){
            usergroupId = Integer.parseInt(usergroupIdStr);
        }
        
        String name = request.getParameter("name");
        
        
        UserGroup usergroup = new UserGroup();
        if(usergroupId > 0){
            usergroup.setId(usergroupId);
            usergroup = usrGrpDao.getUserGroup(usergroupId);
        }
        
        List<ResourceGroup> resourceGroups = groupDao.getAllResourceGroup();
        System.out.println("goups cnt: "+groupDao.countAllResourceGroup(""));
        if("Details".equalsIgnoreCase(action)){
            
            if(usergroup == null){
                usergroup = new UserGroup();                
                // use USER as template
                usergroupId = 2;
            }
            if(usergroup.getId() == 0){
                ArrayList<UserGroupResourceGroup> aclUserGroupResourceGroupTmpList = new ArrayList<>();
                for(ResourceGroup rg : resourceGroups){
                    UserGroupResourceGroup aclUserGroupResourceGroupTmpRow = new UserGroupResourceGroup();
                    aclUserGroupResourceGroupTmpRow.setCanView(true);
                    aclUserGroupResourceGroupTmpRow.setCanDelete(true);
                    aclUserGroupResourceGroupTmpRow.setCanInsert(true);
                    aclUserGroupResourceGroupTmpRow.setCanUpdate(true);
                    aclUserGroupResourceGroupTmpRow.setResourceGroup(rg);
                    aclUserGroupResourceGroupTmpRow.setUserGroup(usergroup);
                    aclUserGroupResourceGroupTmpList.add(aclUserGroupResourceGroupTmpRow); 
                    System.out.println("goups: "+rg.getName());
                }

                usergroup.setAclUserResourceGroups(aclUserGroupResourceGroupTmpList);
            }
        }else if("save".equalsIgnoreCase(action) && "POST".equals(this.requestMethod)){
            // first save
            usergroup.setName(name);
            if(usergroup.getId() > 0){ 
                
            }else{
                usrGrpDao.addUserGroup(usergroup); 
            }
            
            
            // handle global ACL
            ArrayList<AccessControlList> aclList = new ArrayList<>();
                
            for (Permission permission : Permission.values()) {
                AccessControlList aclRow = new AccessControlList();
                
                String aclIdStr = request.getParameter(permission+"_id");
                int aclId = 0;
                if(aclIdStr != null && !aclIdStr.equals("")){
                    aclId = Integer.parseInt(aclIdStr);
                }
                
                if(usergroup.getId() > 0){   
                    aclRow.setId(aclId);
                }
                boolean canView = Boolean.parseBoolean(request.getParameter(permission+"_can_view"));
                aclRow.setCanView(canView);

                boolean canInsert = Boolean.parseBoolean(request.getParameter(permission+"_can_insert"));
                aclRow.setCanInsert(canInsert);

                boolean canUpdate = Boolean.parseBoolean(request.getParameter(permission+"_can_update"));
                aclRow.setCanUpdate(canUpdate);

                boolean canDelete = Boolean.parseBoolean(request.getParameter(permission+"_can_delete"));
                aclRow.setCanDelete(canDelete);

                aclRow.setUserGroup(usergroup);
                aclRow.setPermission(permission);

                aclList.add(aclRow);
                
            }
            
            
            
            // handle resource ACL
            ArrayList<UserGroupResourceGroup> userGroupResourceGroupList = new ArrayList<>();
            for (ResourceGroup rg : resourceGroups) {
                UserGroupResourceGroup aclUserGroupResourceGroupRow = new UserGroupResourceGroup();
                
                String aclUserGroupResourceGroupIdStr = request.getParameter("id_"+rg.getId());
                int aclUserGroupResourceGroupId = 0;
                if(aclUserGroupResourceGroupIdStr != null && !aclUserGroupResourceGroupIdStr.equals("")){
                    aclUserGroupResourceGroupId = Integer.parseInt(aclUserGroupResourceGroupIdStr);
                }
                System.out.println("id_"+rg.getId()+": "+aclUserGroupResourceGroupId);
                
                if(aclUserGroupResourceGroupId > 0){
                    if(usergroup.getId() > 0){   
                        System.out.println("usergroup_id: "+ usergroup.getId());
                        aclUserGroupResourceGroupRow.setId(aclUserGroupResourceGroupId);
                    } 
                                        
                    boolean canView = Boolean.parseBoolean(request.getParameter(rg.getId()+"_can_view"));
                    aclUserGroupResourceGroupRow.setCanView(canView);
  
                    boolean canInsert = Boolean.parseBoolean(request.getParameter(rg.getId()+"_can_insert"));
                    aclUserGroupResourceGroupRow.setCanInsert(canInsert);

                    boolean canUpdate = Boolean.parseBoolean(request.getParameter(rg.getId()+"_can_update"));
                    aclUserGroupResourceGroupRow.setCanUpdate(canUpdate);

                    boolean canDelete = Boolean.parseBoolean(request.getParameter(rg.getId()+"_can_delete"));
                    aclUserGroupResourceGroupRow.setCanDelete(canDelete);

                }
                aclUserGroupResourceGroupRow.setUserGroup(usergroup);
                aclUserGroupResourceGroupRow.setResourceGroup(rg);
                
                System.out.println(aclUserGroupResourceGroupRow.toString());
                userGroupResourceGroupList.add(aclUserGroupResourceGroupRow);
            }
            
               
            usergroup.setAclUserResourceGroups(userGroupResourceGroupList);
            usergroup.setAcl(aclList); 
            if(usergroup.getId() > 0){ 
                usrGrpDao.editUserGroup(usergroup);
            }
            
        }else if("Delete".equalsIgnoreCase(action)){
            usrGrpDao.deleteUserGroup(usergroupId);
        }
        
        
        Pagination pagination = new Pagination(this.userSess.getRowsPerPage(), 5);
        
        int total = usrGrpDao.countAllUserGroups(this.userSess, q);
        PaginationResource pag = pagination.calc(page, total);
        if(q==null){
            q = new String();
        }
        pag.setParam("q", q);
        pag.setUrl(request.getContextPath()+"/secure/usergroup");
        request.setAttribute("userGroup", usergroup);

        
        if("Details".equalsIgnoreCase(action)){
            if(usergroup.getId() > 0){
                request.setAttribute("allResourceGroups", resourceGroups);
                request.setAttribute("allAcl", aclDao.getAclByUserGroup(usergroupId));
            }else{
                ArrayList<AccessControlList> aclTmpList = new ArrayList<>();
                for (Permission permission : Permission.values()) {
                    AccessControlList aclTmpRow = new AccessControlList();
                    aclTmpRow.setPermission(permission);
                    aclTmpRow.setCanView(true);
                    aclTmpRow.setCanDelete(false);
                    aclTmpRow.setCanInsert(false);
                    aclTmpRow.setCanUpdate(false);
                    aclTmpList.add(aclTmpRow);
                }
                request.setAttribute("allAcl", aclTmpList);
                
            }
            request.getRequestDispatcher("user-group-edit.jsp").forward(request, response);
        }else{
            request.setAttribute("allUserGroups", usrGrpDao.getAllUserGroups(this.userSess, q, pagination.getOffset(), pagination.getLimit()));
            request.setAttribute("paginationHtml", pag.getUi());
            request.setAttribute("paginationHtmlRows", pag.getUiRowsPerPage());
            request.getRequestDispatcher("user-group.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.requestMethod = "GET";
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.requestMethod = "POST";
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
