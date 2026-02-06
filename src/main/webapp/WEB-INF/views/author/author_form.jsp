<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Add Author</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/author/author_form.css">

</head>
<body>

<h2>Add Author</h2>

<form action="${pageContext.request.contextPath}/author/add" method="post"  onsubmit="return prepareBooksJson();">

    <label>Name</label><br>
    <input type="text" name="name" required><br><br>

    <label>Age</label><br>
    <input type="number" name="age" required><br><br>

    <label>Book IDs (comma separated)</label><br>
    <input type="text"
           id="bookIdsInput"
           placeholder="e.g. 1,2,5,7"><br><br>


    <input type="hidden" name="bookIdsJson" id="bookIdsJson">

    <button type="submit">Save</button>
</form>


<br>
<a href="${pageContext.request.contextPath}/author">Back</a>

<a href="${pageContext.request.contextPath}">Go Home</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/js/author/prepareBookJson.js"></script>
</body>
</html>
