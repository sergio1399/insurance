<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Spring MVC test Insurance</title>
	<link href="/webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/contracts/add" var="urlAddContract" />
<spring:url value="/contracts/excel" var="excelUrl" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Spring MVC Form</a>
		</div>
		<div id="navbar">
			<button class="btn btn-dark"
					onclick="location.href='${urlAddContract}'">Add Contract</button>
			<button class="btn btn-success"
					onclick="$.post('${excelUrl}')">Load to Excel</button>
		</div>
	</div>
</nav>