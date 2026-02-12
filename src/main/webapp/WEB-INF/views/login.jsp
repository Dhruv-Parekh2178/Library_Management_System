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

    <button type="submit">Login</button>
    <a href="${pageContext.request.contextPath}/signUp">
        <button type="button">Sign Up</button>
    </a>
</form>

<div id="errorMessage" style="color:red; display:none;"></div>

<c:if test="${param.error != null}">
    <p style="color:red">Invalid username or password</p>
</c:if>

<c:if test="${param.logout != null}">
    <p style="color:green">Logged out successfully</p>
</c:if>
<script>
    const contextPath = "${pageContext.request.contextPath}";
    document.getElementById("loginForm").addEventListener("submit", function(e) {
        e.preventDefault();

        const formData = new FormData(this);

        fetch(contextPath + "/login", {
            method: "POST",
            body: formData
        })
            .then(res => {
                if (!res.ok) throw new Error("Invalid username or password");
                return res.json();
            })
            .then(data => {
                localStorage.setItem("JWT", data.token);
                window.location.href = contextPath + "/index";
            })
            .catch(err => {
                document.getElementById("errorMessage").style.display = "block";
                document.getElementById("errorMessage").innerText = err.message;
            });
    });
</script>

<script>
    if (window.location.search.includes("expired")) {
        localStorage.removeItem("JWT");
        alert("Session expired. Please login again.");
    }
</script>
</body>
</html>