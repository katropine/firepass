<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:layout>
    <jsp:body>
        <fmt:setLocale value="${language}"/>
        <div>
            <h2 class="page-heading user"><fmt:message key="settings"/></h2></div>
            
                <form id="settings" role="form" method="post" autocomplete="off" action="${pageContext.request.contextPath}/secure/settings">
                    
                    <div class="clear"></div>
                    <div class="form-group">
                        <label for="fname"><fmt:message key="first_name"/></label>
                        <input type="text" class="form-control" id="fname" name="fname" value="${sessionUser.firstname}">
                    </div>
                    <div class="form-group">
                        <label for="lname"><fmt:message key="last_name"/></label>
                        <input type="text" class="form-control" id="lname" name="lname" value="${sessionUser.lastname}">
                    </div>
                    <div class="form-group no-form">
                        <label class="col-sm-1 control-label text-left label-no-form"><fmt:message key="email"/></label>
                        <div class="col-sm-10">
                            <p class="form-control-static">email@example.com</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lname"><fmt:message key="language"/></label>
                        <select class="form-control" id="language" name="language">
                            <c:forEach items="${allLanguages}" var="lng">
                                <c:choose>
                                    <c:when test="${sessionUser.language == lng.code}">
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
                                    <c:when test="${sessionUser.timeZone == tz}">
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
                    <button type="submit" class="btn btn-primary"><fmt:message key="save"/></button>
                    <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default"><fmt:message key="back"/></button>
              </form>

        </div>        
    </jsp:body>
</t:layout>