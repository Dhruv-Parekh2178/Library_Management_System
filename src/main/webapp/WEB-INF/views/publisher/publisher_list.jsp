<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Publisher</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/publisher/publisher_list.css">
</head>
<body>

<h2>Publisher List</h2>
<a href="${pageContext.request.contextPath}/publisher/add">Add Publisher</a>
<div class="container">
    <c:forEach items="${publishers}" var="publisher">
        <div class="card" data-id = "${publisher.id}">
            <div style="display:none"> <strong id="p_id">Id:</strong> ${publisher.id}<br></div>
            <strong>Name:</strong> ${publisher.name}<br>

            <p><strong>Books:</strong></p>
            <ul>
                <c:forEach items="${publisher.books}" var="book">
                    <li>${book.name}</li>
                </c:forEach>
            </ul>

            <div class="actions">
                <a href="${pageContext.request.contextPath}/publisher/put/${publisher.id}">Edit</a>

                <form action="${pageContext.request.contextPath}/publisher/delete/${publisher.id}" method="get">
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
<script src="${pageContext.request.contextPath}/js/publisher/GetPublisherById.js"></script>
</body>
</html>
