<%-- 
    Document   : home
    Created on : Sep 17, 2020, 8:11:14 PM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <c:set var="articles" value="${sessionScope.ARTICLES}"/>
        <h1>Welcome <c:out value="${sessionScope.ACCOUNT.name}"/></h1>
        <form action="logout" method="POST">
            <input type="submit" value="Logout" name="btAction"/>
        </form>
        <form action="search" method="POST">
            <input type="text" name="search"/>
            <input type="submit" name="btAction" value="Search"/>
        </form>
        <c:if test="${not empty articles}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <c:forEach var="dto" items="${articles}" varStatus="counter">
                    <tbody>
                        <tr>
                            <td>${dto.title}</td>
                            <td>${dto.description}</td>
                            <td>
                                <c:if test="${not empty dto.imagePath}">
                                    <img src="images/${dto.imagePath}" style="display:block; width:300px; height:300px;">
                                </c:if>
                                <c:if test="${empty dto.imagePath}">
                                    <img src="images/notfound.jpg" style="display:block; width:50px; height:50px;">
                                </c:if></td>

                            <td>${dto.createdDate}</td>
                        </c:forEach>
                    </tr>
                </tbody>
            </table>
            <a href="search?page=1">1</a>
            <a href="search?page=2">2</a>
            <a href="search?page=3">3</a>
        </c:if>
        <c:if test="${empty articles}">


            <h1>No record is matched !!!</h1>
        </c:if>
    </body>
</html>
