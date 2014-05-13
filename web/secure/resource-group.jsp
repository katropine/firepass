<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <c:if test="${acl.allowView('RESOURCE_GROUP')}"></c:if>
        <h1 class="page-heading resource-groups">Resource Groups</h1>
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
                    <th></th>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Created</th>
                    <th></th>
                </tr>
                <c:forEach items="${allGroups}" var="gr">
                    <tr>
                        <td class="icon-row"><span class="row-icon resource-groups-row-icon"></span></td>
                        <td>${gr.id}</td>
                        <td>${gr.name}</td>
                        <td></td>
                        <td align="right">
                            <c:if test="${acl.allowUpdate('RESOURCE_GROUP')}">
                            <a href="${pageContext.request.contextPath}/secure/resourcegroup?id=${gr.id}&action=details" class="btn btn-success btn-xs">edit</a>
                            </c:if>
                            <c:if test="${acl.allowDelete('RESOURCE_GROUP')}">
                            <a href="${pageContext.request.contextPath}/secure/resourcegroup?id=${gr.id}&action=delete" class="btn btn-danger btn-xs">delete</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </jsp:body>                    
</t:layout>