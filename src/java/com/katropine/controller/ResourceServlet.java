/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.controller;

import com.katropine.dao.ResourceDaoLocal;
import com.katropine.dao.ResourceGroupDaoLocal;
import com.katropine.helper.PaginationResource;
import com.katropine.helper.Pagination;
import com.katropine.model.Resource;
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
@WebServlet(name = "ResourceServlet", urlPatterns = {"/secure/resource"})
public class ResourceServlet extends CoreServlet {
    @EJB
    private ResourceDaoLocal resDao;
    
    @EJB
    private ResourceGroupDaoLocal resGrpDao;
    
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
        String resIdStr = request.getParameter("id");
        String q = request.getParameter("q");
        String pageStr = request.getParameter("page");
        int page = 0;
        if(pageStr != null && !pageStr.equals("")){
            page = Integer.parseInt(pageStr);
        }
        
        int resId = 0;
        if(resIdStr != null && !resIdStr.equals("")){
            resId = Integer.parseInt(resIdStr);
        }
        String resGrpIdStr = request.getParameter("resource_group_id");
        int resGrpId = 0;
        if(resGrpIdStr != null && !resGrpIdStr.equals("")){
            resGrpId = Integer.parseInt(resGrpIdStr);
        }
        String groupIdStr = request.getParameter("group");
        int groupId = 0;
        if(groupIdStr != null && !groupIdStr.equals("")){
            groupId = Integer.parseInt(groupIdStr);
        }
        
        System.out.println("action: "+action+", request: "+this.requestMethod);
        
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        
        Resource resource = new Resource(resId, title); 
        ResourceGroup group = resGrpDao.getResourceGroup(resGrpId);
        if(resGrpId > 0){
            group.setId(resGrpId);
        }
        resource.setGroup(group);            
                
        
        
        if("Details".equalsIgnoreCase(action)){
            resource = resDao.getResource(resId);
        }else if("save".equalsIgnoreCase(action) && "POST".equals(this.requestMethod)){
            
            if(resId > 0){
                resource.setTitle(title);
                resource.setId(resId);
                resource.setBody(body);
                resource.setGroup(group);
                resDao.editResource(resource);  
                
            }else{
                resource.setTitle(title);
                resource.setBody(body);
                resource.setGroup(group);
                resDao.addResource(resource);
             }
            
        }else if("Delete".equalsIgnoreCase(action)){
            resDao.deleteResource(resId);
        }
        
        Pagination pagination = new Pagination(10, 10);
        
        int total;
        if(groupId == 0){
            total = resDao.countAllResources(q);
        }else{
            total = resDao.countAllResourcesByGroup(groupId);
        }
        PaginationResource pag = pagination.calc(page, total);
        
        
        pag.setParam("group", Integer.toString(groupId));
        pag.setUrl(request.getContextPath()+"/secure/resource");
        
        request.setAttribute("resource", resource);
        request.setAttribute("groupId", groupId);
        request.setAttribute("allResourceGroups", resGrpDao.getAllResourceGroup());
        if("Details".equalsIgnoreCase(action)){
            
            request.getRequestDispatcher("resource-edit.jsp").forward(request, response);
        }else{
            if(groupId == 0){
                if(q!=null && !q.isEmpty()){
                    pag.setParam("q", q);
                }
                request.setAttribute("allResources", resDao.getAllResources(q, pagination.getOffset(), pagination.getLimit()));
            }else{
                request.setAttribute("allResources", resDao.getAllResourcesByGroup(groupId, pagination.getOffset(), pagination.getLimit()));
            }
            request.setAttribute("paginationHtml", pag.getUi());
            request.getRequestDispatcher("resource.jsp").forward(request, response);
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
