/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.controller;

import com.katropine.dao.LanguageService;
import com.katropine.dao.UserDaoLocal;
import com.katropine.model.User;
import com.katropine.model.UserSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kriss
 */
@WebServlet(name = "SettingsServlet", urlPatterns = {"/secure/settings"})
public class SettingsServlet extends CoreServlet {
    
    @EJB
    private UserDaoLocal userDao;
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
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        
        User user = userDao.getUser(this.userSess.getUser().getId());
        
        if("save".equalsIgnoreCase(action)){
            String firstname = request.getParameter("fname");
            String lastname = request.getParameter("lname");
            String language = request.getParameter("language");
            String timeZone = request.getParameter("time_zone");
                        
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setLanguage(language);
            user.setTimeZone(timeZone);
            userDao.editUser(user);
            
            HttpSession session = request.getSession();
            this.userSess.setUser(user);
            
            session.setAttribute("userSession", this.userSess);
            response.sendRedirect("../secure/settings");
            return;
        }
        
        String[] allTimeZones = TimeZone.getAvailableIDs();

        Arrays.sort(allTimeZones);
        
        request.setAttribute("allTimeZones", allTimeZones);
        request.setAttribute("allLanguages", (new LanguageService()).getAllLanguages());
        request.setAttribute("sessionUser", user);
        request.getRequestDispatcher("settings.jsp").forward(request, response);
        
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
