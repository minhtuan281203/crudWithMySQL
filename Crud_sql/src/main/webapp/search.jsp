<%--
  Created by IntelliJ IDEA.
  User: quand
  Date: 5/29/2023
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kết quả tìm kiếm</title>
</head>
<body>
<h1>Kết quả tìm kiếm</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên sách</th>
        <th>Tác giả</th>
        <th>Giá</th>
        <th>Số lượng</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${searchResults}" var="book">
        <tr>
            <td>${book.getId()}</td>
            <td>${book.getTitle()}</td>
            <td>${book.getAuthor()}</td>
            <td>${book.getPrice()}</td>
            <td>${book.getQuantity()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
