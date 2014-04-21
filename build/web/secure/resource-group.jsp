<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        
        <h1>Resource Groups</h1>
        <div class="section">
            <form method="post" action="${pageContext.request.contextPath}/secure/resourcegroup">
            <table class="table ">
                <tr>
                    <td>Title</td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="text" name="title" class="form-control" value="${resourceGroup.title}"></td>
                    <td>
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
                    <th>Name</th>
                    <th>Created</th>
                    <th></th>
                </tr>
                <c:forEach items="${allGroups}" var="gr">
                    <tr>
                        <td>${gr.id}</td>
                        <td>${gr.name}</td>
                        <td></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/secure/resourcegroup?id=${gr.id}&action=details" class="btn btn-success btn-xs">edit</a>
                            <a href="${pageContext.request.contextPath}/secure/resourcegroup?id=${gr.id}&action=delete" class="btn btn-danger btn-xs">delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </jsp:body>                    
</t:layout>