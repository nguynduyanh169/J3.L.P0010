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
        <style>
            .container {
                display: flex;
                justify-content: center;
                flex-direction: column;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <c:set var="articles" value="${sessionScope.ARTICLES}"/>
            <c:set var="totalpage" value="${sessionScope.TOTALPAGE}"/>
            <h1 style=" display: flex;justify-content: center;margin-bottom: 50px">Welcome <c:out value="${sessionScope.ACCOUNT.name}"/></h1> <br><br>
            <form action="logout" method="POST">
                <input type="submit" value="Logout" name="btAction"/>
            </form>
            <br/>
            <form action="search" method="POST">
                <input type="text" name="search" value="${param.search}"/>
                <input type="hidden" name="urlForward" value="Search_Member"/>
                <input type="submit" name="btAction" value="Search"/>
            </form>
            <br/>
            <c:url var="createArticleLink" value="addNewArticle"/>
            <a style="margin:5px" href="${createArticleLink}">Create New Article</a>
            <br/>
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
                                        <img src="images/${dto.imagePath}" style="display:block; width:50px; height:50px;">
                                    </c:if>
                                    <c:if test="${empty dto.imagePath}">
                                        <img src="images/notfound.jpg" style="display:block; width:50px; height:50px;">
                                    </c:if></td>

                                <td>${dto.createdDate}</td>
                            </c:forEach>
                        </tr>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty articles}">
                <h1>No record is matched !!!</h1>
            </c:if>
                <div>
                <c:forEach begin="1" end="${totalpage}" var="i">
                    <c:url var="currentPageLink" value="search">
                        <c:param name="page" value="${i}"/>
                        <c:param name="search" value="${param.search}"/>
                        <c:param name="urlForward" value="Search_Member"/>
                    </c:url>
                    <a id="${i}" style="margin-bottom: 50px" href="${currentPageLink}">${i}</a>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
