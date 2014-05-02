/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.controller;

import com.katropine.dao.AccessControlListDaoLocal;
import com.katropine.dao.UserGroupDaoLocal;
import com.katropine.helper.Permission;
import com.katropine.model.AccessControlList;
import com.katropine.model.UserGroup;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        
        if("Details".equalsIgnoreCase(action)){
            System.out.println("usergroupId: "+usergroupId);
            
            if(usergroup == null){
                usergroup = new UserGroup();
                // use USER as template
                usergroupId = 2;
            }
        }else if("save".equalsIgnoreCase(action) && "POST".equals(this.requestMethod)){
            ArrayList<AccessControlList> aclList = new ArrayList<>();
                
            for (Permission permission : Permission.values()) {
                AccessControlList aclRow = new AccessControlList();
                
                if(usergroup.getId() > 0){
                    int aclId = Integer.parseInt(request.getParameter(permission+"_id"));
                    aclRow.setId(aclId);
                }
                boolean canView = Boolean.parseBoolean(request.getParameter(permission+"_can_view"));
                aclRow.setCanView(canView);
System.out.println(permission+"_can_view :"+request.getParameter(permission+"_can_view"));
                boolean canInsert = Boolean.parseBoolean(request.getParameter(permission+"_can_insert"));
                aclRow.setCanInsert(canInsert);

                boolean canUpdate = Boolean.parseBoolean(request.getParameter(permission+"_can_update"));
                aclRow.setCanUpdate(canUpdate);

                boolean canDelete = Boolean.parseBoolean(request.getParameter(permission+"_can_delete"));
                aclRow.setCanDelete(canDelete);

                aclRow.setUserGroup(usergroup);
                aclRow.setPermission(permission);
                System.out.println("permission : "+aclRow.toString());
                aclList.add(aclRow);
            }
            usergroup.setName(name);
            usergroup.setAcl(aclList);    
            if(usergroupId > 0){
                usrGrpDao.editUserGroup(usergroup);
            }else{
                usrGrpDao.addUserGroup(usergroup); 
            }
            
        }else if("Delete".equalsIgnoreCase(action)){
            usrGrpDao.deleteUserGroup(usergroupId);
        }
                
        request.setAttribute("userGroup", usergroup);
       
        request.setAttribute("allUserGroups", usrGrpDao.getAllUserGroups());
        
        System.out.println("action--: "+action+", id: "+usrGrpDao.getAllUserGroups().size()+" request: "+this.requestMethod);
        if("Details".equalsIgnoreCase(action)){
            if(usergroup.getId() > 0){
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
                    request.setAttribute("allAcl", aclTmpList);
                }
            }
            request.getRequestDispatcher("user-group-edit.jsp").forward(request, response);
        }else{
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
