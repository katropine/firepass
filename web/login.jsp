<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <div id="master-loginform" class="master-loginform clearfix">
            <h1>Log in:</h1>
            <div>${message}</div>
            <form action="./login" method="post">
                <div class="formField">
                    <div class="formLabel"><label for="username">Email</label></div>
                    <div class="formInput"><input type="text" name="email" class="form-control"></div>
                </div>
                <div class="formField">
                    <div class="formLabel"><label for="password">Password</label></div>
                    <div class="formInput"><input name="password" type="password" class="form-control" value=""></div>
                </div>  
                <div class="formField">
                    <input name="submit" type="submit" id="submitbutton" class="btn btn-primary" value="Login">
                </div>
            </form>
        </div>   
    </jsp:body>
</t:layout>

