<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:layout>
    <jsp:body>
        <fmt:setLocale value="${language}"/>
        <c:if test="${acl.allowView('USER_GROUP')}">        
            <div class="container-fluid">
            <nav class="navbar navbar-default" role="navigation">
                
                <div class="container-fluid">
                    <form action="" class="navbar-form navbar-left" role="search" method="GET">
                        <div class="form-group">
                            <input type="text" name="q" class="form-control" placeholder="<fmt:message key="search"/>">
                        </div>
                        <button type="submit" class="btn btn-default"><fmt:message key="submit"/></button>
                        
                        
                    </form>
                    <div class="navbar-form navbar-right">
                        <c:if test="${acl.allowInsert('USER_GROUP')}">
                            <a href="${pageContext.request.contextPath}/secure/usergroup?id=0&action=details" class="btn btn-primary right"><fmt:message key="add_new"/></a>
                        </c:if>
                    </div>
                </div>
            </nav>
            </div>
            
            <div class="container-fluid">
                <div class="panel panel-default">
                    <div class="row">
                        <div class="col-md-6"><div class="container-fluid"><h2 class="page-heading user-groups sub-header"><fmt:message key="user_groups"/></h2></div></div>
                        <div class="col-md-6"><ul class="list-group text-right">
                        <li class="list-group-item">${paginationHtml}</li>
                    </ul></div>
                    </div>

                    <table class="table table-hover table-striped">
                        <tr>
                            <th></th>
                            <th><fmt:message key="id"/></th>
                            <th><fmt:message key="title"/></th>
                            <th></th>
                        </tr>
                        <c:forEach items="${allUserGroups}" var="grp">
                            <tr>
                                <td class="icon-row"><span class="row-icon user-groups-row-icon"></span></td>
                                <td>${grp.id}</td>
                                <td>${grp.name}</td>
                                <td align="right">
                                    <c:if test="${acl.allowUpdate('USER_GROUP') || acl.allowDelete('USER_GROUP')}">
                                        <c:choose>
                                            <c:when test="${grp.locked eq 'false'}">
                                                <c:if test="${acl.allowUpdate('USER_GROUP')}">
                                                <a href="${pageContext.request.contextPath}/secure/usergroup?id=${grp.id}&action=details" class="btn btn-success btn-xs"><fmt:message key="edit"/></a>
                                                </c:if>
                                                <c:if test="${acl.allowDelete('USER_GROUP')}">
                                                <a href="${pageContext.request.contextPath}/secure/usergroup?id=${grp.id}&action=delete" class="btn btn-danger btn-xs"><fmt:message key="delete"/></a>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>

                                                <a href="${pageContext.request.contextPath}/secure/usergroup?id=${grp.id}&action=details" class="btn btn-primary btn-xs"><fmt:message key="details"/></a>

                                            </c:otherwise>    
                                        </c:choose>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <ul class="list-group text-right">
                        <li class="list-group-item">
                            ${paginationHtml}
                        </li>
                    </ul>
                </div>
            </div>
        
        </c:if>           
    </jsp:body>                    
</t:layout>