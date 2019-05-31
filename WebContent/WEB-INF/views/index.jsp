<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.theme.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.theme.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"
	rel="stylesheet">
	<link
	href="<%=request.getContextPath()%>/resources/css/sticky-footer-navbar.css"
	rel="stylesheet">
<title>SAC - Home</title>
</head>
<body>
	<div class="container-fluid">
		<div class="fixed-top">
			<jsp:include page="/WEB-INF/views/navBarDarkCorporation.jsp"></jsp:include>
			<br><br>
			<div class="row">
				<div class="col"></div>
				<div class="col">
					<div class="p-2 mb-3 bg-danger text-white">
						<h3 class="mt-3">
						<a href="<%=request.getContextPath()%>/index" class="btn btn-danger">
							Chamados Pendentes <span class="badge badge-light">${contaPendentes.totalPendentes}</span>
						</a>	
							<a href="<%=request.getContextPath()%>/indexAbertos" class="btn btn-danger">
								Abertos <span class="badge badge-light">${contaPendentes.qtdAbertos}</span>
							</a>
							<a href="<%=request.getContextPath()%>/indexEmAndamento" class="btn btn-danger">
								Em Andamento <span class="badge badge-light">${contaPendentes.qtdEmAndamento}</span>
							</a>
							<a href="<%=request.getContextPath()%>/indexAtrasados" class="btn btn-danger">
								Atrasados <span class="badge badge-light">${contaPendentes.qtdAtrasados}</span>
							</a>
							<a href="<%=request.getContextPath()%>/indexPraHoje" class="btn btn-danger">
								Pra Hoje <span class="badge badge-light">${contaPendentes.qtdPrazoPraHoje}</span>
							</a>
						</h3>
					</div>
				</div>
				
			</div>
		</div>
		<br> <br> <br>
		<div class="row">
			<div class="col-6">
			<br><br><br>
				<div class="col-12 p-1">
					<br> 
					<a title="Novo"
						href="<%=request.getContextPath()%>/novoChamado"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/Novo5.png">
					</a> 
					<a title="Listar Todos os Chamados"
						href="<%=request.getContextPath()%>/listarChamados"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/Listar.png">
					</a> 
					<a id="btnLinhaDoTempo" title="Linha do Tempo do Chamado"
						href="#" class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/Timeline3.png">
					</a> 
					<a title="Adicionar Progresso"
						href="" data-toggle="modal" data-target="#adicionarProgressoModal"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/NovoProgresso3.png">
					</a> 
					<a title="Finalizar Chamado" href="" data-toggle="modal" data-target="#finalizarChamadoModal"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/FinalizarChamado2.png">
					</a> 
					<a title="Imprimir Chamado" href="<%=request.getContextPath()%>/index"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/imprimirChamado2.png">
					</a>
					<a title="Enviar Por Email" href="<%=request.getContextPath()%>/index"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/Enviar.png">
					</a>
					<a title="Atualizar Prazo" href="" data-toggle="modal" data-target="#atualizarPrazo"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/NovoPrazo2.png">
					</a>
					<a title="Atualizar Prazo" href="<%=request.getContextPath()%>/index"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/Eliminar.png">
					</a>
					<a title="Atualizar Prazo" href="<%=request.getContextPath()%>/index"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/semFuncao.png">
					</a>
					<a title="Atualizar Prazo" href="<%=request.getContextPath()%>/index"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/semFuncao.png">
					</a>
					<a title="Atualizar Prazo" href="<%=request.getContextPath()%>/index"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/semFuncao.png">
					</a>
					
				</div>
			</div>
			
			<div class="col-6">
				<br> <br> <br>
				<div style="overflow: auto; width: 720px; height: 440px;">
					<table id="tbPendentes" class="table table-hover">
						<thead>
							<tr>
								<th scope="col">Id</th>
								<th scope="col">Título</th>
								<th scope="col">dia(s) Atrasado</th>
								<th scope="col">Loja</th>
								<th scope="col">Status</th>
								<th scope="col">Prazo</th>
								<th scope="col">Ação</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listaChamados}" var="chamado">
								<tr id="linhaTbPendentes${chamado.id}"
									onclick="pegarIdChamado(this);">
									<td>${chamado.id}</td>
									<td>${chamado.titulo}</td>
									<td>${chamado.diferencaTempoDeEntrega}</td>
									<td>${chamado.loja}</td>
									<td>${chamado.status}</td>
									<td><fmt:formatDate type="date"
											value="${chamado.prazoSolucao.time}" pattern="dd/MM/yyyy" /></td>
									<td><a
										href="<%=request.getContextPath()%>/abrirEditarChamado?id=${chamado.id}"
										class="btn btn-light btn-xs" title="Editar"> <span
											class="ui-icon ui-icon-pencil"></span></a> <a href=""
										class="btn btn-light btn-xs" title="Excluir"> <span
											class="ui-icon ui-icon-trash Red"></span></a> <a href=""
										class="btn btn-light btn-xs" title="Adicionar progresso"
										data-toggle="modal" data-target="#adicionarProgressoModal"> <span
											class="ui-icon ui-icon-signal"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade " id="adicionarProgressoModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Registrar
						Progresso</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" onclick="atualizarPagina();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="needs-validation" novalidate
						action="<%=request.getContextPath()%>/adicionarHistoricoChamado"
						method="post">
						<div class="col-md-7 mb-3">
							<label id="lblIdChamado" for="exampleInputCountryFuncao">Chamado:
							</label> <input type="text" hidden="true" name="chamado.id" class="form-control"
								id="inputId">
						</div>
						<div class="col-md-12 mb-3">
							<label for="txtOcorrencia">Descrição</label>
							<textarea name="ocorrencia" class="form-control"
								id="txtOcorrencia" placeholder="Descreva o progresso do chamado"
								rows="6" required></textarea>
							<div class="invalid-feedback">A descrição é obrigatória.</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal" onclick="atualizarPagina();">Fechar</button>
							<button type="submit" class="btn btn-success">Adicionar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal Finalizar -->
	
	<div class="modal fade " id="finalizarChamadoModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Finalizar Chamado</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" onclick="atualizarPagina();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="needs-validation" novalidate
						action="<%=request.getContextPath()%>/finalizarChamado"
						method="post">
						<div class="col-md-7 mb-3">
							<label id="lblIdChamadoModalFinalizar" for="inputIdModalFinalizar">Chamado:
							</label> <input type="text" hidden="true" name="id"
							class="form-control" id="inputIdModalFinalizar">
						</div>
						<div class="col-md-12 mb-3">
							<label for="solucao">Solução</label>
							<textarea name="solucao" class="form-control"
								id="txtSolucao" placeholder="Descreva a solução"
								rows="6" required></textarea>
							<div class="invalid-feedback">A descrição é obrigatória.</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal" onclick="atualizarPagina();">Fechar</button>
							<button type="submit" class="btn btn-success">Finalizar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal Atualizar Prazo -->
	
	<div class="modal fade " id="atualizarPrazo" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Atualizar Prazo</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" onclick="atualizarPagina();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="needs-validation" novalidate
						action="<%=request.getContextPath()%>/index#"
						method="post">
						<div class="col-md-7 mb-3">
							<label id="lblIdAtualizarPrazo" for="inputIdAtualizarPrazo">Chamado:
							</label>
							<input type="text" hidden="true" name="id"
							class="form-control" id="inputIdAtualizarPrazo">
						</div>
						<div class="col-md-8 mb-3">
							<label for="PrazoSolucao">Novo Prazo</label>
						<input type="date" name="prazoSolucao" class="form-control" id="PrazoSolucao" required>
							<div class="invalid-feedback">Informe uma de Prazo Inicial.</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal" onclick="atualizarPagina();">Fechar</button>
							<button type="submit" class="btn btn-success">Finalizar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<footer class="footer mt-auto py-1">
	  <div class="container-fluid">
	    <span class="text-danger font-weight-bold">Certificado Digital Vence Em: ${certificadoValidade} dias.</span>
	  </div>
	</footer>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/scriptSAC.js"></script>
</body>
</html>