<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lucro Certo</title>
</head>
<body>
	<h1>Lucro Certo!</h1>
	<div class="alert alert-success">Parab�ns, foi enviada uma chave
		de confirma��o para o email que voc� informou, verifique sua caixa.
		Esta chave tem validade de 15 dias.</div>
	<a href='<c:url value="/"/>' class="btn btn-primary" role="button">Continuar</a>
</body>
</html>