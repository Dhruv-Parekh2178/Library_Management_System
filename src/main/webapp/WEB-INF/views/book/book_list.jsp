<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/book/book_list.css">
</head>
<body>

<h2>Book List</h2>
<a href="${pageContext.request.contextPath}/book/add">Add Book</a>
<div class="container">
    <c:forEach items="${books}" var="book">
        <div class="card" data-id="${book.id}">
            <div style="display:none"> <strong id="a_id">Id:</strong> ${book.id}<br></div>
            <strong>Name:</strong> ${book.name}<br>

            <p><strong>Authors:</strong></p>
            <ul>
                <c:forEach items="${book.authors}" var="author">
                    <li>${author.name}</li>
                </c:forEach>
            </ul>

            <p><strong>Categories:</strong></p>
            <ul>
                <c:forEach items="${book.categories}" var="category">
                    <li>${category.name}</li>
                </c:forEach>
            </ul>

            <strong>Publisher:</strong> ${book.publisher.name}<br>

            <p><strong>Users:</strong></p>
            <ul>
                <c:forEach items="${book.users}" var="user">
                    <li>${user.name}</li>
                </c:forEach>
            </ul>

            <div class="actions">
                <a href="${pageContext.request.contextPath}/book/put/${book.id}">Edit</a>

                <form class="delete_btn" data-id=${book.id} >
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
<script src="${pageContext.request.contextPath}/js/book/GetBookById.js"></script>
<script src="${pageContext.request.contextPath}/js/book/deleteBookById.js"></script>
</body>
</html>
