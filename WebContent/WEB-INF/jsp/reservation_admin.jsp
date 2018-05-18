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

#edit {
	background: mediumorchid;
}

#down {
	background: orangered;
}

#back {
	background: grey;
}

#logout {
	background: grey;
}

table {
	width: 200px;
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

div {
	display: inline-block;
}
</style>

<title>${resname}</title>
</head>
<body>

	Logged in as: ${username}
	<form:form method="GET" action="/RezervatiiNaturale/index">
		<td><input type="submit" class="button" id="logout"
			value="Logout" /></td>
	</form:form>
	<form:form method="GET" action="/RezervatiiNaturale/back">
		<td><input type="submit" class="button" id="back" value="Back" /></td>
	</form:form>
	<h1>${resname}</h1>
	<h2>${resloc}</h2>
	<form:form method="POST"
		action="/RezervatiiNaturale/reservationAdmin/edit">
		<form:input type="hidden" path="Id"></form:input>
		<form:input type="hidden" path="Nume"></form:input>
		<form:input type="hidden" path="Localizare"></form:input>
		<input type="submit" name="edit" class="button" id="edit" value="Edit" />
	</form:form>
	<form:form method="POST" action="/RezervatiiNaturale/download">
		<form:input type="hidden" path="Id"></form:input>
		<form:input type="hidden" path="Nume"></form:input>
		<form:input type="hidden" path="Localizare"></form:input>
		<input type="submit" class="button" id="down" value="Download" />
	</form:form>
	<div>
		<div>
			<h3>Habitate</h3>
			<table>
				<tr>
					<th>Denumire</th>
				</tr>
				<c:forEach items="${habitats}" var="element">
					<tr>
						<td>${element.getDenumire()}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<h3>Flora</h3>
			<table>
				<tr>
					<th>Specie</th>
					<th>Denumire Latina</th>
				</tr>
				<c:forEach items="${plants}" var="element">
					<tr>
						<td>${element.getSpecie()}</td>
						<td><i>${element.getDenumireLatina()}</i></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<h3>Fauna</h3>
			<table>
				<tr>
					<th>Specie</th>
					<th>Denumire Latina</th>
				</tr>
				<c:forEach items="${animals}" var="element">
					<tr>
						<td>${element.getSpecie()}</td>
						<td><i>${element.getDenumireLatina()}</i></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<h3>Monumente Istorice</h3>
			<table>
				<tr>
					<th>Denumire</th>
					<th>Perioada Constructiei</th>
				</tr>
				<c:forEach items="${monuments}" var="element">
					<tr>
						<td>${element.getSpecie()}</td>
						<td><i>${element.getDenumireLatina()}</i></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<h3>Arii Protejate</h3>
			<table>
				<tr>
					<th>Denumire</th>
				</tr>
				<c:forEach items="${areas}" var="element">
					<tr>
						<td>${element.getDenumire()}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>