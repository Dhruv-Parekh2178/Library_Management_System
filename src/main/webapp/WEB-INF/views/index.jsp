<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>

<h1> Library Management System</h1>

<a href="${pageContext.request.contextPath}/author">
    <button>Manage Authors</button>
</a>

<a href="${pageContext.request.contextPath}/book">
    <button>Manage Books</button>
</a>

<a href="${pageContext.request.contextPath}/category">
    <button>Manage Categories</button>
</a>

<a href="${pageContext.request.contextPath}/publisher">
    <button>Manage Publisher</button>
</a>

<a href="${pageContext.request.contextPath}/user/">
    <button>Manage Users</button>
</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</body>
</html>
