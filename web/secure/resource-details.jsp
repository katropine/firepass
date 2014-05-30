<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:layout>
    <jsp:body>
        <fmt:setLocale value="${language}"/>
        <c:if test="${(acl.allowView('RESOURCE') and resource.id gt 0 and aclGrp.allowUpdate(group))}">
        <h1 class="page-heading resource"><fmt:message key="resource"/></h1>
        <div>
          
            <table class="table">
                <tr>
                    <td><fmt:message key="title"/></td>
                    <td>${resource.title}</td>
                </tr>
                <tr>
                     <td><fmt:message key="group"/></td>
                    <td>${resource.group.name}</td>
                </tr>    
                <tr>   
                    <td><fmt:message key="data"/></td>
                    <td>${resource.body}</td>
                </tr>
              </table>

                        
                    
          
        </div>
                <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default"><fmt:message key="back"/></button>
        </c:if>
        
    </jsp:body>                    
</t:layout> 