<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Edit Book</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/book/book_form.css">
</head>
<body>

<h2>Edit Book</h2>

<form id="bookForm" action="${pageContext.request.contextPath}/book/put/${book.id}"
      method="post"
      onsubmit="prepareAuthorJson(); prepareCategoryJson(); prepareUserJson(); return true;">

    <label for="name">Name</label><br>
    <input type="text" name="name" value="${book.name}" ><br><br>

 <div>Select Authors from below List </div>
           <c:forEach var="author" items="${authors}">
               <input type="checkbox"
                      name="authorIds"
                      value="${author.id}" />
               ${author.name}
           </c:forEach>

   <div>Select Categories from below List </div>
           <c:forEach var="category" items="${categories}">
               <input type="checkbox"
                      name="categoryIds"
                      value="${category.id}" />
               ${category.name}
           </c:forEach>

     <label>Publiher</label><br>
     <input type="text" name="publisher"><br><br>

       <div>Select Users from below List </div>
               <c:forEach var="user" items="${users}">
                   <input type="checkbox"
                          name="userIds"
                          value="${user.id}" />
                   ${user.name}
               </c:forEach>

    <button type="submit">Update</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/book">Back</a>
<a href="${pageContext.request.contextPath}">Go Home</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    const contextPath = "${pageContext.request.contextPath}";
    const id = "${book.id}";
</script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/book/validateForm.js"></script>
<script src="${pageContext.request.contextPath}/js/book/editBookResponse.js"></script>
<script src="${pageContext.request.contextPath}/js/auth_interceptor.js"></script>
</body>
</html>
