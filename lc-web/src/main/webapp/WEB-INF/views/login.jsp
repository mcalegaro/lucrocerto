<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lucro Certo</title>
<link
	href="<c:url value="resources/bootstrap-3.1.1-dist/css/bootstrap.min.css"/>"
	rel="stylesheet" />
<link href="<c:url value="resources/global.css"/>" rel="stylesheet" />
</head>
<body>
	<c:url value="/login" var="loginUrl" />
	<c:url value="/showSignup" var="registerUrl" />
	<div id="divLogin">
		<f:form action="${loginUrl}" method="post">
			<c:if test="${param.error != null}">
				<p>Invalid username/password.</p>
			</c:if>
			<c:if test="${param.logout != null}">
				<p>You have been logged out.</p>
			</c:if>
			<table>
				<tr>
					<td><label for="username">Username:</label></td>
					<td><input type="text" id="username" name="username"
						class="form-control" /></td>
				</tr>
				<tr>
					<td><label for="password">Password:</label></td>
					<td><input type="password" id="password" name="password"
						class="form-control" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
						<button type="submit" class="btn btn-success" style="width: 100%;">Log
							in</button></td>
				</tr>
			</table>
		</f:form>
	</div>
	<br>
	<div id="divRegister">
		<f:form action="${registerUrl}" method="get">
			<table>
				<tr>
					<td>
						<button type="submit" class="btn btn-primary">Register</button>
					</td>
				</tr>
				<tr>
					<td><a href="#" class="btn btn-primary" role="button">Learn
							more »</a></td>
				</tr>
			</table>
		</f:form>
	</div>
</body>
</html>