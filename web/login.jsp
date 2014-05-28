<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <div id="master-loginform" class="master-loginform clearfix">
            <h1>Log in:</h1>
            <div>${message}</div>
            <form id="login" action="./login" method="post">
                <div class="form-group">
                    <label for="username">Email</label>
                    <input type="text" name="email" class="form-control" value="">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input name="password" type="password" class="form-control" value="">
                </div>  
               
                <button type="submit" class="btn btn-primary">Sign up</button>
               
            </form>
        </div>   
    </jsp:body>
</t:layout>

