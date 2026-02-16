<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Add Publisher</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/publisher/publisher_form.css">
</head>
<body>

<h2>Add Publisher</h2>

<form id="publisherForm" action="${pageContext.request.contextPath}/publisher/add" method="post">

    <label>Name</label><br>
    <input type="text" name="name" ><br><br>

    <label>Book IDs (comma separated)</label><br>
    <input type="text"
           name="books"
           id="bookIdsInput"
           placeholder="e.g. 1,2,5,7"><br><br>


    <input type="hidden" name="bookIdsJson" id="bookIdsJson">

    <button type="submit">Save</button>
</form>


<br>
<a href="${pageContext.request.contextPath}/publisher">Back</a>

<a href="${pageContext.request.contextPath}">Go Home</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/publisher/prepareBookJson.js"></script>
<script src="${pageContext.request.contextPath}/js/publisher/validateForm.js"></script>
<script src="${pageContext.request.contextPath}/js/auth_interceptor.js"></script>

</body>
</html>
