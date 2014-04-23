<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <h1>Resources:</h1>
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
                            <a href="${pageContext.request.contextPath}/secure/resource?id=${res.id}&action=details" class="btn btn-success btn-xs">edit</a>
                            <a href="${pageContext.request.contextPath}/secure/resource?id=${res.id}&action=delete" class="btn btn-danger btn-xs">delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </jsp:body>
</t:layout>