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

import com.katropine.dao.ResourceDaoLocal;
import com.katropine.dao.ResourceGroupDaoLocal;
import com.katropine.dao.UserGroupResourceGroupDaoLocal;
import com.katropine.helper.AclResourceGroup;
import com.katropine.helper.Pagination;
import com.katropine.helper.PaginationResource;
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
    @EJB
    private UserGroupResourceGroupDaoLocal usrGrpResGrpDao;
    
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
        // ufff!
        String groupIdStr = request.getParameter("group");
        int groupId = 0;
        if(groupIdStr != null && !groupIdStr.equals("")){
            groupId = Integer.parseInt(groupIdStr);
        }
        // ufff!
        String resGrpIdStr = request.getParameter("resource_group_id");
        int resGrpId = 0;
        if(resGrpIdStr != null && !resGrpIdStr.equals("")){
            resGrpId = Integer.parseInt(resGrpIdStr);
        }else{
            resGrpId = groupId;
        }
        
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        
        Resource resource = new Resource(resId, title); 
        ResourceGroup group = resGrpDao.getResourceGroup(resGrpId);
        if(resGrpId > 0){
            group.setId(resGrpId);
        }
        resource.setGroup(group);            
                
        
        AclResourceGroup resAcl = new AclResourceGroup(this.userSess.getUser(), usrGrpResGrpDao, group);
        if("Details".equalsIgnoreCase(action)){
            if(resId==0){
                
            }else{
                resource = resDao.getResource(resAcl, resId);
                if(resource==null){
                    request.getRequestDispatcher("401.jsp").forward(request, response);
                    return;
                }
            }
        
        }else if("save".equalsIgnoreCase(action) && "POST".equals(this.requestMethod)){
            
            if(resGrpId == 0){
                // no group selected, a
                request.getRequestDispatcher("resource-edit.jsp").forward(request, response);
                return;
            }
            
            if(resId > 0){
                resource.setTitle(title);
                resource.setId(resId);
                resource.setBody(body);
                resource.setGroup(group);
                resDao.editResource(resAcl, resource); 
            }else{
                resource.setTitle(title);
                resource.setBody(body);
                resource.setGroup(group);
                resDao.addResource(resAcl, resource);                
            }
            response.sendRedirect("../secure/resource");
            return;
        }else if("Delete".equalsIgnoreCase(action)){
            resDao.deleteResource(resAcl, resId);
        }
        
        Pagination pagination = new Pagination(this.userSess.getRowsPerPage(), 5);
        
        int total;
        if(groupId == 0){
            total = resDao.countAllResources(this.userSess.getAclResourceGroupList(), q);
        }else{
            total = resDao.countAllResourcesByGroup(this.userSess.getAclResourceGroupList(), groupId);
        }
        PaginationResource pag = pagination.calc(page, total);
        
        
        pag.setParam("group", Integer.toString(groupId));
        pag.setUrl(request.getContextPath()+"/secure/resource");
        
        request.setAttribute("resource", resource);
        request.setAttribute("group", group);
        request.setAttribute("allResourceGroups", resGrpDao.getAllResourceGroup());
        if("Details".equalsIgnoreCase(action)){
            
            request.getRequestDispatcher("resource-edit.jsp").forward(request, response);
        }else{
            if(groupId == 0){
                if(q!=null && !q.isEmpty()){
                    pag.setParam("q", q);
                }
                request.setAttribute("allResources", resDao.getAllResources(this.userSess.getAclResourceGroupList(), q, pagination.getOffset(), pagination.getLimit()));
            }else{
                request.setAttribute("allResources", resDao.getAllResourcesByGroup(this.userSess.getAclResourceGroupList(), groupId, pagination.getOffset(), pagination.getLimit()));
            }
            request.setAttribute("paginationHtml", pag.getUi());
            request.setAttribute("paginationHtmlRows", pag.getUiRowsPerPage());
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
