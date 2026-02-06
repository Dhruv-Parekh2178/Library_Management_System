<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Add Book</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/book/book_form.css">
</head>
<body>

<h2>Add Book</h2>

<form action="${pageContext.request.contextPath}/book/add" method="post"   onsubmit="prepareAuthorJson(); prepareCategoryJson(); prepareUserJson(); return true;">

    <label>Name</label><br>
    <input type="text" name="name" required><br><br>


    <label>Author IDs (comma separated)</label><br>
    <input type="text"
           id="authorIdsInput"
           placeholder="e.g. 1,2,5,7"><br><br>


    <input type="hidden" name="authorIdsJson" id="authorIdsJson">

    <label>Categories IDs (comma separated)</label><br>
    <input type="text"
           id="categoryIdsInput"
           placeholder="e.g. 1,2,5,7"><br><br>


    <input type="hidden" name="categoryIdsJson" id="categoryIdsJson">

    <label>Publiher</label><br>
    <input type="text" name="publisher"><br><br>

    <label>Users IDs (comma separated)</label><br>
    <input type="text"
           id="userIdsInput"
           placeholder="e.g. 1,2,5,7"><br><br>


    <input type="hidden" name="userIdsJson" id="userIdsJson">

    <button type="submit">Save</button>
</form>


<br>
<a href="${pageContext.request.contextPath}/book">Back</a>

<a href="${pageContext.request.contextPath}">Go Home</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/js/book/prepareAuthorJson.js"></script>
<script src="${pageContext.request.contextPath}/js/book/prepareCategoryJson.js"></script>
<script src="${pageContext.request.contextPath}/js/book/prepareUserJson.js"></script>
</body>
</html>
