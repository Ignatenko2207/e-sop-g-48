<html>
<head>
    <title>Registration page</title>
</head>
<body>
<h3>Hello, input all data. please!</h3>

<form action="/registration" method="post">
    <input type="text" size="40" name="login" placeholder="Input login" required>
    <br>
    <input type="password" size="40" name="password" placeholder="Input password" required>
    <br>
    <input type="text" size="40" name="fname" placeholder="Input first name" required>
    <br>
    <input type="text" size="40" name="lname" placeholder="Input last name" required>
    <br>
    <input type="text" size="40" name="email" placeholder="Input email" required>
    <br>
    <input type="text" size="40" name="phone" placeholder="Input phone">
    <br>
    <input type="submit" value="REGISTER">
</form>

<br>
<h3>
    <a href="/index.jsp">GO BACK!</a>
</h3>

</body>
</html>
