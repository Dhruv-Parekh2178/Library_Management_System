<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Categories</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/category/category_list.css">
</head>
<body>

<h2>category List</h2>
<a href="${pageContext.request.contextPath}/category/add">Add Category</a>
<div class="container">
    <c:forEach items="${categories}" var="category">
        <div class="card" data-id = "${category.id}">
            <div style="display:none"> <strong id="a_id">Id:</strong> ${category.id}<br></div>
            <strong>Name:</strong> ${category.name}<br>

            <p><strong>Books:</strong></p>
            <ul>
                <c:forEach items="${category.books}" var="book">
                    <li>${book.name}</li>
                </c:forEach>
            </ul>

            <div class="actions">
                <a href="${pageContext.request.contextPath}/category/put/${category.id}">Edit</a>

                <form class="delete_btn" data-id=${category.id} >
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
<script src="${pageContext.request.contextPath}/js/category/GetCategoryById.js"></script>
<script src="${pageContext.request.contextPath}/js/category/deleteCategoryById.js"></script>
</body>
</html>
