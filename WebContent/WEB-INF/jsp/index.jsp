<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<style>
.button {
	width: 150px;
	color: #eee;
	line-height: 25px;
	font-size: 14px;
	padding: 0 10px;
}

#login {
	background: forestgreen;
}

#guest {
	background: goldenrod;
}

body {
	background-color: lightblue;
}
</style>
<title>Rezervatii Naturale Login</title>
</head>
<body>

	<h2>Login</h2>
	<h3>${label}</h3>
	<form:form method="POST" action="/RezervatiiNaturale/login">
		<table>
			<tr>
				<td><form:label path="username">Username</form:label></td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><input class="button" id="login" type="submit"
					value="Submit" /></td>
			</tr>
		</table>
	</form:form>

	<form:form method="GET" action="/RezervatiiNaturale/login">
		<input type="submit" class="button" id="guest" value="Guest" />
	</form:form>

</body>
</html>