<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
		<title>SAC - Home</title>
	</head>
	<body>
		<h2>Bem-vindo ao SacHelp</h2>
		<a href="<%=request.getContextPath()%>/novoChamado" class="btn btn-outline-primary my-sm-3">Novo Chamado</a>
		<a href="<%=request.getContextPath()%>/listarChamados" class="btn btn-outline-primary my-sm-3">Listar Chamados</a>
		
	</body>
</html>