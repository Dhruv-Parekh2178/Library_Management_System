<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Add User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user_form.css">
</head>
<body>

<h2>Add User</h2>

<form id="userForm" action="${pageContext.request.contextPath}/user/add" method="post">

    <label>Name</label><br>
    <input type="text" name="name" required><br><br>

    <label>Age</label><br>
    <input type="number" name="age" required><br><br>

    <div>Select Books from below List </div>
       <c:forEach var="book" items="${books}">
           <input type="checkbox"
                  name="bookIds"
                  value="${book.id}" />
           ${book.name}
       </c:forEach>



    <input type="hidden" name="bookIdsJson" id="bookIdsJson">

    <button type="submit">Save</button>
</form>


<br>
<a href="${pageContext.request.contextPath}/user">Back</a>

<a href="${pageContext.request.contextPath}">Go Home</a>
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/user/validateForm.js"></script>
<script src="${pageContext.request.contextPath}/js/user/userFormResponse.js"></script>
<script src="${pageContext.request.contextPath}/js/auth_interceptor.js"></script>

</body>
</html>
