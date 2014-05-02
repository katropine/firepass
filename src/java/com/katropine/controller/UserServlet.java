/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.controller;

import com.katropine.dao.UserDaoLocal;
import com.katropine.dao.UserGroupDaoLocal;
import com.katropine.model.User;
import com.katropine.model.UserGroup;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kriss
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/secure/user"})
public class UserServlet extends CoreServlet {
    @EJB
    private UserDaoLocal userDao;
    @EJB
    private UserGroupDaoLocal groupDao;
    
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
        response.setContentType("text/html;charset=UTF-8");
        
        super.processRequest(request, response);
        
        
        String action = request.getParameter("action");
        String userIdStr = request.getParameter("id");
        String usergroupIdStr = request.getParameter("usergroup_id");
        
        int userId = 0;
        if(userIdStr != null && !userIdStr.equals("")){
            userId = Integer.parseInt(userIdStr);
        }
        int usergroupId = 0;
        if(usergroupIdStr != null && !usergroupIdStr.equals("")){
            usergroupId = Integer.parseInt(usergroupIdStr);
        }
        
        System.out.println("action: "+action+", id: "+userId+" request: "+this.requestMethod);
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        User user = new User(userId, firstname, lastname, email, password);
        UserGroup usergroup = new UserGroup();
        if(usergroupId > 0){
            usergroup.setId(usergroupId);
        }
        user.setUserGroups(usergroup);
        
        if("Details".equalsIgnoreCase(action)){
            user = userDao.getUser(userId);
        }else if("save".equalsIgnoreCase(action) && "POST".equals(this.requestMethod)){
            
            if(userId > 0){
                userDao.editUser(user);
            }else{
                userDao.addUser(user);
            }
            System.out.println("user: "+user.toString());
        }else if("Delete".equalsIgnoreCase(action)){
            userDao.deleteUser(userId);
        }
        
        
        request.setAttribute("user", user);
        request.setAttribute("allUsers", userDao.getAllUsers());
        
        if("Details".equalsIgnoreCase(action)){
            
            request.setAttribute("allUserGroups", groupDao.getAllUserGroups());
            request.getRequestDispatcher("user-edit.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("user.jsp").forward(request, response);
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
