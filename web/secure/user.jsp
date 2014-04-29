<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        
        <h1>Users</h1>
        <div class="section">
            <a href="${pageContext.request.contextPath}/secure/user?id=0&action=details" class="btn btn-primary">Add new</a>
        </div>
        <div class="section">
            <table class="table table-hover table-striped">
                <tr>
                    <th>Id</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Email</th>
                    <th>Created</th>
                    <th></th>
                </tr>
                <c:forEach items="${allUsers}" var="usr">
                    <tr>
                        <td>${usr.id}</td>
                        <td>${usr.firstname}</td>
                        <td>${usr.lastname}</td>
                        <td>${usr.email}</td>
                        <td>${usr.created}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/secure/user?id=${usr.id}&action=details" class="btn btn-success btn-xs">edit</a>
                            <a href="${pageContext.request.contextPath}/secure/user?id=${usr.id}&action=delete" class="btn btn-danger btn-xs">delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
                        
    </jsp:body>                    
</t:layout>