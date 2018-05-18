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

#search {
	background: saddlebrown;
}

#view {
	background: darkgreen;
}

div th {
	border: 3px solid crimson;
}

div td {
	border: 2px solid coral;
}

body {
	background-color: palegreen;
}
</style>

<title>Rezervatii Naturale Search</title>
</head>
<body>

	Browsing as Guest
	<h2>Rezervatii Naturale</h2>
	<form:form method="POST" action="/RezervatiiNaturale/search">
		<table>
			<tr>
				<td><form:label path="nume">Nume</form:label></td>
				<td><form:input path="nume" /></td>

			</tr>
			<tr>
				<td><form:label path="localizare">Localizare</form:label></td>
				<td><form:input path="localizare" /></td>
			</tr>
			<tr>
				<td><form:label path="bonusHabitate">Habitate</form:label></td>
				<td><form:input path="bonusHabitate" /></td>
				<td><form:label path="bonusPlante">Plante</form:label></td>
				<td><form:input path="bonusPlante" /></td>
				<td><form:label path="bonusAnimale">Animale</form:label></td>
				<td><form:input path="bonusAnimale" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" class="button" id="search"
					value="Search" /></td>
			</tr>
		</table>
	</form:form>
	<div>
		<table>
			<tr>
				<th>Nume</th>
				<th>Localizare</th>
			</tr>
			<c:forEach items="${reslist}" var="element">
				<tr>
					<form:form method="POST" action="/RezervatiiNaturale/reservation">
						<form:input type="hidden" path="Id" value="${element.getId()}"></form:input>
						<form:input type="hidden" path="Nume" value="${element.getNume()}"></form:input>
						<form:input type="hidden" path="Localizare"
							value="${element.getLocalizare()}"></form:input>
						<td>${element.getNume()}</td>
						<td>${element.getLocalizare()}</td>
						<td><input type="submit" name="view" class="button" id="view"
							value="View" /></td>
					</form:form>
				</tr>

			</c:forEach>
		</table>
	</div>
</body>
</html>