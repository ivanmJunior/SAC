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
	href="<%=request.getContextPath()%>/resources/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/botai_iOS.css"
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
			<br>
			<br>
			<div class="row">
				<div class="col">
					<input type="checkbox" name="vehicle3" id="checkAbas" hidden="true"
						value="${checkAbaIndex}" ${checkAbaIndex?	'checked'	:	''	}><br>
				</div>
				<!-- FLAG ABAS -->
				<div class="col">
					<div class="p-2 mb-3 bg-danger text-white">
						<ul class="nav nav-tabs" id="myTab" role="tablist">
							<li class="nav-item" id="ERP"><a
								class="nav-link text-white" id="home-tab" data-toggle="tab"
								href="#home" role="tab" aria-controls="home"
								aria-selected="true">ERP</a></li>
							<li class="nav-item" id="Internos"><a
								class="nav-link text-white" id="profile-tab" data-toggle="tab"
								href="#perfil" role="tab" aria-controls="home"
								aria-selected="false">Internos</a></li>
						</ul>
						<div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="home" role="tabpanel"
								aria-labelledby="home-tab">
								<h3 class="mt-3">
									<a href="<%=request.getContextPath()%>/index"
										class="btn btn-danger"> Chamados Pendentes <span
										class="badge badge-light">${contaPendentes.totalPendentes}</span>
									</a> <a href="<%=request.getContextPath()%>/indexAbertos"
										class="btn btn-danger"> Abertos <span
										class="badge badge-light">${contaPendentes.qtdAbertos}</span>
									</a> <a href="<%=request.getContextPath()%>/indexEmAndamento"
										class="btn btn-danger"> Em Andamento <span
										class="badge badge-light">${contaPendentes.qtdEmAndamento}</span>
									</a> <a href="<%=request.getContextPath()%>/indexAtrasados"
										class="btn btn-danger"> Atrasados <span
										class="badge badge-light">${contaPendentes.qtdAtrasados}</span>
									</a> <a href="<%=request.getContextPath()%>/indexPraHoje"
										class="btn btn-danger"> Pra Hoje <span
										class="badge badge-light">${contaPendentes.qtdPrazoPraHoje}</span>
									</a>
								</h3>
							</div>
							<div class="tab-pane fade show active" id="perfil"
								role="tabpanel" aria-labelledby="profile-tab">
								<h3 class="mt-3">
									<a href="<%=request.getContextPath()%>/indexTI"
										class="btn btn-danger"> Chamados Pendentes <span
										class="badge badge-light">${contaPendentes.totalPendentes}</span>
									</a> <a href="<%=request.getContextPath()%>/indexAbertosTI"
										class="btn btn-danger"> Abertos <span
										class="badge badge-light">${contaPendentes.qtdAbertos}</span>
									</a> <a href="<%=request.getContextPath()%>/indexEmAndamentoTI"
										class="btn btn-danger"> Em Andamento <span
										class="badge badge-light">${contaPendentes.qtdEmAndamento}</span>
									</a> <a href="<%=request.getContextPath()%>/indexAtrasadosTI"
										class="btn btn-danger"> Atrasados <span
										class="badge badge-light">${contaPendentes.qtdAtrasados}</span>
									</a> <a href="<%=request.getContextPath()%>/indexPraHojeTI"
										class="btn btn-danger"> Pra Hoje <span
										class="badge badge-light">${contaPendentes.qtdPrazoPraHoje}</span>
									</a>
								</h3>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br> <br> <br> <br>
		<div class="row">
			<div class="col-6">
				<br>
				<br>
				<br>
				<div class="col-12 p-1">
					<br> <a title="Novo"
						href="<%=request.getContextPath()%>/novoChamado"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/Novo5.png">
					</a> <a title="Listar Todos os Chamados"
						href="<%=request.getContextPath()%>/listarChamados"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/Listar.png">
					</a> <a id="btnLinhaDoTempo" title="Linha do Tempo do Chamado" href="#"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/Timeline3.png">
					</a> <a title="Adicionar Progresso" id="linkAddProgresso" href=""
						data-toggle="modal" data-target="#adicionarProgressoModal"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/NovoProgresso3.png">
					</a> <a title="Finalizar Chamado" id="linkFinalizarChamado" href=""
						data-toggle="modal" data-target="#finalizarChamadoModal"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/FinalizarChamado2.png">
					</a> <a title="Imprimir Chamado"
						href="<%=request.getContextPath()%>/index" class="btn btn-light">
						<img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/imprimirChamado2.png">
					</a> <a title="Enviar Por Email"
						href="<%=request.getContextPath()%>/index" class="btn btn-light">
						<img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/Enviar.png">
					</a> <a title="Atualizar Prazo" id="linkAtualizarPrazo" href=""
						data-toggle="modal" data-target="#atualizarPrazo"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/atualizarPrazo4.png">
					</a> <a title="Eliminar Chamado"
						href="<%=request.getContextPath()%>/index" class="btn btn-light">
						<img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/Eliminar.png">
					</a> <a title="Atualizar Status" id="linkAtualizarStatus" href=""
						data-toggle="modal" data-target="#atualizarStatus"
						class="btn btn-light"> <img alt="" width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/atualizarStatus5.png">
					</a> <a title="Criar lembrete" href="" data-toggle="modal"
						data-target="#addLembrete" class="btn btn-light"> <img alt=""
						width="145px"
						src="<%=request.getContextPath()%>/resources/imagens/lembrete3.png">
					</a> <a title="Sem Fun��o" href="<%=request.getContextPath()%>/index"
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
								<th scope="col">T�tulo</th>
								<th scope="col">Dia(s)p/ Prazo</th>
								<th scope="col">Loja</th>
								<th scope="col">Status</th>
								<th scope="col">Prazo</th>
								<th scope="col">A��o</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listaChamados}" var="chamado">
								<tr id="linhaTbPendentes${chamado.id}">
									<td>${chamado.id}</td>
									<td>${chamado.titulo}</td>
									<td align="center">${chamado.diferencaTempoDeEntrega}</td>
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
										data-toggle="modal" data-target="#adicionarProgressoModal">
											<span class="ui-icon ui-icon-signal"></span>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Adicionar Progresso -->

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
							</label> <input type="text" hidden="true" name="chamado.id"
								class="form-control" id="inputId">
						</div>
						<div class="col-md-12 mb-3">
							<label for="txtOcorrencia">Descri��o</label>
							<textarea name="ocorrencia" class="form-control"
								id="txtOcorrencia" placeholder="Descreva o progresso do chamado"
								rows="6" required></textarea>
							<div class="invalid-feedback">A descri��o � obrigat�ria.</div>
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
					<h5 class="modal-title" id="exampleModalLabel">Finalizar
						Chamado</h5>
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
							<label id="lblIdChamadoModalFinalizar"
								for="inputIdModalFinalizar">Chamado: </label> <input type="text"
								hidden="true" name="id" class="form-control"
								id="inputIdModalFinalizar">
						</div>
						<div class="col-md-12 mb-3">
							<label for="solucao">Solu��o</label>
							<textarea name="solucao" class="form-control" id="txtSolucao"
								placeholder="Descreva a solu��o" rows="6" required></textarea>
							<div class="invalid-feedback">A descri��o � obrigat�ria.</div>
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
						action="<%=request.getContextPath()%>/atualizarPrazo"
						method="post">
						<div class="col-md-7 mb-3">
							<label id="lblIdAtualizarPrazo" for="inputIdAtualizarPrazo">Chamado:
							</label> <input type="text" hidden="true" name="id" class="form-control"
								id="inputIdAtualizarPrazo">
						</div>
						<div class="col-md-8 mb-3">
							<label for="PrazoSolucao">Novo Prazo</label> <input type="date"
								name="prazoSolucao" class="form-control" id="PrazoSolucao"
								required>
							<div class="invalid-feedback">Informe um Novo Prazo.</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal" onclick="atualizarPagina();">Fechar</button>
							<button type="submit" class="btn btn-success">Atualizar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Atualizar Status -->

	<div class="modal fade " id="atualizarStatus" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Atualizar
						Status</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" onclick="atualizarPagina();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="needs-validation" novalidate
						action="<%=request.getContextPath()%>/atualizarStatus"
						method="post">
						<div class="col-md-7 mb-3">
							<label id="lblIdAtualizarStatus" for="inputIdAtualizarStatus">Chamado:
							</label> <input type="text" hidden="true" name="id" class="form-control"
								id="inputIdAtualizarStatus">
						</div>
						<div class="col-md-7 mb-3">
							<label for="Status">Selecione um novo Status</label> <select
								class="form-control" id="Status" name="status">
								<option value="ABERTO">ABERTO</option>
								<option value="EM ANDAMENTO">EM ANDAMENTO</option>
								<option value="FINALIZADO">FINALIZADO</option>
							</select>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal" onclick="atualizarPagina();">Fechar</button>
							<button type="submit" class="btn btn-success">Atualizar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Novo Lembrete -->

	<div class="modal fade " id="addLembrete" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Lembrete</h5>
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
							<div class="col-md-12 mb-3">
								<label>Ativo</label>
								<main>
								<div class="liga-desliga">
									<input type="checkbox" class="liga-desliga__checkbox"
										id="checkAtivo" name="ativo"> <label for="checkAtivo"
										class="liga-desliga__botao"></label>
								</div>
								</main>
							</div>
						</div>
						<div class=row>
							<div class="col-md-12 mb-3">
								<label for="descricaoLembrete">Descri��o</label>
								<textarea name="assunto" class="form-control"
									id="descricaoLembrete" placeholder="O que deseja lembrar?"
									rows="4" required></textarea>
								<div class="invalid-feedback">A descri��o � obrigat�ria.</div>
							</div>
						</div>
						<div class=row>
							<div class="col-md-5 mb-3">
								<label for="data">Data</label> <input type="date" name="data"
									class="form-control" id="data" required>
									<div class="invalid-feedback">Informe a data.</div>
							</div>
							<div class="col-md-3 mb-3">
								<label for="hora">Hora</label> <input type="time" name="hora"
									class="form-control" id="hora" required>
								<div class="invalid-feedback">Informe a hora.</div>
							</div>
							<div class="col-md-4 mb-3">
								<label for="tempoAntecipacao">Avisar Antes?</label>
								<select class="form-control" id="tempoAntecipacao" name="tempoAntecipacao">
									<option value="">Selecione..</option>
									<option value="">30 Minutos</options>
									<option value="">1 Hora</options>
									<option value="">3 Horas</options>
									<option value="">1 Dia</options>
									<option value="">1 Semana</options>
								</select>		
							</div>
						</div>
						<div class=row>
							<div class="col-md-2 mb-3">
								<label>Repetir</label>
								<main>
								<div class="liga-desliga">
									<input type="checkbox" class="liga-desliga__checkbox"
										name="repetir" id="checkRepetir"> <label
										for="checkRepetir" class="liga-desliga__botao"></label>
								</div>
								</main>
							</div>
							<div class="col-md-4 mb-3">
								<label>A partir da data?</label>
								<main>
								<div class="liga-desliga">
									<input disabled type="checkbox" class="liga-desliga__checkbox"
										id="checkAPartirData" name="repetirAPartirData"> <label
										for="checkAPartirData" class="liga-desliga__botao"></label>
								</div>
								</main>
							</div>
						</div>
						<div class=row>
							<div class="col-md-12 mb-3">
								<select disabled class="form-control" id="cbBoxRepetir"
									name="tipoRepetir" required>
									<option value="">Selecione aqui</option>
									<option value="d">Di�rio</option>
									<option value="m">Por M�s</option>
									<option value="s">Por Semana</option>
									<option value="n">Na Data</option>
								</select>
								<div class="invalid-feedback">Selecione como quer repetir.</div>
							</div>
						</div>
						<div class=row>
							<div align="center" class="col-md-2 mb-3">
								<label>Seg</label>
								<main>
								<div align="center" class="liga-desliga">
									<input disabled type="checkbox" class="liga-desliga__checkbox"
										id="checkSeg" name="seg"> <label for="checkSeg"
										class="liga-desliga__botao"></label>
								</div>
								</main>
							</div>
							<div align="center" class="col-md-2 mb-3">
								<label>Ter</label>
								<main>
								<div class="liga-desliga">
									<input disabled type="checkbox" class="liga-desliga__checkbox"
										id="checkTer" name="ter"> <label for="checkTer"
										class="liga-desliga__botao"></label>
								</div>
								</main>
							</div>
							<div align="center" class="col-md-2 mb-3">
								<label>Qua</label>
								<main>
								<div class="liga-desliga">
									<input disabled type="checkbox" class="liga-desliga__checkbox"
										id="checkQua" name="qua"> <label for="checkQua"
										class="liga-desliga__botao"></label>
								</div>
								</main>
							</div>
							<div align="center" class="col-md-2 mb-3">
								<label>Qui</label>
								<main>
								<div class="liga-desliga">
									<input disabled type="checkbox" class="liga-desliga__checkbox"
										id="checkQui" name="qui"> <label for="checkQui"
										class="liga-desliga__botao"></label>
								</div>
								</main>
							</div>
							<div align="center" class="col-md-2 mb-3">
								<label>Sex</label>
								<main>
								<div class="liga-desliga">
									<input disabled type="checkbox" class="liga-desliga__checkbox"
										id="checkSex" name="sex"> <label for="checkSex"
										class="liga-desliga__botao"></label>
								</div>
								</main>
							</div>
							<div align="center" class="col-md-2 mb-3">
								<label>Sab</label>
								<main>
								<div class="liga-desliga">
									<input disabled type="checkbox" class="liga-desliga__checkbox"
										id="checkSab" name="sab"> <label for="checkSab"
										class="liga-desliga__botao"></label>
								</div>
								</main>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal" onclick="atualizarPagina();">Fechar</button>
							<button type="submit" class="btn btn-success">Salvar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<footer class="footer mt-auto py-1">
	<div class="container-fluid">
		<span class="text-danger font-weight-bold">Certificado Digital
			Vence Em: ${certificadoValidade} dias.</span>
	</div>
	</footer>

	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/scriptSAC.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap-datetimepicker.min.js"></script>

</body>
</html>