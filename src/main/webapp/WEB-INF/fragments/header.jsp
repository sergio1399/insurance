<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Spring MVC test Insurance</title>
	<spring:url value="/resources/static/hello.css" var="coreCss" />
	<spring:url value="/resources/static/bootstrap.min.css"
				var="bootstrapCss" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${coreCss}" rel="stylesheet" />
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
					onclick="location.href='${urlAddContract}'">Add contract</button>
			<button class="btn btn-success"
					onclick="location.href='${excelUrl}'">Load to Excel</button>
		</div>
	</div>
</nav>