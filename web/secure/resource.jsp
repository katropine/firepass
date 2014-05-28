<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:layout>
    <jsp:body>
        <fmt:setLocale value="${language}"/>
        <c:if test="${acl.allowView('RESOURCE')}">
        <div class="row">
            <div class="col-sm-2">
                <c:if test="${group==null}">
                <a href="${pageContext.request.contextPath}/secure/resource?group=0" class="btn btn-primary btn-block active"><fmt:message key="_all_"/></a>
                </c:if>
                <c:if test="${group!=null}">
                <a href="${pageContext.request.contextPath}/secure/resource?group=0" class="btn btn-default btn-block"><fmt:message key="_all_"/></a>
                </c:if>
                <c:forEach items="${allResourceGroups}" var="resGrp">
                    <c:if test="${aclGrp.allowView(resGrp)}">
                        <c:choose>
                            <c:when test="${group.id == resGrp.id}">
                                <a href="${pageContext.request.contextPath}/secure/resource?group=${resGrp.id}" class="btn btn-primary btn-block active">${resGrp.name}</a>
                            </c:when>    
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/secure/resource?group=${resGrp.id}" class="btn btn-default btn-block">${resGrp.name}</a>   
                            </c:otherwise>
                        </c:choose>
                     </c:if>
                </c:forEach>
            </div>
            <div class="col-md-10">
                <div class="container-fluid">
                    <nav class="navbar navbar-default" role="navigation">

                        <div class="container-fluid">
                            <form class="navbar-form navbar-left" role="search" method="GET">
                                <div class="form-group">
                                    <input type="text" name="q" class="form-control" placeholder="Search">
                                </div>

                                <button type="submit" class="btn btn-default"><fmt:message key="submit"/></button>

                            </form>
                            <div class="navbar-form navbar-right">
                                <c:if test="${acl.allowInsert('RESOURCE')}">
                                    <c:choose>
                                        <c:when test="${group.id > 0 && aclGrp.allowUpdate(group)}">
                                            <a href="${pageContext.request.contextPath}/secure/resource?id=0&group=${group.id}&action=details" class="btn btn-primary"><fmt:message key="add_new"/></a>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${group== null}">
                                                <a href="${pageContext.request.contextPath}/secure/resource?id=0&group=0&action=details" class="btn btn-primary"><fmt:message key="add_new"/></a> 
                                            </c:if>
                                        </c:otherwise>   
                                    </c:choose>
                                </c:if>
                            </div>
                        </div>
                    </nav>
                </div>
                
                
                <div class="container-fluid">
                    <div class="panel panel-default">
                        <div class="row">
                            <div class="col-md-4"><div class="container-fluid"><h2 class="page-heading resource"><fmt:message key="resource"/></h2></div></div>
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
                            <th><fmt:message key="title"/></th>
                            <th><fmt:message key="created"/></th>
                            <th><fmt:message key="modified"/></th>
                            <th></th>
                        </tr>
                        <c:forEach items="${allResources}" var="res">
                            <c:if test="${aclGrp.allowView(res.group)}">
                            <tr>
                                <td class="icon-row"><span class="row-icon resource-row-icon"></span></td>
                                <td>${res.id}</td>
                                <td>${res.title}</td>
                                <td><fmt:formatDate value="${res.created}" pattern="d MMM yyyy, HH:mm" timeZone="${timezone}"/></td>
                                <td><fmt:formatDate value="${res.modified}" pattern="d MMM yyyy, HH:mm" timeZone="${timezone}"/></td>
                                <td align="right">
                                    <c:if test="${acl.allowUpdate('RESOURCE')}">
                                        <c:if test="${aclGrp.allowUpdate(res.group)}">
                                            <a href="${pageContext.request.contextPath}/secure/resource?id=${res.id}&group=${res.group.id}&action=details" class="btn btn-success btn-xs"><fmt:message key="edit"/></a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${acl.allowDelete('RESOURCE')}">
                                        <c:if test="${aclGrp.allowDelete(res.group)}">
                                            <a href="${pageContext.request.contextPath}/secure/resource?id=${res.id}&group=${res.group.id}&action=delete" class="btn btn-danger btn-xs"><fmt:message key="delete"/></a>
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                    <div class="container-fluid">
                        <div class="btn-toolbar" role="toolbar">
                            <div class="btn-group pull-right">${paginationHtmlRows}</div><div class="btn-group pull-right">${paginationHtml}</div>
                        </div>
                    </div>
            </div>   
                
            </div>
        </div>
                    </div>
        </c:if>
    </jsp:body>
</t:layout>