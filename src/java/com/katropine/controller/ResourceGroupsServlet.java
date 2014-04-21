/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.controller;

import com.katropine.dao.ResourceGroupDaoLocal;
import com.katropine.model.ResourceGroup;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
        int grpId = 0;
        if(grpIdStr != null && !grpIdStr.equals("")){
            grpId = Integer.parseInt(grpIdStr);
        }
        System.out.println("action: "+action+", id: "+grpId);
        
        String title = request.getParameter("title");
        
        ResourceGroup group = new ResourceGroup(title);
        
        if("Add".equalsIgnoreCase(action) && grpId==0){
            groupDao.addResourceGroup(group);
        }else if("Details".equalsIgnoreCase(action)){
            group = groupDao.getResourceGroup(grpId);
        }else if("Edit".equalsIgnoreCase(action)){
            group.setId(grpId);
            groupDao.editResourceGroup(group);
        }else if("Delete".equalsIgnoreCase(action)){
            groupDao.deleteResourceGroup(grpId);
        }
        
        if("Details".equalsIgnoreCase(action)){
            request.setAttribute("group", group);
            request.getRequestDispatcher("resource-group-edit.jsp").forward(request, response);
        }else{
            request.setAttribute("allGroups", groupDao.getAllResourceGroup());
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
