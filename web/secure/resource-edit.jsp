<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:layout>
    <jsp:body>
        <fmt:setLocale value="${language}"/>
        <c:if test="${(acl.allowUpdate('RESOURCE') and resource.id gt 0) or (acl.allowInsert('RESOURCE'))}">
        <h1 class="page-heading resource"><fmt:message key="resource"/></h1>
        <div>
            <form method="post" autocomplete="off" action="${pageContext.request.contextPath}/secure/resource">
                <div class="form-group">
                
                    <label><fmt:message key="title"/></label>
                    <input type="text" name="title" class="form-control" value="${resource.title}">
                </div>
                <div class="form-group">
                    <lable><fmt:message key="group"/></lable>
                    <select name="resource_group_id" class="form-control">
                        <option value=""><fmt:message key="select"/></option>
                        <c:forEach items="${allResourceGroups}" var="grp">
                            <c:if test="${aclGrp.allowUpdate(grp) or aclGrp.allowInsert(grp)}">
                            <c:choose>
                                <c:when test="${grp.id==resource.group.id || grp.id==groupId}">
                                    <option value="${grp.id}" selected="selected">${grp.name}</option>
                                </c:when>    
                                <c:otherwise>
                                    <option value="${grp.id}">${grp.name}</option>
                                </c:otherwise>    
                            </c:choose>
                            </c:if>        
                        </c:forEach>
                    </select>
                </div>    
                <div class="form-group">
                    <label><fmt:message key="data"/></label>
                    <textarea type="text" name="body" class="form-control">${resource.body}</textarea>
                </div> 
                
                        <input type="hidden" name="action" value="save">
                        <c:if test="${acl.allowUpdate('RESOURCE')}">
                        <input type="hidden" name="id" value="${resource.id}">
                        </c:if>
                        <input type="submit" value="<fmt:message key="save"/>" class="btn btn-primary">
                        <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default"><fmt:message key="back"/></button>
                    
            </form>
        </div>
        </c:if>
        
    </jsp:body>                    
</t:layout> 