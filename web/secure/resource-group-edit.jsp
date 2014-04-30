<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <c:if test="${(acl.allowUpdate('RESOURCE_GROUP') and group.id gt 0) or (acl.allowInsert('RESOURCE_GROUP'))}">
        <h1>Resource Group: ${group.id} </h1>
        <div>
            <form method="post" action="${pageContext.request.contextPath}/secure/resourcegroup">
                <div class="form-group">
                
                    <label>Title</label>
                    <input type="text" name="title" class="form-control" value="${group.name}">
                </div>    
                
                        <c:if test="${acl.allowUpdate('RESOURCE_GROUP')}">
                        <input type="hidden" name="id" value="${group.id}">
                        </c:if>
                        <input type="submit" name="action" value="Edit" class="btn btn-primary">
                        <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default">Back</button>
                    
            </form>
        </div>
        </c:if>
        
    </jsp:body>                    
</t:layout> 