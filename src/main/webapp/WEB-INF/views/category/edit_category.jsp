<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Edit Category</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/category/category_form.css">
</head>
<body>

    <h2>Edit Category</h2>

<form id="categoryForm" action="${pageContext.request.contextPath}/category/put/${category.id}"
      method="post">

    <label>Name</label><br>
    <input type="text" name="name" value="${category.name}" ><br><br>

  <div>Select Books from below List </div>
     <c:forEach var="book" items="${books}">
         <input type="checkbox"
                name="bookIds"
                value="${book.id}" />
         ${book.name}
     </c:forEach>


    <input type="hidden" name="bookIdsJson" id="bookIdsJson">

    <button type="submit">Update</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/category">Back</a>
<a href="${pageContext.request.contextPath}">Go Home</a>
<script>
    const contextPath = "${pageContext.request.contextPath}";
    const id = "${category.id}";
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/category/validateForm.js"></script>
    <script src="${pageContext.request.contextPath}/js/category/editCategoryResponse.js"></script>
    <script src="${pageContext.request.contextPath}/js/auth_interceptor.js"></script>

</body>
</html>
