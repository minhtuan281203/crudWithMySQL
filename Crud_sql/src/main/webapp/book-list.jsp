<%--
  Created by IntelliJ IDEA.
  User: quand
  Date: 5/26/2023
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Book list</h1>
    <a href="books?action=new" class="button add-button">Add new Book</a>
    <form action="books" method="GET">
        <input type="hidden" name="action" value="search">
        <input type="text" name="searchTerm" placeholder="Tìm kiếm theo tên sáchhh">
        <button type="submit">Tìm kiếm</button>
    </form>

<table>
    <tr>
        <td>ID</td>
        <td>Name Book</td>
        <td>Name Author</td>
        <td>Price</td>
        <td>Quantity</td>
    </tr>
    <c:forEach var="bookArr" items="${bookList}">
        <tr>
            <td>${bookArr.id}</td>
            <td>${bookArr.nameBook}</td>
            <td>${bookArr.nameAuthor}</td>
            <td>${bookArr.price}</td>
            <td>${bookArr.quantity}</td>
            <td><img src="${bookArr.imageUrl}" alt="Product Image" class="image-container"></td>
            <td>
                <a class="button" href="books?action=edit&id=${bookArr.id}">Edit</a>
                <a class="button" href="books?action=delete&id=${bookArr.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
