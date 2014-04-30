<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <c:if test="${acl.allowView('RESOURCE')}">
        <h1>Resources:</h1>
        <div class="row">
            <div class="col-sm-2">
                <c:forEach items="${allResourceGroups}" var="resGrp">
                    <a href="${pageContext.request.contextPath}/secure/resource?group=${resGrp.id}" class="btn btn-default btn-block">${resGrp.name}</a>
                </c:forEach>
            </div>
            <div class="col-md-8">
                <div class="section">
                    <a href="${pageContext.request.contextPath}/secure/resource?id=0&action=details" class="btn btn-primary">Add new</a>
                </div>
                <div class="section">
                    <table class="table table-hover table-striped">
                        <tr>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Created</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${allResources}" var="res">
                            <tr>
                                <td>${res.id}</td>
                                <td>${res.title}</td>
                                <td>${res.created}</td>
                                <td>
                                    <c:if test="${acl.allowUpdate('RESOURCE')}">
                                    <a href="${pageContext.request.contextPath}/secure/resource?id=${res.id}&action=details" class="btn btn-success btn-xs">edit</a>
                                    </c:if>
                                    <c:if test="${acl.allowDelete('RESOURCE')}">
                                    <a href="${pageContext.request.contextPath}/secure/resource?id=${res.id}&action=delete" class="btn btn-danger btn-xs">delete</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        </c:if>
    </jsp:body>
</t:layout>