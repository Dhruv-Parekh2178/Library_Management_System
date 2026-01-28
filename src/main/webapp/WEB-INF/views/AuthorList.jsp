<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h2>Author List</h2>

<a href="${pageContext.request.contextPath}/library/authors/new">Add Author</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="author" items="${authors}">
        <tr>
            <td>${author.id}</td>
            <td>${author.authorName}</td>
            <td>
                <a href="/authors/edit/${author.id}">Edit</a>
                <a href="/authors/delete/${author.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
