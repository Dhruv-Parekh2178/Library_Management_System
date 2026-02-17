<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user_form.css">
</head>
<body>

<h2>Edit User</h2>

<form id="userForm" action="${pageContext.request.contextPath}/user/put/${user.id}"
      method="post">

    <label>Name</label><br>
    <input type="text" id="name" name="name" value="${user.name}" ><br><br>

    <label>Age</label><br>
    <input type="number" id="name" name="age" value="${user.age}" ><br><br>

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
<a href="${pageContext.request.contextPath}/user">Back</a>
<a href="${pageContext.request.contextPath}">Go Home</a>
<script>
    const contextPath = "${pageContext.request.contextPath}";
    const id = "${user.id}";
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>

<script src="${pageContext.request.contextPath}/js/user/validateForm.js"></script>
<script src="${pageContext.request.contextPath}/js/user/editUserResponse.js"></script>
<script src="${pageContext.request.contextPath}/js/auth_interceptor.js"></script>

</body>
</html>
