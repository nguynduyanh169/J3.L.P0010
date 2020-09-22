<%-- 
    Document   : admin_home
    Created on : Sep 18, 2020, 7:35:24 PM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome <c:out value="${sessionScope.ACCOUNT.name}"/>!</h1>
        <form action="logout" method="POST">
            <input type="submit" value="Logout" name="btAction"/>
        </form>
    </body>
</html>
