<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Author</title>

</head>
<body>

<h2>Author Details</h2>



            <div> <strong id="a_id">Id:</strong> ${author.id}<br></div>
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

                <form action="${pageContext.request.contextPath}/author/delete/${author.id}" method="get">
                    <button type="submit">Delete</button>
                </form>
            </div>

<a href="${pageContext.request.contextPath}/author">Back</a>

<a href="${pageContext.request.contextPath}">Go Home</a>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</body>
</html>
