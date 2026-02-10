<%@ page contentType="text/html;charset=UTF-8" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error_ex.css">
</head>
<body>
<h2>Something went wrong</h2>
<p>${errorMessage}</p>
<a href="${pageContext.request.contextPath}">Go Home</a>
<script src="${pageContext.request.contextPath}/js/auth_interceptor.js"></script>
</body>
</html>
