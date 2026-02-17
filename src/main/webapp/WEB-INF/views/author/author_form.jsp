<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Add Author</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/author/author_form.css">

</head>
<body>

<h2>Add Author</h2>

<form id="authorForm" action="${pageContext.request.contextPath}/author/add" method="post">

    <label for="name">Name</label><br>
    <input type="text" name="name" ><br><br>

    <label for="age">Age</label><br>
    <input type="number" name="age" ><br><br>

    <div>Select Books from below List </div>
   <c:forEach var="book" items="${books}">
       <input type="checkbox"
              name="bookIds"
              value="${book.id}" />
       ${book.name}
   </c:forEach>

    <button type="submit">Save</button>
</form>


<br>
<a href="${pageContext.request.contextPath}/author">Back</a>

<a href="${pageContext.request.contextPath}">Go Home</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/author/validateForm.js"></script>
<script src="${pageContext.request.contextPath}/js/author/authorFormResponse.js"></script>
<script src="${pageContext.request.contextPath}/js/auth_interceptor.js"></script>
</body>
</html>
