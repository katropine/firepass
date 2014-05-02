<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <c:if test="${acl.allowView('USER_GROUP')}">        
        
            <h1>User Groups</h1>
            <div class="section">
                <c:if test="${acl.allowInsert('USER_GROUP')}">
                <a href="${pageContext.request.contextPath}/secure/usergroup?id=0&action=details" class="btn btn-primary">Add new</a>
                </c:if>
            </div>
            <div class="section">
                <table class="table table-hover table-striped">
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${allUserGroups}" var="grp">
                        <tr>
                            <td>${grp.id}</td>
                            <td>${grp.name}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${grp.locked eq 'false'}">
                                        <c:if test="${acl.allowUpdate('USER_GROUP')}">
                                        <a href="${pageContext.request.contextPath}/secure/usergroup?id=${grp.id}&action=details" class="btn btn-success btn-xs">edit</a>
                                        </c:if>
                                        <c:if test="${acl.allowDelete('USER_GROUP')}">
                                        <a href="${pageContext.request.contextPath}/secure/usergroup?id=${grp.id}&action=delete" class="btn btn-danger btn-xs">delete</a>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/secure/usergroup?id=${grp.id}&action=details" class="btn btn-primary btn-xs">details</a>
                                    </c:otherwise>    
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        
        </c:if>           
    </jsp:body>                    
</t:layout>