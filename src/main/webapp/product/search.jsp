<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28/03/2022
  Time: 4:46 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Search product</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/products">Back to product list</a>
</p>
<form method="post">
    <fieldset>
        <table>
            <tr>
                <td>ProductName: </td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Search"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
