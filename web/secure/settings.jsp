<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:layout>
    <jsp:body>
        <fmt:setLocale value="${language}"/>
        <div>
            <h2 class="page-heading user"><fmt:message key="settings"/></h2></div>
            
                <form role="form">
                    
                    <div class="clear"></div>
                    <div class="form-group">
                        <label for="fname"><fmt:message key="first_name"/></label>
                        <input type="text" class="form-control" id="fname" name="fname" value="">
                    </div>
                    <div class="form-group">
                        <label for="lname"><fmt:message key="last_name"/></label>
                        <input type="text" class="form-control" id="lname" name="lname" value="">
                    </div>
                    <div class="form-group no-form">
                        <label class="col-sm-1 control-label text-left label-no-form"><fmt:message key="email"/></label>
                        <div class="col-sm-10">
                            <p class="form-control-static">email@example.com</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lname"><fmt:message key="language"/></label>
                        <select class="form-control" id="lname" name="lname"></select>
                    </div>
                    <div class="form-group">
                        <label for="lname"><fmt:message key="time_zone"/></label>
                        <select class="form-control" id="lname" name="lname">
                            <c:forEach items="${allTimeZones}" var="tz">
                                <option value="${tz}">${tz}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message key="save"/></button>
                    <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default"><fmt:message key="back"/></button>
              </form>

        </div>        
    </jsp:body>
</t:layout>