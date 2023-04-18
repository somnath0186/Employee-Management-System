<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String msg=(String)request.getAttribute("msg"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Management</title>
<style type="text/css">
form {
	margin-top: 10px;
}
form table {
	margin: auto;
	width: 100%;
}
tr {
	text-align: center;
}
fieldset table {
	margin: auto;
	text-align: left;
}
fieldset {
	margin: 15px 520px;
	text-align: center;
}
legend {
	color: white;
	background-color: #333;
}
body {
	background-image:
		url('https://www.xmple.com/wallpaper/linear-blue-white-highlight-gradient-1920x1080-c2-ffffff-e0ffff-l-50-a-165-f-21.svg');
	background-size: 100%;
}
#data {
	background-color: white;
	border: 1px solid black;
	width: 100%;
	border: 1px solid black;
}
#data td {
	border: 1px solid black;
	text-align: center;
}
</style>
</head>
<body>


<div align="center">
<form action="./create"  method="post">

<fieldset>
<legend>::: Create Admin Account:::</legend>

<table>
<tr>
<td>UserName</td>
<td><input type="text" name="username"></td>
</tr>
<tr>
<td>Password</td>
<td><input type="text" name="password"></td>
</tr>
</table>
</fieldset>

<input type="submit" value="Create Account">

</form>
<%if(msg !=null){ %>
<%=msg %>
<%}else{ %>

<%} %>

</div>
</body>
</html>