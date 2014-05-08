<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <c:if test="${(acl.allowUpdate('USER_GROUP') and userGroup.id gt 0) or (acl.allowInsert('USER_GROUP'))}">
        <h1>User Group: ${userGroup.locked} </h1>
        <div>
            <form method="post" action="${pageContext.request.contextPath}/secure/usergroup">
                <div class="form-group">
                
                    <label>Title</label>
                    <c:choose>
                        <c:when test="${userGroup.locked == false}">
                            <input type="text" name="name" class="form-control" value="${userGroup.name}">
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" readonly="readonly" value="${userGroup.name}">
                        </c:otherwise>
                    </c:choose>
                    
                </div> 
                <div class="form-group">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Permission</th>
                                <th>Can view</th>
                                <th>Can insert</th>
                                <th>Can update</th>
                                <th>Can delete</th>
                            </tr>
                        </thead>
                        <c:forEach items="${allAcl}" var="acl">
                            <c:choose>
                                <c:when test="${userGroup.locked == false}">
                                    <tr>
                                        <td>${acl.permission}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${acl.canView == true}">
                                                    <input type="checkbox" name="${acl.permission}_can_view" value="true" checked="checked">
                                                </c:when> 
                                                <c:otherwise>
                                                    <input type="checkbox" name="${acl.permission}_can_view" value="true">
                                                </c:otherwise>
                                            </c:choose>
                                                 
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${acl.canInsert == true}">
                                                    <input type="checkbox" name="${acl.permission}_can_insert" value="true" checked="checked">
                                                </c:when> 
                                                <c:otherwise>
                                                    <input type="checkbox" name="${acl.permission}_can_insert" value="true">
                                                </c:otherwise>
                                            </c:choose>
                                            
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${acl.canUpdate == true}">
                                                    <input type="checkbox" name="${acl.permission}_can_update" value="true" checked="checked">
                                                </c:when> 
                                                <c:otherwise>
                                                    <input type="checkbox" name="${acl.permission}_can_update" value="true">
                                                </c:otherwise>
                                            </c:choose>
                                                  
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${acl.canDelete == true}">
                                                    <input type="checkbox" name="${acl.permission}_can_delete" value="true" checked="checked">
                                                </c:when> 
                                                <c:otherwise>
                                                    <input type="checkbox" name="${acl.permission}_can_delete" value="true">
                                                </c:otherwise>
                                            </c:choose>
                                            <input type="hidden" name="${acl.permission}_id" value="${acl.id}">        
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                <tr>
                                        <td>${acl.permission}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${acl.canView == true}">
                                                    <input type="checkbox" readonly="readonly" checked="checked">
                                                </c:when> 
                                                <c:otherwise>
                                                    <input type="checkbox" readonly="readonly">
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${acl.canInsert == true}">
                                                    <input type="checkbox" readonly="readonly" checked="checked">
                                                </c:when> 
                                                <c:otherwise>
                                                    <input type="checkbox" readonly="readonly">
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${acl.canUpdate == true}">
                                                    <input type="checkbox" readonly="readonly" checked="checked">
                                                </c:when> 
                                                <c:otherwise>
                                                    <input type="checkbox" readonly="readonly">
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${acl.canDelete == true}">
                                                    <input type="checkbox" readonly="readonly" checked="checked">
                                                </c:when> 
                                                <c:otherwise>
                                                    <input type="checkbox" readonly="readonly">
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:otherwise> 
                            </c:choose>    
                                   
                        </c:forEach>
                    </table>
                </div>
                
                        <c:if test="${acl.allowUpdate('RESOURCE_GROUP')}">
                            <c:if test="${userGroup.locked == false}">
                            <input type="hidden" name="id" value="${userGroup.id}">
                            <input type="hidden" name="action" value="save">
                            <input type="submit" name="action" value="Save" class="btn btn-primary">
                            </c:if>
                        </c:if>
                        <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default">Back</button>
                    
            </form>
        </div>
        </c:if>
        
    </jsp:body>                    
</t:layout> 