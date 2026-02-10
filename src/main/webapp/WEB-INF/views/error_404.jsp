<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>404 - Not Found</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error_404.css">
</head>
<body>
<h2>404 - Resource Not Found</h2>
<p>${errorMessage}</p>
<a href="${pageContext.request.contextPath}">Go Home</a>
<script src="${pageContext.request.contextPath}/js/auth_interceptor.js"></script>
</body>
</html>
