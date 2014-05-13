<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <c:if test="${(acl.allowUpdate('USER') and user.id gt 0) or (acl.allowInsert('USER'))}">
        <h1 class="page-heading user">User: ${user.id} </h1>
        <div>
            <form method="post" autocomplete="off" action="${pageContext.request.contextPath}/secure/user">
                <div class="form-group">
                
                    <label>First Name</label>
                    <input type="text" name="firstname" class="form-control" value="${user.firstname}">
                </div>    
                <div class="form-group">
                    <label>Last Name</label>
                    <input type="text" name="lastname" class="form-control" value="${user.lastname}">
                </div>    
                <div class="form-group">
                    <label>Email Name</label>
                    <input type="text" name="email" class="form-control" value="${user.email}">
                </div>    
                <div class="form-group">
                    <label>Password Name</label>
                    <input type="password" name="password" class="form-control" value="${user.password}">
                </div>
                <div class="form-group">
                    <lable>Group</lable>
                    <select name="usergroup_id" class="form-control">
                        <option value="">Select...</option>
                        <c:forEach items="${allUserGroups}" var="grp">
                            <c:choose>
                                <c:when test="${grp.id==user.userGroup.id}">
                                    <option value="${grp.id}" selected="selected">${grp.name}</option>
                                </c:when>    
                                <c:otherwise>
                                    <option value="${grp.id}">${grp.name}</option>
                                </c:otherwise>    
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>    
                        <input type="hidden" name="action" value="save">
                        <c:if test="${acl.allowUpdate('USER')}">
                        <input type="hidden" name="id" value="${user.id}">
                        </c:if>
                        <input type="submit" name="action" value="Save" class="btn btn-primary">
                        <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default">Back</button>
                    
            </form>
        </div>
        </c:if>
  </jsp:body>                    
</t:layout>  