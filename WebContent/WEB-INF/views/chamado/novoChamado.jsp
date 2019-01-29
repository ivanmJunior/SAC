<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SacHelp - Novo Chamado</title>
</head>
<body>
	<div class="container">
		<div class="fixed-top">
			<div class="p-2 mb-3 bg-secondary text-white">
				<h1 class="mt-5">Novo Chamados</h1>
			</div>
		</div>
		
		<h1 class="mt-5"><hr/></h1>
		<h1 class="mt-5"><hr/></h1>
		<h1 class="mt-5"><hr/></h1>

	<form id="formFuncionario" class="needs-validation" novalidate
			action="<%=request.getContextPath()%>/adicionarChamado"
			method="post">
			<div class="p-4 bg-light rounded">
				<div class="form-row">
					
					<div class="col-md-4 mb-3">
						<label for="validationLoja">Loja</label>
						<input type="text"	name="loja" class="form-control"
							id="validationLoja" placeholder="Nome da Loja Solicitante" required>
						<div class="invalid-feedback">Informe a loja.</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="validationContato">Contato</label>
						<input type="text" name="contato" class="form-control"
							id="validationContato" placeholder="Seu Nome" required>
						<div class="invalid-feedback">Informe seu nome de contato.</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="Protocolo">Protocolo do Atendimento</label>
						<input type="text" name="contato" class="form-control"
							id="Protocolo" placeholder="Nº Protocolo gerado pelo setor">
					</div>
					<div class="col-md-4 mb-3">
						<label for="TecResponsavel">Téc. Responsavel</label>
						<input type="text" name="tecResponsavel" class="form-control"
							id="TecResponsavel" placeholder="Responsavel pelo atendimento solicitado" required>
						<div class="invalid-feedback">Informe o nome do Técnico.</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="Setor/Empresa">Setor/Empresa</label>
						<select	class="form-control" id="Setor/Empresa"
							name="setorResponsavel" required>
							<option value="" selected="selected">Selecione o Setor</option>
							<option value="BM">BM Informática</option>
							<option value="TI Interno">TI Interno</option>
							<option value="Vivo">Vivo</option>
							<option value="Oi">Oi</option>
							<option value="Téc. Monitoramento">Téc. Monitoramento</option>
						</select>
						<div class="invalid-feedback">Selecione um setor responsável.</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="Status">Status do Chamado</label>
						<select	class="form-control" id="Status" name="status">
							<option value="ABERTO" selected="selected">ABERTO</option>
							<option value="EM ANDAMENTO">EM ANDAMENTO</option>
							<option value="FINALIZADO">FINALIZADO</option>
						</select>
					</div>
					<div class="col-md-8 mb-3">
						<label for="Titulo">Título</label>
						<input type="text" name="titulo" class="form-control" id="Titulo"
							placeholder="Assunto do Chamado" required>
							<div class="invalid-feedback">Informe um Título.</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="PrazoSolucao">Prazo Solução</label>
						<input type="date" name="prazoSolucao" class="form-control" id="PrazoSolucao" required>
							<div class="invalid-feedback">Informe uma de Prazo Inicial.</div>
					</div>
					<div class="col-md-12 mb-3">
						<label for="Descricao">Descrição</label>
						<textarea name="descricao" class="form-control"	id="Descricao"
							placeholder="Digite uma descrição que identifique claramente o problema do Chamado"
							rows="6" required></textarea>
							<div class="invalid-feedback">A descrição é obrigatória.</div>
					</div>
					<div class="col-md-12 mb-3">
						<label for="OBS">Observação</label>
						<textarea name="observacao" class="form-control" id="OBS"
						placeholder="Observações" rows="2"></textarea>
					</div>
				</div>
				<button class="btn btn-primary my-sm-3" type="submit">Salvar Chamado</button>
				<a href="<%=request.getContextPath()%>/index"
					class="btn btn-secondary my-sm-3">Página Inicial</a>
			</div>
		</form>
	</div>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<script	src="<%=request.getContextPath()%>/resources/js/validaFormClienteUser.js"></script>
</body>
</html>