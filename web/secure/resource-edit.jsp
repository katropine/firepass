<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        
        <h1>Resource: ${resource.id} </h1>
        <div>
            <form method="post" action="${pageContext.request.contextPath}/secure/resource">
                <div class="form-group">
                
                    <label>Title</label>
                    <input type="text" name="title" class="form-control" value="${resource.title}">
                </div>
                <div class="form-group">
                    <lable>Group</lable>
                    <select name="resource_group_id" class="form-control">
                        <option value="">Select...</option>
                        <c:forEach items="${allResourceGroups}" var="grp">
                            <c:choose>
                                <c:when test="${grp.id==resource.group.id}">
                                    <option value="${grp.id}" selected="selected">${grp.name}</option>
                                </c:when>    
                                <c:otherwise>
                                    <option value="${grp.id}">${grp.name}</option>
                                </c:otherwise>    
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>    
                <div class="form-group">
                    <label>Data</label>
                    <textarea type="text" name="body" class="form-control">${resource.bodyMessage}</textarea>
                </div> 
                
                        <input type="hidden" name="action" value="save">
                        <input type="hidden" name="id" value="${resource.id}">
                        <input type="submit" value="Save" class="btn btn-primary">
                        <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default">Back</button>
                    
            </form>
        </div>
        
        
    </jsp:body>                    
</t:layout> 