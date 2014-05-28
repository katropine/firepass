<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:layout>
    <jsp:body>
        <fmt:setLocale value="${language}"/>
        <c:if test="${(acl.allowUpdate('RESOURCE_GROUP') and group.id gt 0 and aclGrp.allowUpdate(group)) or (acl.allowInsert('RESOURCE_GROUP'))}">
        <h1 class="page-heading resource-groups"><fmt:message key="resource_group"/></h1>
        <div>
            <form id="resource-group-save" method="post" action="${pageContext.request.contextPath}/secure/resourcegroup">
                <div class="form-group">
                
                    <label><fmt:message key="title"/></label>
                    <input type="text" name="title" class="form-control" value="${group.name}">
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
                                <c:forEach items="${group.aclUserResourceGroups}" var="acl">


                                            <tr>
                                                <td>${acl.userGroup.name}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${acl.canView == true}">
                                                            <input type="checkbox" name="${acl.userGroup.id}_can_view" value="true" checked="checked">
                                                        </c:when> 
                                                        <c:otherwise>
                                                            <input type="checkbox" name="${acl.userGroup.id}_can_view" value="true">
                                                        </c:otherwise>
                                                    </c:choose>

                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${acl.canInsert == true}">
                                                            <input type="checkbox" name="${acl.userGroup.id}_can_insert" value="true" checked="checked">
                                                        </c:when> 
                                                        <c:otherwise>
                                                            <input type="checkbox" name="${acl.userGroup.id}_can_insert" value="true">
                                                        </c:otherwise>
                                                    </c:choose>

                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${acl.canUpdate == true}">
                                                            <input type="checkbox" name="${acl.userGroup.id}_can_update" value="true" checked="checked">
                                                        </c:when> 
                                                        <c:otherwise>
                                                            <input type="checkbox" name="${acl.userGroup.id}_can_update" value="true">
                                                        </c:otherwise>
                                                    </c:choose>

                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${acl.canDelete == true}">
                                                            <input type="checkbox" name="${acl.userGroup.id}_can_delete" value="true" checked="checked">
                                                        </c:when> 
                                                        <c:otherwise>
                                                            <input type="checkbox" name="${acl.userGroup.id}_can_delete" value="true">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <input type="hidden" name="id_${acl.userGroup.id}" value="${acl.id}">   
                                                    <input type="hidden" name="id_usergroup_${acl.id}" value="${acl.id}"> 
                                                </td>
                                            </tr>




                                </c:forEach>
                            </table>
                        </div> 
                    </div>                    
                </div>     
                        <c:if test="${acl.allowUpdate('RESOURCE_GROUP') and aclGrp.allowUpdate(group)}">
                        <input type="hidden" name="id" value="${group.id}">
                        </c:if>
                        <input type="hidden" name="action" value="save">
                        <button type="submit" class="btn btn-primary"><fmt:message key="save"/></button>
                        <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default"><fmt:message key="back"/></button>
                    
            </form>
        </div>
        </c:if>
        
    </jsp:body>                    
</t:layout> 