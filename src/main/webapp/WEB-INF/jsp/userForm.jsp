<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Yahoooo !!!!</title>
    <!-- Bootstrap core CSS -->
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">

    <style>
        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 60px;
            background-color: #f5f5f5;
        }

        .footer .container {
            width: auto;
            max-width: 680px;
            padding: 0 15px;
        }
    </style>
</head>
<body>

<nav role="navigation" class="navbar navbar-default">

    <div class="">
        <a href="/" class="navbar-brand">Brand</a>
    </div>

    <div class="navbar-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="/users">Users</a></li>

        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/logout">Logout</a></li>
        </ul>
    </div>

</nav>
<div class="container">
    <form action="${action}" method="post">
        <div class="form-group">
            <label for="userId">User ID</label>
            <c:if test="${!newUser}">
                <input class="form-control" type="text" name="userId" value="${user.userId}"
                   readonly>
            </c:if>
            <c:if test="${newUser}">
                <input class="form-control" type="text" name="userId" value="${user.userId}">
            </c:if>

        </div>

        <div class="form-group">
            <label for="firstName">First Name</label>
            <input class="form-control" type="text" name="firstName" value="${user.firstName}">
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input class="form-control" type="text" name="lastName" value="${user.lastName}">
        </div>
        <div class="form-group">
            <label for="dob">Date of Birth (yyyy-mm-dd)</label>
            <input class="form-control" type="text" name="dob" value="${user.dob}">
        </div>
        <div class="form-group">
            <label for="email">E-Mail</label>
            <input class="form-control" type="text" name="email" value="${user.email}">
        </div>
        <button type="submit" class="btn btn-info">Save</button>
    </form>
</div>

<footer class="footer">
    <div class="container">
        <p>footer content</p>
    </div>
</footer>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>