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

#view {
	background: darkgreen;
}

#search {
	background: saddlebrown;
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

	Browsing as Guest
	<h2>Rezervatii Naturale</h2>
	<form:form method="GET" action="/RezervatiiNaturale/search">
		<td><input type="submit" class="button" id="search"
			value="Search" /></td>
	</form:form>
	<table>
		<tr>
			<th>Nume</th>
			<th>Localizare</th>
		</tr>
		<c:forEach items="${reslist}" var="element">
			<form:form method="POST" action="/RezervatiiNaturale/reservation">
				<form:input type="hidden" path="Id" value="${element.getId()}"></form:input>
				<form:input type="hidden" path="Nume" value="${element.getNume()}"></form:input>
				<form:input type="hidden" path="Localizare"
					value="${element.getLocalizare()}"></form:input>
				<tr>
					<td>${element.getNume()}</td>
					<td>${element.getLocalizare()}</td>
					<td><input type="submit" class="button" id="view" value="View" /></td>
				</tr>
			</form:form>
		</c:forEach>
	</table>
</body>
</html>