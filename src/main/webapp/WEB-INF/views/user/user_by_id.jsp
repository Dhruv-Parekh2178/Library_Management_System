<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user_by_id.css">
</head>
<body>

<h2>User Details</h2>



            <div> <strong id="u_id">Id:</strong> ${user.id}<br></div>
            <strong>Name:</strong> ${user.name}<br>
            <strong>age:</strong> ${user.age}

            <p><strong>Books:</strong></p>
            <ul>
                <c:forEach items="${author.books}" var="book">
                    <li>${book.name}</li>
                </c:forEach>
            </ul>

            <div class="actions">
                <a href="${pageContext.request.contextPath}/user/put/${user.id}">Edit</a>

                <form class="delete_btn" data-id=${user.id} >
                    <button type="submit">Delete</button>
                </form>
            </div>

<a href="${pageContext.request.contextPath}/user">Back</a>

<a href="${pageContext.request.contextPath}">Go Home</a>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/js/user/deleteUserById.js"></script>
</body>
</html>
