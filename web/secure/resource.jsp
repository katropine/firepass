<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <c:if test="${acl.allowView('RESOURCE')}">
        <div class="row">
            <div class="col-sm-2">
                <c:if test="${groupId==0}">
                <a href="${pageContext.request.contextPath}/secure/resource?group=0" class="btn btn-primary btn-block active">- All -</a>
                </c:if>
                <c:if test="${groupId!=0}">
                <a href="${pageContext.request.contextPath}/secure/resource?group=0" class="btn btn-default btn-block">All</a>
                </c:if>
                <c:forEach items="${allResourceGroups}" var="resGrp">
                    <c:choose>
                    <c:when test="${groupId == resGrp.id}">
                    <a href="${pageContext.request.contextPath}/secure/resource?group=${resGrp.id}" class="btn btn-primary btn-block active">${resGrp.name}</a>
                    </c:when>    
                    <c:otherwise>
                     <a href="${pageContext.request.contextPath}/secure/resource?group=${resGrp.id}" class="btn btn-default btn-block">${resGrp.name}</a>   
                    </c:otherwise>
                    </c:choose>
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

                                <button type="submit" class="btn btn-default">Submit</button>

                            </form>
                            <div class="navbar-form navbar-right">
                                <c:if test="${acl.allowInsert('RESOURCE')}">
                                <a href="${pageContext.request.contextPath}/secure/resource?id=0&group=${groupId}&action=details" class="btn btn-primary">Add new</a>
                                </c:if>
                            </div>
                        </div>
                    </nav>
                </div>
                
                
                <div class="container-fluid">
                    <div class="panel panel-default">
                        <div class="row">
                            <div class="col-md-6"><div class="container-fluid"><h2 class="page-heading resource">Resources</h2></div></div>
                            <div class="col-md-6"><ul class="list-group text-right">
                            <li class="list-group-item">${paginationHtml}</li>
                        </ul></div>
                    </div>
                    <table class="table table-hover table-striped">
                        <tr>
                            <th></th>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Created</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${allResources}" var="res">
                            <tr>
                                <td class="icon-row"><span class="row-icon resource-row-icon"></span></td>
                                <td>${res.id}</td>
                                <td>${res.title}</td>
                                <td>${res.created}</td>
                                <td align="right">
                                    <c:if test="${acl.allowUpdate('RESOURCE')}">
                                    <a href="${pageContext.request.contextPath}/secure/resource?id=${res.id}&group=${res.group.id}&action=details" class="btn btn-success btn-xs">edit</a>
                                    </c:if>
                                    <c:if test="${acl.allowDelete('RESOURCE')}">
                                    <a href="${pageContext.request.contextPath}/secure/resource?id=${res.id}&action=delete" class="btn btn-danger btn-xs">delete</a>
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
        </div>
                    </div>
        </c:if>
    </jsp:body>
</t:layout>