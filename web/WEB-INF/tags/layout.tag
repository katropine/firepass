<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<!DOCTYPE html>
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
        <% if (session.getAttribute("user_id") != null) { %>
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/timetracker2/public/">Time Tracker</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="">Dashboard</a></li>
                        <li><a href="${pageContext.request.contextPath}/secure/user">User</a></li>
                        <li><a href="${pageContext.request.contextPath}/secure/resource">Resource</a></li>
                        <li><a href="${pageContext.request.contextPath}/secure/resourcegroup">Resource Groups</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="">Logdin as ${sessionUserName}</a> </li>
                        <li>
                            <a href="#">Settings</a>
                        </li>
                        <li>
                           <a href="../logout">Logg out</a>
                        </li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>
        <%}%>
        
        <div class="container">
        <jsp:doBody/>
        </div>
    </body>
</html>