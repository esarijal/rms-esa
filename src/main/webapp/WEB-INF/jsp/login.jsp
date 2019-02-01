<html>
<head>
    <title>Login Page</title>
    <!-- Bootstrap core CSS -->
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container">
<span style="color: red"> ${errorMessage}</span>
<form action="/login" method="POST">
    Name: <input type="text" name="name"/>&nbsp;
    Password: <input type="password" name="password"/>&nbsp;
    <input type="submit"/>
</form>
</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>