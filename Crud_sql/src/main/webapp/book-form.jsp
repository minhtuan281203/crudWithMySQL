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
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1 {
            color: #333;
        }

        form {
            width: 300px;
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"] {
            width: 100px;
            padding: 5px;
            margin-bottom: 10px;
        }

        input[type="submit"],
        a.button {
            display: inline-block;
            background-color: #4CAF50;
            color: #fff;
            padding: 8px 16px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover,
        a.button:hover {
            background-color: #45a049;
        }

        a.button {
            margin-left: 5px;
        }
    </style>
</head>
<body>
    <h1>Book form</h1>

    <c:choose>
        <c:when test="${empty book.id}">
            <form action="books?action=create" method="POST" enctype="multipart/form-data">
                <label for="nameBook">Name Book:</label>
                <input type="text" id="nameBook" name="nameBook">
                <label for="nameAuthor">Name Author:</label>
                <input type="text" id="nameAuthor" name="nameAuthor">
                <label for="price">Price: </label>
                <input type="text" id="price" name="price">
                <label for="quantity">Quantity:</label>
                <input type="text" id="quantity" name="quantity">
                <br><br>

                <label for="image">Image:</label>
                <input type="file" id="image" name="image">
                <br><br>
                <input type="submit" value="Create">
                <a href="books" class="button">Cancel</a>
            </form>
        </c:when>

        
        <c:otherwise>
            <form action="books?action=update" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${book.id}">
                <label for="nameBook">Name Book:</label>
                <input type="text" id="nameBook" name="nameBook" value="${book.nameBook}">
                <br><br>
                <label for="nameAuthor">Name Author:</label>
                <input type="text" id="nameAuthor" name="nameAuthor" value="${book.nameAuthor}">
                <br><br>
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" value="${book.price}">
                <br><br>
                <label for="quantity">Quantity:</label>
                <input type="text" id="quantity" name="quantity" value="${book.quantity}">

                <br><br>
                <label for="image">Image:</label>
                <input type="file" id="image" name="image">
                <br><br>

                <input type="submit" value="Update">
                <a href="books">Cancel</a>
                <br><br>
            </form>
            <form action="books?action=delete" method="POST">
                <input type="hidden" name="id" value="${book.id}">
                <input type="submit" value="Delete">
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>
