<%--
  Created by IntelliJ IDEA.
  User: quand
  Date: 5/31/2023
  Time: 1:43 PM
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
<h1>Student form</h1>

<c:choose>
    <c:when test="${empty student.id}">
        <form action="students?action=create" method="POST">
            <label for="name">Student Name:</label>
            <input type="text" id="name" name="name">
            <label for="grade">Grade:</label>
            <input type="text" id="grade" name="grade">
            <label for="scores">Scores: </label>
            <input type="text" id="scores" name="scores">
            <br><br>
            <input type="submit" value="Create">
            <a href="students" class="button">Cancel</a>
        </form>
    </c:when>


    <c:otherwise>
        <form action="students?action=update" method="POST">
            <input type="hidden" name="id" value="${student.id}">
            <label for="name">Student Name:</label>
            <input type="text" id="name" name="name" value="${student.name}">
            <br><br>
            <label for="grade">Grade:</label>
            <input type="text" id="grade" name="grade" value="${student.grade}">
            <br><br>
            <label for="scores">Scores:</label>
            <input type="text" id="scores" name="scores" value="${student.scores}">
            <br><br>
            <input type="submit" value="Update">
            <a href="students" class="button">Cancel</a>
            <br><br>
        </form>
        <form action="students?action=delete" method="POST">
            <input type="hidden" name="id" value="${student.id}">
            <input type="submit" value="Delete">
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>

