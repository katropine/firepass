/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.controller;

import com.katropine.dao.LanguageService;
import com.katropine.dao.UserDaoLocal;
import com.katropine.dao.UserGroupDaoLocal;
import com.katropine.helper.PaginationResource;
import com.katropine.helper.Pagination;
import com.katropine.model.User;
import com.katropine.model.UserGroup;
import java.io.IOException;
import java.util.Arrays;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String q = ""; 
        q = request.getParameter("q");
        
        String pageStr = request.getParameter("page");
        int page = 0;
        if(pageStr != null && !pageStr.equals("")){
            page = Integer.parseInt(pageStr);
        }
        int userId = 0;
        if(userIdStr != null && !userIdStr.equals("")){
            userId = Integer.parseInt(userIdStr);
        }
        int usergroupId = 0;
        if(usergroupIdStr != null && !usergroupIdStr.equals("")){
            usergroupId = Integer.parseInt(usergroupIdStr);
        }
        
        
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String language = request.getParameter("language");
        String timeZone = request.getParameter("time_zone");
            
        User user = new User(userId, firstname, lastname, email, password);
        
        UserGroup usergroup = new UserGroup();
        if(usergroupId > 0){
            usergroup.setId(usergroupId);
        }
        user.setUserGroups(usergroup);
        
        if("Details".equalsIgnoreCase(action)){
            if(userId>0){
                user = userDao.getUser(userId);
            }
        }else if("save".equalsIgnoreCase(action) && "POST".equals(this.requestMethod)){
            
            user.setLanguage(language);
            user.setTimeZone(timeZone);
            
            if(userId > 0){
                userDao.editUser(user);
            }else{
                userDao.addUser(user);
            }
            
        }else if("Delete".equalsIgnoreCase(action)){
            userDao.deleteUser(userId);
        }
        
        Pagination pagination = new Pagination(10, 10);
        
        int total = userDao.countAllUsers(q);
        PaginationResource pag = pagination.calc(page, total);
        if(q==null){
            q = "";
        }
        pag.setParam("q", q);
        pag.setUrl(request.getContextPath()+"/secure/user");
        request.setAttribute("user", user);
        
        
        
        if("Details".equalsIgnoreCase(action)){
            String[] allTimeZones = TimeZone.getAvailableIDs();
            Arrays.sort(allTimeZones);
            request.setAttribute("allTimeZones", allTimeZones);
            request.setAttribute("allLanguages", (new LanguageService()).getAllLanguages());
            request.setAttribute("allUserGroups", groupDao.getAllUserGroups(this.userSess, ""));
            request.getRequestDispatcher("user-edit.jsp").forward(request, response);
        }else{
            request.setAttribute("allUsers", userDao.getAllUsers(q, pagination.getOffset(), pagination.getLimit()));
            request.setAttribute("paginationHtml", pag.getUi());
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
