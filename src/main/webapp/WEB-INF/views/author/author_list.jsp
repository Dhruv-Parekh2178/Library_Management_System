<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Authors</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/author/author_list.css">
</head>
<body>

<h2>Author List</h2>
<a href="${pageContext.request.contextPath}/author/add">Add Author</a>
<div class="container">
    <c:forEach items="${authors}" var="author">
        <div class="card" data-id = "${author.id}">
            <div style="display:none"> <strong id="a_id">Id:</strong> ${author.id}<br></div>
            <strong>Name:</strong> ${author.name}<br>
            <strong>age:</strong> ${author.age}

            <p><strong>Books:</strong></p>
            <ul>
                <c:forEach items="${author.books}" var="book">
                    <li>${book.name}</li>
                </c:forEach>
            </ul>

            <div class="actions">
                <a href="${pageContext.request.contextPath}/author/put/${author.id}">Edit</a>

                <form class="delete_btn" data-id=${author.id} >
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
<script src="${pageContext.request.contextPath}/js/author/GetAuthorById.js"></script>
<script src="${pageContext.request.contextPath}/js/author/deleteAuthorById.js"></script>
</body>
</html>
