<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <c:if test="${acl.allowView('USER')}">
        <h1>Users</h1>
        <div class="section">
            <c:if test="${acl.allowInsert('USER')}">
            <a href="${pageContext.request.contextPath}/secure/user?id=0&action=details" class="btn btn-primary">Add new</a>
            </c:if>
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
                            <c:if test="${acl.allowUpdate('USER')}">
                            <a href="${pageContext.request.contextPath}/secure/user?id=${usr.id}&action=details" class="btn btn-success btn-xs">edit</a>
                            </c:if>
                            <c:if test="${acl.allowDelete('USER')}">
                            <a href="${pageContext.request.contextPath}/secure/user?id=${usr.id}&action=delete" class="btn btn-danger btn-xs">delete</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        </c:if>           
    </jsp:body>                    
</t:layout>