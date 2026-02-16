<%@ page contentType="text/html;charset=UTF-8" %>

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
    <input type="text" name="name" value="${author.name}" ><br><br>

    <label for="age">Age</label><br>
    <input type="number" name="age" value="${author.age}" ><br><br>

    <label for="books">Book IDs (comma separated)</label><br>
    <input type="text"
           name="books"
           id="bookIdsInput"
           placeholder="e.g. 1,2,5,7"
        ><br><br>


    <input type="hidden" name="bookIdsJson" id="bookIdsJson">

    <button type="submit">Update</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/author">Back</a>
<a href="${pageContext.request.contextPath}">Go Home</a>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/author/prepareBookJson.js"></script>
<script src="${pageContext.request.contextPath}/js/author/validateForm.js"></script>
<script src="${pageContext.request.contextPath}/js/auth_interceptor.js"></script>
</body>
</html>
