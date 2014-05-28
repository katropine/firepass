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

import com.katropine.dao.ResourceGroupDaoLocal;
import com.katropine.dao.UserGroupDaoLocal;
import com.katropine.dao.UserGroupResourceGroupDaoLocal;
import com.katropine.helper.Pagination;
import com.katropine.helper.PaginationResource;
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
@WebServlet(name = "ResourceGroupsServlet", urlPatterns = {"/secure/resourcegroup"})
public class ResourceGroupsServlet extends CoreServlet {
    @EJB
    private ResourceGroupDaoLocal groupDao;
    @EJB
    private UserGroupDaoLocal userGroupDao;
    @EJB
    private UserGroupResourceGroupDaoLocal usrResGroupDao;
    
    
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
        response.setContentType("text/html;charset=UTF-8");
        
        super.processRequest(request, response);
        
        String action = request.getParameter("action");
        String grpIdStr = request.getParameter("id");
        String q = ""; 
        q = request.getParameter("q");
        
        String pageStr = request.getParameter("page");
        int page = 0;
        if(pageStr != null && !pageStr.equals("")){
            page = Integer.parseInt(pageStr);
        }
        int grpId = 0;
        if(grpIdStr != null && !grpIdStr.equals("")){
            grpId = Integer.parseInt(grpIdStr);
        }
        
        String title = request.getParameter("title");
        
        ResourceGroup resourceGroup = new ResourceGroup(title);

        if(grpId > 0){
            resourceGroup.setId(grpId);
            resourceGroup = groupDao.getResourceGroup(grpId);
        }
        
        
        List<UserGroup> userGroups = userGroupDao.getAllUserGroups(this.userSess, "");
        //List<User> users = userDao.getAllUsers("");
        
        if("Details".equalsIgnoreCase(action)){
            resourceGroup = groupDao.getResourceGroup(grpId);
        }else if("save".equalsIgnoreCase(action)){
            
            if(resourceGroup.getId()>0){
                groupDao.editResourceGroup(resourceGroup);
            }else{
                groupDao.addResourceGroup(resourceGroup);
            }
            
            // handle resource ACL
            ArrayList<UserGroupResourceGroup> userGroupResourceGroupList = new ArrayList<>();
            for (UserGroup ug : userGroups) {
                String aclUserGroupResourceGroupIdStr = request.getParameter("id_"+ug.getId());
                int aclUserGroupResourceGroupId = 0;
                if(aclUserGroupResourceGroupIdStr != null && !aclUserGroupResourceGroupIdStr.equals("")){
                    aclUserGroupResourceGroupId = Integer.parseInt(aclUserGroupResourceGroupIdStr);
                }
                UserGroupResourceGroup aclUserGroupResourceGroupRow = usrResGroupDao.getUserGroupResourceGroup(aclUserGroupResourceGroupId);
                if(aclUserGroupResourceGroupRow == null){
                    aclUserGroupResourceGroupRow = new UserGroupResourceGroup();
                }  
                
                boolean canView = Boolean.parseBoolean(request.getParameter(ug.getId()+"_can_view"));
                aclUserGroupResourceGroupRow.setCanView(canView);

                boolean canInsert = Boolean.parseBoolean(request.getParameter(ug.getId()+"_can_insert"));
                aclUserGroupResourceGroupRow.setCanInsert(canInsert);

                boolean canUpdate = Boolean.parseBoolean(request.getParameter(ug.getId()+"_can_update"));
                aclUserGroupResourceGroupRow.setCanUpdate(canUpdate);

                boolean canDelete = Boolean.parseBoolean(request.getParameter(ug.getId()+"_can_delete"));
                aclUserGroupResourceGroupRow.setCanDelete(canDelete);
                System.out.println("--------------------------------"+ug.getId());

                aclUserGroupResourceGroupRow.setUserGroup(ug);
                aclUserGroupResourceGroupRow.setResourceGroup(resourceGroup);

                userGroupResourceGroupList.add(aclUserGroupResourceGroupRow);
                System.out.println("-"+aclUserGroupResourceGroupRow.toString());
            }
            resourceGroup.setAclUserResourceGroups(userGroupResourceGroupList);
            // persist again, now that the resourceGroup Entity has an ID
            if(resourceGroup.getId()>0){
                groupDao.editResourceGroup(resourceGroup);
            }
            
        }else if("Delete".equalsIgnoreCase(action)){
            groupDao.deleteResourceGroup(grpId);
        }
        
        Pagination pagination = new Pagination(this.userSess.getRowsPerPage(), 5);
        
        int total = groupDao.countAllResourceGroup(q);
        PaginationResource pag = pagination.calc(page, total);
        if(q==null){
            q = "";
        }
        pag.setParam("q", q);
        pag.setUrl(request.getContextPath()+"/secure/resourcegroup");
        System.out.println(total);
        if("Details".equalsIgnoreCase(action)){
            userGroups = userGroupDao.getAllUserGroups(this.userSess, "");
            if(grpId==0 && userGroups != null){
                ArrayList<UserGroupResourceGroup> aclUserGroupResourceGroupTmpList = new ArrayList<>();
                for (UserGroup ug : userGroups) {
                    UserGroupResourceGroup aclUserGroupResourceGroupTmpRow = new UserGroupResourceGroup();

                    aclUserGroupResourceGroupTmpRow.setCanView(true);
                    aclUserGroupResourceGroupTmpRow.setCanDelete(true);
                    aclUserGroupResourceGroupTmpRow.setCanInsert(true);
                    aclUserGroupResourceGroupTmpRow.setCanUpdate(true);
                    aclUserGroupResourceGroupTmpRow.setUserGroup(ug);
                    aclUserGroupResourceGroupTmpRow.setResourceGroup(resourceGroup);
                    aclUserGroupResourceGroupTmpList.add(aclUserGroupResourceGroupTmpRow); 
                }
                resourceGroup = new ResourceGroup();
                resourceGroup.setAclUserResourceGroups(aclUserGroupResourceGroupTmpList);
            }

            
            request.setAttribute("allUserGroups", userGroups);
            request.setAttribute("group", resourceGroup);
            request.getRequestDispatcher("resource-group-edit.jsp").forward(request, response);
        }else{
            request.setAttribute("allGroups", groupDao.getAllResourceGroup(q, pagination.getOffset(), pagination.getLimit()));
            request.setAttribute("paginationHtml", pag.getUi());
            request.setAttribute("paginationHtmlRows", pag.getUiRowsPerPage());
            request.getRequestDispatcher("resource-group.jsp").forward(request, response);
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
