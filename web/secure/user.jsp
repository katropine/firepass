<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        
        <h1>Users</h1>
        <div class="section">
            <form method="post" action="${pageContext.request.contextPath}/secure/user">
            <table class="table ">
                <tr>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Email</td>
                    <td>Password</td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="text" name="firstname" class="form-control" value="${user.firstname}"></td>
                    <td><input type="text" name="lastname" class="form-control" value="${user.lastname}"></td>
                    <td><input type="text" name="email" class="form-control" value="${user.email}"></td>
                    <td><input type="password" name="password" class="form-control" value="${user.password}"></td>
                    <td>
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" name="action" class="btn btn-primary" value="Add">
                    </td>
                </tr>
            </table>
            </form>
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