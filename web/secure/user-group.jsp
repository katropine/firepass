<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <c:if test="${acl.allowView('USER_GROUP')}">        
            <h2 class="page-heading user-groups sub-header">User Groups</h2>
            
            <div class="container-fluid">
            <nav class="navbar navbar-default" role="navigation">
                
                <div class="container-fluid">
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                        
                    </form>
                    <div class="navbar-form navbar-right">
                        <c:if test="${acl.allowInsert('USER_GROUP')}">
                            <a href="${pageContext.request.contextPath}/secure/usergroup?id=0&action=details" class="btn btn-primary right">Add new</a>
                        </c:if>
                    </div>
                </div>
            </nav>
            </div>

             <div class="container-fluid">
                <table class="table table-hover table-striped">
                    <tr>
                        <th></th>
                        <th>Id</th>
                        <th>Title</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${allUserGroups}" var="grp">
                        <tr>
                            <td class="icon-row"><span class="row-icon user-groups-row-icon"></span></td>
                            <td>${grp.id}</td>
                            <td>${grp.name}</td>
                            <td align="right">
                                <c:if test="${acl.allowUpdate('USER_GROUP') || acl.allowDelete('USER_GROUP')}">
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
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        
        </c:if>           
    </jsp:body>                    
</t:layout>