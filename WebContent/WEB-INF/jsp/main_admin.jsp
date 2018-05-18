<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

#new {
	background: royalblue;
}

#search {
	background: saddlebrown;
}

#view {
	background: darkgreen;
}

#delete {
	background: darkred;
}

#logout {
	background: grey;
}

th, td {
	border: 2px solid coral;
}

body {
	background-color: palegreen;
}
</style>

<title>Rezervatii Naturale Main</title>
</head>
<body>

	Logged in as: ${username}
	<form:form method="GET" action="/RezervatiiNaturale/index">
		<td><input type="submit" class="button" id="logout"
			value="Logout" /></td>
	</form:form>
	<h2>Rezervatii Naturale</h2>
	<table>
		<tr>
			<form:form method="POST"
				action="/RezervatiiNaturale/reservationAdmin">
				<td><input type="submit" class="button" id="new" name="new"
					value="New" /></td>
			</form:form>
			<form:form method="GET" action="/RezervatiiNaturale/searchAdmin">
				<td><input type="submit" class="button" id="search"
					value="Search" /></td>
			</form:form>
		</tr>
	</table>

	<table>
		<tr>
			<th>Nume</th>
			<th>Localizare</th>
		</tr>
		<c:forEach items="${reslist}" var="element">
			<tr>
				<form:form method="POST"
					action="/RezervatiiNaturale/reservationAdmin">
					<form:input type="hidden" path="Id" value="${element.getId()}"></form:input>
					<form:input type="hidden" path="Nume" value="${element.getNume()}"></form:input>
					<form:input type="hidden" path="Localizare"
						value="${element.getLocalizare()}"></form:input>
					<td>${element.getNume()}</td>
					<td>${element.getLocalizare()}</td>
					<td><input type="submit" class="button" id="view" name="view"
						value="View" /></td>
					<td><input type="submit" class="button" id="delete"
						name="delete" value="Delete" /></td>
				</form:form>
			</tr>

		</c:forEach>
	</table>
</body>
</html>