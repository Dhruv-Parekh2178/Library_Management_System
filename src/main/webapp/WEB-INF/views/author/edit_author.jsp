<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Edit Author</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/author/author_form.css">
</head>
<body>

<h2>Edit Author</h2>

<form id="authorForm" action="${pageContext.request.contextPath}/author/put/${author.id}"
      method="post">

    <label for="name">Name</label><br>
    <input type="text" id="name" name="name" value="${author.name}" ><br><br>

    <label for="age">Age</label><br>
    <input type="number" id="age" name="age" value="${author.age}" ><br><br>

     <div>Select Books from below List </div>
       <c:forEach var="book" items="${books}">
           <input type="checkbox"
                  name="bookIds"
                  value="${book.id}" />
           ${book.name}
       </c:forEach>
    <button type="submit">Update</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/author">Back</a>
<a href="${pageContext.request.contextPath}">Go Home</a>
<script>
    const contextPath = "${pageContext.request.contextPath}";
    const id = "${author.id}";
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/author/validateForm.js"></script>
<script src="${pageContext.request.contextPath}/js/author/editAuthorResponse.js"></script>
<script src="${pageContext.request.contextPath}/js/auth_interceptor.js"></script>
</body>
</html>
