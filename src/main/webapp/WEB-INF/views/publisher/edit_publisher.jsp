<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Edit Publisher</title>
</head>
<body>

    <h2>Edit Publisher</h2>

<form action="${pageContext.request.contextPath}/publisher/put/${publisher.id}"
      method="post"
   onsubmit="prepareBooksJson()">

    <label>Name</label><br>
    <input type="text" name="name" value="${publisher.name}" required><br><br>

    <label>Book IDs (comma separated)</label><br>
    <input type="text"
           id="bookIdsInput"
           placeholder="e.g. 1,2,5,7"
        ><br><br>


    <input type="hidden" name="bookIdsJson" id="bookIdsJson">

    <button type="submit">Update</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/publisher">Back</a>
<a href="${pageContext.request.contextPath}">Go Home</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</body>
</html>
