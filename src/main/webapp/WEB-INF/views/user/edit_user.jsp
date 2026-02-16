<%@ page contentType="text/html;charset=UTF-8" %>

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
    <input type="text" name="name" value="${user.name}" ><br><br>

    <label>Age</label><br>
    <input type="number" name="age" value="${user.age}" ><br><br>

    <label>Book IDs (comma separated)</label><br>
    <input type="text"
           name="books"
           id="bookIdsInput"
           placeholder="e.g. 1,2,5,7"
        ><br><br>


    <input type="hidden" name="bookIdsJson" id="bookIdsJson">

    <button type="submit">Update</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/user">Back</a>
<a href="${pageContext.request.contextPath}">Go Home</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/user/prepareBookJson.js"></script>
<script src="${pageContext.request.contextPath}/js/user/validateForm.js"></script>
<script src="${pageContext.request.contextPath}/js/auth_interceptor.js"></script>

</body>
</html>
