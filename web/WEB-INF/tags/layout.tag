<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag import="com.katropine.helper.Permission" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag trimDirectiveWhitespaces="true"%><!DOCTYPE html>
<html>
    <head>
        <title>Users - FirePass</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" media="screen" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/assets/css/main.css" media="screen" rel="stylesheet" type="text/css">
        <script src="${pageContext.request.contextPath}/assets/js/jquery-1.9.0.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
        <c:if test="${loggedin > 0}">
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">FirePass</a>
                </div>
                
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <c:if test="${acl.allowView('RESOURCE')}">
                        <li><a href="${pageContext.request.contextPath}/secure/resource"><fmt:message key="resource"/></a></li>
                        </c:if>
                        <c:if test="${acl.allowView('RESOURCE_GROUP')}">    
                        <li><a href="${pageContext.request.contextPath}/secure/resourcegroup"><fmt:message key="resource_groups"/></a></li>
                        </c:if>
                        <c:if test="${acl.allowView('USER')}">
                        <li><a href="${pageContext.request.contextPath}/secure/user"><fmt:message key="user"/></a></li>
                        </c:if>
                        <c:if test="${acl.allowView('USER_GROUP')}">
                        <li><a href="${pageContext.request.contextPath}/secure/usergroup"><fmt:message key="user_groups"/></a></li>
                        </c:if>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            
                                <a data-toggle="dropdown" href="#">Logdin as ${sessionUserName} &nbsp;<span class="caret"></span></a>
                            
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                              
                              <li><a href="${pageContext.request.contextPath}/secure/settings">Settings</a></li>
                              <li><a href="#">Change Password</a></li>
                              <li class="divider"></li>
                              <li><a href="../logout">Sign out</a></li>
                            </ul>
                           
                        </li>
                    </ul>
                            
                                  


                </div><!--/.nav-collapse -->
            </div>
        </div>
        </c:if>
        
        <div class="container">
        <jsp:doBody/>
        </div>

        <div class="footer navbar-fixed-bottom" >
            <div class="container credit">
                <p class="text-muted credit text-center">© Katropine, <a href="http://www.katropine.com">www.katropine.com</a></p>
            </div>
        </div>
</html>