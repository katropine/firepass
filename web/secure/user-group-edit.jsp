<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:layout>
    <jsp:body>
        <fmt:setLocale value="${language}"/>
        <c:if test="${(acl.allowUpdate('USER_GROUP') and userGroup.id gt 0) or (acl.allowInsert('USER_GROUP'))}">
        <h2 class="page-heading user-groups"><fmt:message key="user_groups"/></h2>
        <div>
            <form id="user-group-save" method="post" action="${pageContext.request.contextPath}/secure/usergroup">
                <div class="form-group">
                
                    <label><fmt:message key="title"/></label>
                    <c:choose>
                        <c:when test="${userGroup.locked == false}">
                            <input type="text" name="name" class="form-control" value="${userGroup.name}">
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" readonly="readonly" value="${userGroup.name}">
                        </c:otherwise>
                    </c:choose>
                    
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading"><fmt:message key="acl_level_global"/></div>
                    <div class="panel-body">
                        <div class="form-group">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th><fmt:message key="permission"/></th>
                                        <th><fmt:message key="can_view"/></th>
                                        <th><fmt:message key="can_insert"/></th>
                                        <th><fmt:message key="can_update"/></th>
                                        <th><fmt:message key="can_delete"/></th>
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
                    </div>                
                </div>
                <div class="panel panel-default">  
                    <div class="panel-heading"><fmt:message key="acl_level_resource"/></div>
                    <div class="panel-body">
                        <div class="form-group">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th><fmt:message key="resource_group"/></th>
                                        <th><fmt:message key="can_view"/></th>
                                        <th><fmt:message key="can_insert"/></th>
                                        <th><fmt:message key="can_update"/></th>
                                        <th><fmt:message key="can_delete"/></th>
                                    </tr>
                                </thead>
                                <c:forEach items="${userGroup.aclUserResourceGroups}" var="grp">


                                            <tr>
                                                <td>${grp.resourceGroup.name}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${grp.canView == true}">
                                                            <input type="checkbox" name="${grp.resourceGroup.id}_can_view" value="true" checked="checked">
                                                        </c:when> 
                                                        <c:otherwise>
                                                            <input type="checkbox" name="${grp.resourceGroup.id}_can_view" value="true">
                                                        </c:otherwise>
                                                    </c:choose>

                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${grp.canInsert == true}">
                                                            <input type="checkbox" name="${grp.resourceGroup.id}_can_insert" value="true" checked="checked">
                                                        </c:when> 
                                                        <c:otherwise>
                                                            <input type="checkbox" name="${grp.resourceGroup.id}_can_insert" value="true">
                                                        </c:otherwise>
                                                    </c:choose>

                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${grp.canUpdate == true}">
                                                            <input type="checkbox" name="${grp.resourceGroup.id}_can_update" value="true" checked="checked">
                                                        </c:when> 
                                                        <c:otherwise>
                                                            <input type="checkbox" name="${grp.resourceGroup.id}_can_update" value="true">
                                                        </c:otherwise>
                                                    </c:choose>

                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${grp.canDelete == true}">
                                                            <input type="checkbox" name="${grp.resourceGroup.id}_can_delete" value="true" checked="checked">
                                                        </c:when> 
                                                        <c:otherwise>
                                                            <input type="checkbox" name="${grp.resourceGroup.id}_can_delete" value="true">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <input type="hidden" name="id_${grp.resourceGroup.id}" value="${grp.id}"> 
                                                    <input type="hidden" name="id_resource_${grp.resourceGroup.id}" value="${grp.resourceGroup.id}"> 
                                                </td>
                                            </tr>




                                </c:forEach>
                            </table>
                        </div> 
                    </div>                    
                </div>            
                            
                            
                
                <c:if test="${acl.allowUpdate('RESOURCE_GROUP')}">
                    <c:if test="${userGroup.locked == false}">
                    <input type="hidden" name="id" value="${userGroup.id}">
                    <input type="hidden" name="action" value="save">
                    <button type="submit" class="btn btn-primary"><fmt:message key="save"/></button>
                    </c:if>
                </c:if>
                <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default"><fmt:message key="back"/></button>
                    
            </form>
        </div>
        </c:if>
        
    </jsp:body>                    
</t:layout> 