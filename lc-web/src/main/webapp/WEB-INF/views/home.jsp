<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lucro Certo</title>
<link
	href="<c:url value="resources/bootstrap-3.1.1-dist/css/bootstrap.min.css"/>"
	rel="stylesheet" />
<link
	href="<c:url value="resources/bootstrap-3.1.1-dist/css/bootstrap-theme.min.css"/>"
	rel="stylesheet" />
<link href="<c:url value="resources/global.css"/>" rel="stylesheet" />
<script src="<c:url value="resources/js/jquery_1.11.0/jquery.min.js"/>"></script>
<script
	src="<c:url value="resources/bootstrap-3.1.1-dist/js/bootstrap.min.js"/>"></script>
</head>
<body role="document">
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
<!-- 				<button type="button" class="navbar-toggle" data-toggle="collapse" -->
<!-- 					data-target=".navbar-collapse"> -->
<!-- 					<span class="sr-only">Toggle navigation</span> <span -->
<!-- 						class="icon-bar"></span> <span class="icon-bar"></span> <span -->
<!-- 						class="icon-bar"></span> -->
<!-- 				</button> -->
				<a class="navbar-brand" href="#">LucroCerto</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
<!-- 					<li class="dropdown"><a href="#" class="dropdown-toggle" -->
<!-- 						data-toggle="dropdown">Dropdown <b class="caret"></b></a> -->
<!-- 						<ul class="dropdown-menu"> -->
<!-- 							<li><a href="#">Action</a></li> -->
<!-- 							<li><a href="#">Another action</a></li> -->
<!-- 							<li><a href="#">Something else here</a></li> -->
<!-- 							<li class="divider"></li> -->
<!-- 							<li class="dropdown-header">Nav header</li> -->
<!-- 							<li><a href="#">Separated link</a></li> -->
<!-- 							<li><a href="#">One more separated link</a></li> -->
<!-- 						</ul></li> -->
					<li><a href="<c:url value="/logout"/>">Logout</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div class="container theme-showcase" role="main" style="margin: 75px;">
		<div class="alert alert-success">Bem-vindo ${userName} ao Lucro Certo!</div>
		<a href='<c:url value="/logout"/>' class="btn btn-primary"
			role="button">logout</a>
	</div>
</body>
</html>