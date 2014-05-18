/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.controller;

import com.katropine.dao.ResourceGroupDaoLocal;
import com.katropine.helper.PaginationResource;
import com.katropine.helper.Pagination;
import com.katropine.model.ResourceGroup;
import java.io.IOException;
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
        
        ResourceGroup group = new ResourceGroup(title);
        
        if("Details".equalsIgnoreCase(action)){
            group = groupDao.getResourceGroup(grpId);
        }else if("save".equalsIgnoreCase(action)){
            
            if(grpId==0){
                groupDao.addResourceGroup(group);
            }else{
                group.setId(grpId);
                groupDao.editResourceGroup(group);
            }
        
        }else if("Delete".equalsIgnoreCase(action)){
            groupDao.deleteResourceGroup(grpId);
        }
        
        Pagination pagination = new Pagination(10, 10);
        
        int total = groupDao.countAllResourceGroup(q);
        PaginationResource pag = pagination.calc(page, total);
        if(q==null){
            q = "";
        }
        pag.setParam("q", q);
        pag.setUrl(request.getContextPath()+"/secure/resourcegroup");
        System.out.println(total);
        if("Details".equalsIgnoreCase(action)){
            request.setAttribute("group", group);
            request.getRequestDispatcher("resource-group-edit.jsp").forward(request, response);
        }else{
            request.setAttribute("allGroups", groupDao.getAllResourceGroup(q, pagination.getOffset(), pagination.getLimit()));
            request.setAttribute("paginationHtml", pag.getUi());
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
