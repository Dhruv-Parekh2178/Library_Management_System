<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Publisher</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/publisher/publisher_by_id.css">
</head>
<body>

<h2>Publisher Details</h2>



            <div> <strong id="p_id">Id:</strong> ${publisher.id}<br></div>
            <strong>Name:</strong> ${publisher.name}<br>

            <p><strong>Books:</strong></p>
            <ul>
                <c:forEach items="${publisher.books}" var="book">
                    <li>${book.name}</li>
                </c:forEach>
            </ul>

            <div class="actions">
                <a href="${pageContext.request.contextPath}/publisher/put/${publisher.id}">Edit</a>

                <form class="delete_btn" data-id=${publisher.id} >
                    <button type="submit">Delete</button>
                </form>
            </div>

<a href="${pageContext.request.contextPath}/publisher">Back</a>

<a href="${pageContext.request.contextPath}">Go Home</a>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/js/publisher/deletePublisherById.js"></script>
</body>
</html>
