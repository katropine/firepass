<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        
        <h1>User: ${user.id} </h1>
        <div>
            <form method="post" action="${pageContext.request.contextPath}/secure/user">
                <div class="form-group">
                
                    <label>First Name</label>
                    <input type="text" name="firstname" class="form-control" value="${user.firstname}">
                </div>    
                <div class="form-group">
                    <label>Last Name</label>
                    <input type="text" name="lastname" class="form-control" value="${user.lastname}">
                </div>    
                <div class="form-group">
                    <label>Email Name</label>
                    <input type="text" name="email" class="form-control" value="${user.email}">
                </div>    
                <div class="form-group">
                    <label>Password Name</label>
                    <input type="password" name="password" class="form-control" value="${user.password}">
                </div>  
                
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" name="action" value="Edit" class="btn btn-primary">
                        <button type="button" onclick="javascript: history.back(-1)" class="btn btn-default">Back</button>
                    
            </form>
        </div>
        
  </jsp:body>                    
</t:layout>  