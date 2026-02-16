<%@ page contentType="text/html;charset=UTF-8" %>

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

    <label for="authors">Author IDs (comma separated)</label><br>
    <input type="text"
           name="auhtors"
           id="authorIdsInput"
           placeholder="e.g. 1,2,5,7"><br><br>


    <input type="hidden" name="authorIdsJson" id="authorIdsJson">

    <label for="categories">Categories IDs (comma separated)</label><br>
    <input type="text"
           name="categories"
           id="categoryIdsInput"
           placeholder="e.g. 1,2,5,7"><br><br>


    <input type="hidden" name="categoryIdsJson" id="categoryIdsJson">

    <label>Publiher</label><br>
    <input type="text" name="publisher" value="${book.publisher}"><br><br>


    <label for="users">Users IDs (comma separated)</label><br>
    <input type="text"
           name="users"
           id="userIdsInput"
           placeholder="e.g. 1,2,5,7"><br><br>


    <input type="hidden" name="userIdsJson" id="userIdsJson">

    <button type="submit">Update</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/book">Back</a>
<a href="${pageContext.request.contextPath}">Go Home</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/book/prepareAuthorJson.js"></script>
<script src="${pageContext.request.contextPath}/js/book/prepareCategoryJson.js"></script>
<script src="${pageContext.request.contextPath}/js/book/prepareUserJson.js"></script>
<script src="${pageContext.request.contextPath}/js/book/validateForm.js"></script>
<script src="${pageContext.request.contextPath}/js/auth_interceptor.js"></script>
</body>
</html>
