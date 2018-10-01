<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Spring MVC test Insurance</title>
	<link href="/webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/contracts/add" var="urlAddContract" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Spring MVC Form</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlAddContract}">Add Contract</a></li>
			</ul>
		</div>
	</div>
</nav>