<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">

</head>
<body>

<h2>User Registration</h2>

<form action="${pageContext.request.contextPath}/signUp" method="post">

    <label>Username:</label><br>
    <input type="text" name="name" required><br><br>

    <label>Password:</label><br>
    <input type="password" name="password" required><br><br>

    <label>Role:</label><br>
    <select name="role" required>
        <option value="">--Select Role--</option>
        <option value="ROLE_USER">USER</option>
        <option value="ROLE_ADMIN">ADMIN</option>
    </select><br><br>

    <button type="submit">Sign Up</button>
</form>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<c:if test="${not empty success}">
    <p style="color:green">${success}</p>
</c:if>

</body>
</html>
