<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:layout>
    <jsp:body>
        <fmt:setLocale value="${language}"/>
        <div>
            <h2 class="page-heading password"><fmt:message key="change_password"/></h2></div>
            <form role="form" method="post" autocomplete="off" action="${pageContext.request.contextPath}/secure/changepassword">
                    
                    <div class="clear"></div>
                    <div class="form-group">
                        <label for="fname"><fmt:message key="password"/></label>
                        <input type="password" class="form-control" id="password" name="password" value="">
                    </div>
                    <div class="form-group">
                        <label for="lname"><fmt:message key="repassword"/></label>
                        <input type="password" class="form-control" id="repassword" name="repassword" value="">
                    </div>
            
            
            
                    <input type="hidden" name="action" value="save">
                    <button type="submit" class="btn btn-primary"><fmt:message key="save"/></button>
                    <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default"><fmt:message key="back"/></button>
              </form>

        </div>        
    </jsp:body>
</t:layout>