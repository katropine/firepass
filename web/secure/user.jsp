<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:layout>
    <jsp:body>
        <fmt:setLocale value="${language}"/>
        <c:if test="${acl.allowView('USER')}">
        
        <div class="container-fluid">
            <nav class="navbar navbar-default" role="navigation">
                
                <div class="container-fluid">
                    <form class="navbar-form navbar-left" role="search" method="GET">
                        <div class="form-group">
                            <input type="text" name="q" class="form-control" placeholder="<fmt:message key="search"/>">
                        </div>
                        
                        <button type="submit" class="btn btn-default"><fmt:message key="submit"/></button>
                        
                    </form>
                    <div class="navbar-form navbar-right">
                        <c:if test="${acl.allowInsert('USER')}">
                        <a href="${pageContext.request.contextPath}/secure/user?id=0&action=details" class="btn btn-primary"><fmt:message key="add_new"/></a>
                        </c:if>
                    </div>
                </div>
            </nav>
        </div>
        
        <div class="container-fluid">
            <div class="panel panel-default">
                <div class="row">
                    <div class="col-md-4"><div class="container-fluid"><h2 class="page-heading user"><fmt:message key="users"/></h2></div></div>
                    <div class="col-md-8">
                        <div class="container-fluid">
                            <div class="btn-toolbar" role="toolbar">
                                <div class="btn-group pull-right">${paginationHtmlRows}</div><div class="btn-group pull-right">${paginationHtml}</div>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                <table class="table table-hover table-striped">
                    <tr>
                        <th></th>
                        <th><fmt:message key="id"/></th>
                        <th><fmt:message key="first_name"/></th>
                        <th><fmt:message key="last_name"/></th>
                        <th><fmt:message key="email"/></th>
                        <th><fmt:message key="created"/></th>
                        <th></th>
                    </tr>
                    <c:forEach items="${allUsers}" var="usr">
                        <tr>
                            <td class="icon-row"><span class="row-icon user-row-icon"></span></td>
                            <td>${usr.id}</td>
                            <td>${usr.firstname}</td>
                            <td>${usr.lastname}</td>
                            <td>${usr.email}</td>
                            <td><fmt:formatDate value="${usr.created}" pattern="d MMM yyyy, HH:mm" timeZone="${timezone}"/></td>
                            <td align="right">
                                <c:if test="${acl.allowUpdate('USER')}">
                                <a href="${pageContext.request.contextPath}/secure/user?id=${usr.id}&action=details" class="btn btn-success btn-xs"><fmt:message key="edit"/></a>
                                </c:if>
                                <c:if test="${acl.allowDelete('USER')}">
                                <a href="${pageContext.request.contextPath}/secure/user?id=${usr.id}&action=delete" class="btn btn-danger btn-xs"><fmt:message key="delete"/></a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="container-fluid">
                    <div class="btn-toolbar" role="toolbar">
                        <div class="btn-group pull-right">${paginationHtmlRows}</div><div class="btn-group pull-right">${paginationHtml}</div>
                    </div>
                </div>
            </div>    
            

            
        </div>
        </c:if>           
    </jsp:body>                    
</t:layout>