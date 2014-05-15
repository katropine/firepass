<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <c:if test="${acl.allowView('USER')}">
        <h2 class="page-heading user">Users</h2>
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
                        <c:if test="${acl.allowInsert('USER')}">
                        <a href="${pageContext.request.contextPath}/secure/user?id=0&action=details" class="btn btn-primary">Add new</a>
                        </c:if>
                    </div>
                </div>
            </nav>
        </div>
        
        <div class="container-fluid">
            <div class="panel panel-default">
                <ul class="list-group text-right">
                    <li class="list-group-item">${paginationHtml}</li>
                </ul>
                <table class="table table-hover table-striped">
                    <tr>
                        <th></th>
                        <th>Id</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Email</th>
                        <th>Created</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${allUsers}" var="usr">
                        <tr>
                            <td class="icon-row"><span class="row-icon user-row-icon"></span></td>
                            <td>${usr.id}</td>
                            <td>${usr.firstname}</td>
                            <td>${usr.lastname}</td>
                            <td>${usr.email}</td>
                            <td>${usr.created}</td>
                            <td align="right">
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
                <ul class="list-group text-right">
                    <li class="list-group-item">${paginationHtml}</li>
                </ul>
            </div>    
            

            
        </div>
        </c:if>           
    </jsp:body>                    
</t:layout>