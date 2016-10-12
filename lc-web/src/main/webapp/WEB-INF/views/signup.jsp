<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="<c:url value="resources/bootstrap-3.1.1-dist/css/bootstrap.min.css"/>"
	rel="stylesheet" />
<link href="<c:url value="resources/global.css"/>" rel="stylesheet" />
<title>Lucro Certo</title>
</head>
<body>
	<c:url value="/signup" var="signup" />
	<div>
		<f:form action="${signup}" method="POST" modelAttribute="userSignup">
			<table border="0">
				<tr>
					<td><label for="username">Username:</label></td>
					<td><f:input type="text" id="username" name="username"
							path="user.name" class="form-control" /></td>
				</tr>
				<tr>
					<td><label for="usernameConfirm">Confirm Username:</label></td>
					<td><f:input type="text" id="usernameConfirm"
							name="usernameConfirm" path="confirmName" class="form-control" /></td>
				</tr>
				<tr>
					<td><label for="password">Password:</label></td>
					<td><f:input type="password" id="password" name="password"
							path="user.pwd" class="form-control" /></td>
				</tr>
				<tr>
					<td><label for="password">Confirmm Password:</label></td>
					<td><f:input type="password" id="passwordConfirm"
							name="passwordConfirm" path="confirmPwd" class="form-control" /></td>
				</tr>
				<tr>
					<td><c:url value="/" var="backUrl" /> <a href="${backUrl}"
						class="btn btn-primary" role="button">Back</a></td>
					<td>
						<button type="submit" class="btn btn-success"
							style="width: 100%;">OK</button>
					</td>
				</tr>
			</table>
		</f:form>
	</div>
</body>
</html>