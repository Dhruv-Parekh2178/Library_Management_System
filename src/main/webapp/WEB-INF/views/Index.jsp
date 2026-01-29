<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <style>
        body {
            font-family: Arial;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .box {
            text-align: center;
        }
        button {
            padding: 15px 30px;
            font-size: 18px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<div class="box">
    <h1>CRUD Application</h1>
    <a href="/authors">
        <button>Author Management</button>
    </a>
</div>

</body>
</html>
