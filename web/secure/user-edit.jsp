<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:layout>
    <jsp:body>
        <fmt:setLocale value="${language}"/>
        <c:if test="${(acl.allowUpdate('USER') and user.id gt 0) or (acl.allowInsert('USER'))}">
        <h1 class="page-heading user"><fmt:message key="user"/></h1>
        <div>
            <form method="post" autocomplete="off" action="${pageContext.request.contextPath}/secure/user">
                <div class="form-group">
                
                    <label><fmt:message key="first_name"/></label>
                    <input type="text" name="firstname" class="form-control" value="${user.firstname}">
                </div>    
                <div class="form-group">
                    <label><fmt:message key="last_name"/></label>
                    <input type="text" name="lastname" class="form-control" value="${user.lastname}">
                </div>    
                <div class="form-group">
                    <label><fmt:message key="email"/></label>
                    <input type="text" name="email" class="form-control" value="${user.email}">
                </div>    
                <div class="form-group">
                    <label><fmt:message key="password"/></label>
                    <input type="password" name="password" class="form-control" value="${user.password}">
                </div>
                <div class="form-group">
                    <lable><fmt:message key="group"/></lable>
                    <select name="usergroup_id" class="form-control">
                        <option value=""><fmt:message key="select"/></option>
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
                <div class="form-group">
                    <label for="lname"><fmt:message key="language"/></label>
                    <select class="form-control" id="language" name="language">
                        <c:forEach items="${allLanguages}" var="lng">
                            <c:choose>
                                <c:when test="${user.language == lng.code}">
                                    <option value="${lng.code}" selected="selected">${lng.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${lng.code}">${lng.name}</option>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="lname"><fmt:message key="time_zone"/></label>
                    <select class="form-control" id="time_zone" name="time_zone">
                        <c:forEach items="${allTimeZones}" var="tz">
                            <c:choose>
                                <c:when test="${user.timeZone == tz}">
                                    <option value="${tz}" selected="selected">${tz}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${tz}">${tz}</option>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </select>
                </div>
                        <input type="hidden" name="action" value="save">
                        <c:if test="${acl.allowUpdate('USER')}">
                        <input type="hidden" name="id" value="${user.id}">
                        </c:if>
                        <input type="submit" name="action" value="<fmt:message key="save"/>" class="btn btn-primary">
                        <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default"><fmt:message key="back"/></button>
                    
            </form>
        </div>
        </c:if>
  </jsp:body>                    
</t:layout>  