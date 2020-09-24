<%-- 
    Document   : member_create_article
    Created on : Sep 22, 2020, 9:20:52 AM
    Author     : anhnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Article</title>
    </head>
    <body>
        <h1 style=" display: flex;justify-content: center;margin-bottom: 50px">Create new article</h1> <br><br>
        <form action="createArticle" method="POST" enctype="multipart/form-data">
            <table border="0" style=" display: flex;justify-content: center;margin-bottom: 50px">
                <tbody>
                    <tr>
                        <td>Title</td>
                        <td><input type="text" name="txtTitle" value="${param.txtTitle}"/></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input type="text" name="txtDescription" value="${param.txtDescription}"/></td>
                    </tr>
                    <tr>
                        <td>Image</td>
                        <td><input type="file" name="imageFile"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" name="btAction" value="Create Article"/> <input type="reset" value="Reset"/></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
