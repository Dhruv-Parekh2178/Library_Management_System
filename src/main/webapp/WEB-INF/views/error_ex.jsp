<%@ page contentType="text/html;charset=UTF-8" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h2>Something went wrong</h2>
<p>${errorMessage}</p>
<a href="${pageContext.request.contextPath}">Go Home</a>
</body>
</html>
