<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">

</head>
<body>

<h2>User Registration</h2>

<form id="signupForm" action="${pageContext.request.contextPath}/signUp" method="post">

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script>
 const contextPath = "${pageContext.request.contextPath}"
</script>
<script src="${pageContext.request.contextPath}/js/signupResponse.js"></script>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>
