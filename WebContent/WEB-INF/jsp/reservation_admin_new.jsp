<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script>
	function func(x) {
		document.getElementById(x).style.display = document
				.getElementById(x).style.display === 'block' ? 'none'
				: 'block';
	};

	function gohab(id,denumire) {
		window.location="/RezervatiiNaturale/reservationAdmin/linkHabitat?id="+id+"&denumire="+denumire;
	};
	function goplant(id,specie,denumire) {
		window.location="/RezervatiiNaturale/reservationAdmin/linkPlant?id="+id+"&specie="+specie+"&denumire="+denumire;
	};
	function goanimal(id,specie,denumire) {
		window.location="/RezervatiiNaturale/reservationAdmin/linkAnimal?id="+id+"&specie="+specie+"&denumire="+denumire;
	};
</script>
<style>
table {
	width: 200px;
}

.button {
	width: 150px;
	color: #eee;
	line-height: 25px;
	font-size: 14px;
	padding: 0 10px;
}

#add {
	background: lawngreen;
}

#edit {
	background: mediumorchid;
}

#back {
	background: grey;
}

#logout {
	background: grey;
}

ul {
	display: none;
	position: absolute;
	margin: 0;
	background: #dddddd;
}

ul>li {
	width: 120px;
	background: #eee;
	line-height: 25px;
	font-size: 14px;
	padding: 0 10px;
}

ul>li:hover {
	background: #aaa;
}

div th {
	border: 3px solid crimson;
}

div td {
	border: 2px solid coral;
}

td table {
	margin: 0px;
	top: 0px;
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
	<h2>${resname}</h2>
	<h3>${label}</h3>
	<form:form method="POST"
		action="/RezervatiiNaturale/reservationAdmin/create">
		<form:input type="hidden" path="Id"></form:input>
		<table>
			<tr>
				<td><form:label path="Nume">Nume</form:label></td>
				<td><form:input path="Nume" /></td>
			</tr>
			<tr>
				<td><form:label path="Localizare">Localizare</form:label></td>
				<td><form:input path="Localizare" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" class="button" id="edit"
					value="${buttonvalue}" /></td>
			</tr>
		</table>
	</form:form>
	<table>
		<tr>
			<td>
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
					<c:if test="${not empty allhabitats}">
						<button class="button" id="add" onclick="func('list0')">Add
							Habitat</button>
						<ul id="list0">
							<c:forEach items="${allhabitats}" var="element">
								<li
									onclick="gohab(${element.getId()},'${element.getDenumire()}');">${element.getDenumire()}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</td>
			<td>
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

					<c:if test="${not empty allplants}">
						<button class="button" id="add" onclick="func('list1')">Add
							Plant</button>
						<ul id="list1">
							<c:forEach items="${allplants}" var="element">
								<li
									onclick="goplant(${element.getId()},'${element.getSpecie()}','${element.getDenumireLatina()}');">${element.getSpecie()}</li>
							</c:forEach>
						</ul>
					</c:if>

				</div>
			</td>
			<td>
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

					<c:if test="${not empty allanimals}">
						<button class="button" id="add" onclick="func('list2')">Add
							Animal</button>
						<ul id="list2">
							<c:forEach items="${allanimals}" var="element">
								<li
									onclick="goanimal(${element.getId()},'${element.getSpecie()}','${element.getDenumireLatina()}');">${element.getSpecie()}</li>
							</c:forEach>
						</ul>
					</c:if>

				</div>
			</td>
			<td>
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
			</td>
			<td>
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
			</td>
		</tr>
	</table>
</body>
</html>