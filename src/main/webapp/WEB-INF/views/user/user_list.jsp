<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user_list.css">
</head>
<body>

<h2>User List</h2>
<a href="${pageContext.request.contextPath}/user/add">Add User</a>
<div class="container">
    <c:forEach items="${users}" var="user">
        <div class="card" data-id = "${user.id}">
            <div style="display:none"> <strong id="a_id">Id:</strong> ${user.id}<br></div>
            <strong>Name:</strong> ${user.name}<br>
            <strong>age:</strong> ${user.age}

            <p><strong>Books:</strong></p>
            <ul>
                <c:forEach items="${user.books}" var="book">
                    <li>${book.name}</li>
                </c:forEach>
            </ul>

            <div class="actions">
                <a href="${pageContext.request.contextPath}/user/put/${user.id}">Edit</a>

                <form action="${pageContext.request.contextPath}/user/delete/${user.id}" method="get">
                    <button type="submit">Delete</button>
                </form>
            </div>
        </div>
    </c:forEach>
</div>
<a href="${pageContext.request.contextPath}">Go Home</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/js/user/GetUserById.js"></script>
</body>
</html>
