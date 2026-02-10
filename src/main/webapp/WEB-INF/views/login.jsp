<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>

<h2>Login</h2>

<form id="loginForm">
    <label>Username:</label><br>
    <input type="text" name="username" id="username" required><br><br>

    <label>Password:</label><br>
    <input type="password" name="password" id="password" required><br><br>

    <button type="button" onclick="login()">Login</button>
    <button type="button"><a href="${pageContext.request.contextPath}/signUp">Sign Up</a></button>
</form>

<div id="errorMessage" style="color:red; display:none;"></div>

<c:if test="${param.error != null}">
    <p style="color:red">Invalid username or password</p>
</c:if>

<c:if test="${param.logout != null}">
    <p style="color:green">Logged out successfully</p>
</c:if>
<script>
    function login() {
        const username = document.getElementById("username").value;
        const pass = document.getElementById("password").value;

        console.log("username:", username, "password:", pass);

        fetch('${pageContext.request.contextPath}/doLogin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: username,
                password: pass
            })
        })
            .then(res => {
                if (!res.ok) throw new Error("Login failed");
                return res.text();
            })
            .then(token => {
                localStorage.setItem("JWT", token);
                window.location.href = "${pageContext.request.contextPath}/index";
            })
            .catch(() => alert("Invalid login"));
    }
</script>

<script>
    if (window.location.search.includes("expired")) {
        localStorage.removeItem("JWT");
        alert("Session expired. Please login again.");
    }
</script>
</body>
</html>