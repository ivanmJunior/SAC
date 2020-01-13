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
	<link href="<%=request.getContextPath()%>/resources/css/botai_iOS.css"
	rel="stylesheet">

<title>SacHelp - Lembretes</title>
</head>
<body>

	<div class="container-fluid">
	      <div class="fixed-top">
	      <jsp:include page="/WEB-INF/views/navBarDarkCorporation.jsp"></jsp:include>
			<div class="p-2 bg-info text-white">
				<h4 class="mt-5">Lembretes ${lembrete.assunto}</h4>
			</div>
			</div>
			<br>
			<br>
			<br>
			<br>
      <div class="row">
      <div class="col-sm-2">
      		<a href="<%=request.getContextPath()%>/index" class="btn btn-outline-secondary my-2 my-sm-3">Página Inicial</a>	
      	</div>
      	<div class="col-sm-4">
      		<form class="form-inline mt-2 mt-md-0" action="<%=request.getContextPath()%>/filtrarPorDescricaoOuTitulo">
      			<input class="form-control mr-sm-1" type="text" name="descricao"
				placeholder="Digite uma palavra chave" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-3" type="submit">Filtrar</button>
      		</form>
      	</div>
      </div>
		<table class="table table-hover">
		  <thead>
		    <tr >
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=id"
		      		class="btn btn-light">
		      		<b>Id</b>
		      	</a>
		      </th>
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=dataFechamento"
		      		class="btn btn-light">
		      		<b>Data</b>
		      	</a>
			  </th>
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=loja"
		      		class="btn btn-light">
		      		<b>Hora</b>
		      	</a>
		      </th>
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=dataAbertura" 
		      		class="btn btn-light">
		      		<b>Assunto</b>
		      	</a>
		      </th>
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=titulo" 
		      		class="btn btn-light">
		      		<b>Data Registro</b>
		      	</a>
		      </th>
		      <th scope="col">
		      	<a href="#" 
		      		class="btn btn-light">
		      		<b>Hora Registro</b>
		      	</a>
		      </th>
		      <th scope="col">
		      	<a href="<%=request.getContextPath()%>/classificarLitaChamados?coluna=contato"
		      		class="btn btn-light">
		      		<b>Ativo</b>
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
		  	<c:forEach items="${listaLembretes}" var="lembrete">
	  		 	<tr>
	  		 	  <td>${lembrete.id}</td>
	  		 	  <td><fmt:formatDate type="date" value="${lembrete.data.time}" pattern="dd/MM/yyyy"/></td>
			      <td>${lembrete.hora}</td>
			      <td>${lembrete.assunto}</td>
			      <td><fmt:formatDate type="date" value="${lembrete.dataRegistro.time}" pattern="dd/MM/yyyy"/></td>
	  		 	  <td>${lembrete.horaRegistro}</td>
			      <td>${lembrete.ativo? "Sim" : "Não"}</td>
			      <td><a id="abrirEditarLembrete" href="<%=request.getContextPath() %>/abrirEditar?id=${lembrete.id}" class="btn btn-light btn-xs" title="Editar"><span class="ui-icon ui-icon-pencil"></span></a>
			      	  <a href="<%=request.getContextPath() %>/?id=${lembrete.id}" class="btn btn-light btn-xs" title="Excluir"><span class="ui-icon ui-icon-trash"></span></a>
			      	  <a href="<%=request.getContextPath() %>/abrirLinhaDoTempo?id=${lembrete.id}" class="btn btn-light btn-xs" title="Linha do Tempo"><span class="ui-icon ui-icon-clock text-white"></span></a>
			      </td>
		    	</tr>
		  	</c:forEach>
		   </tbody>
		</table>
	</div>
	
	<!-- Modal Editar Lembrete -->
	
	<div class="modal fade " id="editarLembrete" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Editar Lembrete</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" onclick="atualizarPagina();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="needs-validation" novalidate
						action="<%=request.getContextPath()%>/adicionarLembrete"
						method="post">
						<div class=row>
							<div class="col-md-4 mb-3">
								<label>Ativo</label>
								<main>
									<div class="liga-desliga">
										<input type="checkbox" class="liga-desliga__checkbox" id="checkAtivo" name="ativo">
										<label for="checkAtivo" class="liga-desliga__botao"></label>
									</div>
								</main>
							</div>
							<div class="col-md-8 mb-3">
								<label id="lblIdLembreteModalEditar" for="inputIdModalEditar">Id Lembrete: ${lembreteEditar.id}
								</label> <label class="form-control"> ${lembreteEditar.id}</label>
								<input type="text" hidden="true" name="id"
								class="form-control" id="inputIdModalEditar">
							</div>
						</div>
						<div class=row>
						<div class="col-md-12 mb-3">
							<label for="descricaoLembrete">Descrição</label>
							<textarea name="assunto" class="form-control" 
								id="descricaoLembrete" placeholder="O que deseja lembrar?"
								rows="4" required>${lembreteEditar.assunto}</textarea>
							<div class="invalid-feedback">A descrição é obrigatória.</div>
						</div>
						</div>
						<div class=row>
						<div class="col-md-6 mb-3">
							<label for="data">Data</label>
						<input type="date" name="data"  class="form-control" id="data">
						</div>
						<div class="col-md-6 mb-3">
							<label for="hora">Hora</label>
						<input type="time" name="hora"  class="form-control" id="hora" required>
							<div class="invalid-feedback">Informe a hora.</div>
						</div>
						</div>
						<div class=row>
							<div class="col-md-2 mb-3">
								<label>Repetir</label>
								<main>
									<div class="liga-desliga">
										<input type="checkbox" class="liga-desliga__checkbox" name="repetir" id="checkRepetir">
										<label for="checkRepetir" class="liga-desliga__botao"></label>
									</div>
								</main>
							</div>
							<div class="col-md-4 mb-3">
								<label>A partir da data?</label>
								<main>
									<div class="liga-desliga">
										<input disabled type="checkbox" class="liga-desliga__checkbox" id="checkAPartirData" name="repetirAPartirData">
										<label for="checkAPartirData" class="liga-desliga__botao"></label>
									</div>
								</main>
							</div>
						</div>
						<div class=row>
							<div class="col-md-12 mb-3" >
								<select disabled class="form-control" id="cbBoxRepetir" name="tipoRepetir">
									<option value="">Selecione aqui</option>
									<option value="d">Diário</option>
									<option value="m">Por Mês</option>
									<option value="s">Por Semana</option>
									<option value="n">Na Data</option>
								</select>
							</div>
						</div>
						<div class=row>
						<div align="center" class="col-md-2 mb-3">
								<label>Seg</label>
								<main>
									<div align="center" class="liga-desliga">
										<input disabled type="checkbox" class="liga-desliga__checkbox" id="checkSeg" name="seg">
										<label for="checkSeg" class="liga-desliga__botao"></label>
									</div>
								</main>
							</div>
							<div align="center" class="col-md-2 mb-3">
								<label>Ter</label>
								<main>
									<div class="liga-desliga">
										<input disabled type="checkbox" class="liga-desliga__checkbox" id="checkTer" name="ter">
										<label for="checkTer" class="liga-desliga__botao"></label>
									</div>
								</main>
							</div>
							<div align="center" class="col-md-2 mb-3">
								<label>Qua</label>
								<main>
									<div class="liga-desliga">
										<input disabled type="checkbox" class="liga-desliga__checkbox" id="checkQua" name="qua">
										<label for="checkQua" class="liga-desliga__botao"></label>
									</div>
								</main>
							</div>
							<div align="center" class="col-md-2 mb-3">
								<label>Qui</label>
								<main>
									<div class="liga-desliga">
										<input disabled type="checkbox" class="liga-desliga__checkbox" id="checkQui" name="qui">
										<label for="checkQui" class="liga-desliga__botao"></label>
									</div>
								</main>
							</div>
							<div align="center" class="col-md-2 mb-3">
								<label>Sex</label>
								<main>
									<div class="liga-desliga">
										<input disabled type="checkbox" class="liga-desliga__checkbox" id="checkSex" name="sex">
										<label for="checkSex" class="liga-desliga__botao"></label>
									</div>
								</main>
							</div>
							<div align="center" class="col-md-2 mb-3">
								<label>Sab</label>
								<main>
									<div class="liga-desliga">
										<input disabled type="checkbox" class="liga-desliga__checkbox" id="checkSab" name="sab">
										<label for="checkSab" class="liga-desliga__botao"></label>
									</div>
								</main>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal" onclick="atualizarPagina();">Fechar</button>
							<button type="submit" class="btn btn-success">Confirmar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/lembretesScript.js"></script>
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