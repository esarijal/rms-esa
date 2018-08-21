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
    <div>
        <button type="button" class="btn btn-success">Add User</button>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>User ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Date of Birth</th>
                    <th>E-Mail</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <c:if test="${userSize == 0}">
                <tr>
                    <td colspan="5" align="center">No Data Found</td>
                </tr>
            </c:if>
            <c:if test="${userSize > 0}">
                <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.dob}</td>
                    <td>${user.email}</td>
                    <td>
                        <button type="button" class="btn btn-warning">Update</button>
                        <a href="/deleteUser?userid=${user.userId}" class="btn btn-danger"
                           role="button">
                            Delete</a>
                    </td>
                </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <ol>

        </ol>

        <p>
            <span style="color: red">${errorMessage}</span>
        </p>
        <%--<form method="post" action="/todo.do">--%>
            <%--New Todo : <input name="todo" type="text"> <input name="add" type="submit">--%>
        <%--</form>--%>
    </div>
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