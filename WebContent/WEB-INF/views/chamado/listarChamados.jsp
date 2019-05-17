<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.theme.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/css/sticky-footer-navbar.css" rel="stylesheet">

<title>SacHelp - Chamados</title>
</head>
<body>

	<div class="container-fluid">
	      <div class="fixed-top">
	      <jsp:include page="/WEB-INF/views/navBarDarkCorporation.jsp"></jsp:include>
			<div class="p-2 bg-info text-white">
				<h4 class="mt-5">Chamados</h4>
			</div>
			</div>
			<br>
			<br>
			<br>
			<br>
      <div class="row">
      <div class="col-sm-2">
      		<a href="<%=request.getContextPath()%>/novoChamado" class="btn btn-outline-primary my-2 my-sm-3" >Novo</a>
      		<a href="<%=request.getContextPath()%>/index" class="btn btn-outline-secondary my-2 my-sm-3" >Página Inicial</a>	
      	</div>
      	<div class="col-sm-4">
      		<form class="form-inline mt-2 mt-md-0" action="<%=request.getContextPath()%>/filtrarPorDescricaoOuTitulo">
      			<input class="form-control mr-sm-1" type="text" name="descricao"
				placeholder="Digite uma palavra chave" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-3" type="submit">Filtrar</button>
      		</form>
      	</div>
      </div>
		<table class="table table-hover table-striped">
		  <thead>
		    <tr >
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=id"
		      		class="btn btn-light">
		      		<b>Id</b>
		      	</a>
		      </th>
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=titulo" 
		      		class="btn btn-light">
		      		<b>Título</b>
		      	</a>
		      </th>
		      <th scope="col">
		      	<a href="#" 
		      		class="btn btn-light">
		      		<b>Descrição</b>
		      	</a>
		      </th>
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=dataAbertura" 
		      		class="btn btn-light">
		      		<b>Abertura</b>
		      	</a>
		      </th>
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=horaAbertura" 
		      		class="btn btn-light">
		      		<b>Hora</b>
		      	</a>
			  </th>
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=loja"
		      		class="btn btn-light">
		      		<b>Loja</b>
		      	</a>
		      </th>
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=contato"
		      		class="btn btn-light">
		      		<b>Contato</b>
		      	</a>
		      </th>
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=status"
		      		class="btn btn-light">
		      		<b>Status</b>
		      	</a>
		      </th>
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=tecResponsavel"
		      		class="btn btn-light">
		      		<b>Téc/Responsavel</b>
		      	</a>
		      </th>
		      <th scope="col">
		      <a href="#"
		      		class="btn btn-light">
		      		<b>Ação</b>
		      	</a>
		      </th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${listaChamados}" var="chamado">
	  		 	<tr>
	  		 	  <td>${chamado.id}</td>
	  		 	  <td style="width: 200px;">${chamado.titulo}</td>
	  		 	  <td >${chamado.descricao}</td>
			       <td><fmt:formatDate type="date" value="${chamado.dataAbertura.time}" pattern="dd/MM/yyyy"/></td>
			      <td>${chamado.horaAbertura}</td>
			      <td>${chamado.loja}</td>
			      <td>${chamado.contato}</td>
			      <td>${chamado.status}</td>
			      <td>${chamado.tecResponsavel}</td>
			      <td><a href="<%=request.getContextPath() %>/abrirEditarChamado?id=${chamado.id}" class="btn btn-light btn-xs" title="Editar"><span class="ui-icon ui-icon-pencil"></span></a>
			      	  <a href="<%=request.getContextPath() %>/?id=${chamado.id}" class="btn btn-light btn-xs" title="Excluir"><span class="ui-icon ui-icon-trash"></span></a>
			      	  <a href="<%=request.getContextPath() %>/abrirLinhaDoTempo?id=${chamado.id}" class="btn btn-light btn-xs" title="Linha do Tempo"><span class="ui-icon ui-icon-clock text-white"></span></a>
			      </td>
		    	</tr>
		  	</c:forEach>
		   </tbody>
		</table>
	</div>
	
	
	
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.js"></script>
	<script src="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<div class="fixed-bottom">	
	<footer class="footer mt-auto py-1">
	  <div class="container-fluid">
	    <span class="text-primary font-weight-bold">${registros} Registros</span>
	  </div>
	</footer>
	</div>

</body>
</html>