<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SacHelp - Editar Chamado</title>
</head>
<body>
	<div class="container">
		<div class="fixed-top">
		<jsp:include page="/WEB-INF/views/navBarDarkCorporation.jsp"></jsp:include>
			<div class="p-2 bg-info text-white">
				<h4 class="mt-5">Editar Chamado</h4>
			</div>
		</div>
		
		<h1 class="mt-5"><hr/></h1>
		<h1 class="mt-5"><hr/></h1>
		<h1 class="mt-5"><hr/></h1>

	<form id="formFuncionario" class="needs-validation" novalidate
			action="<%=request.getContextPath()%>/editarChamado" method="post">
		<div class="p-4 bg-white">
		<div class="form-row">
		<input type="text" hidden="true" name="id" value="${chamado.id}">
		<input type="date" hidden="true" name="dataAbertura"
			value='<fmt:formatDate value="${chamado.dataAbertura.time}" pattern="yyyy-MM-dd"/>'>
		<input type="text" hidden="true" name="horaAbertura" value="${chamado.horaAbertura}">
		<input type="text" hidden="true" name="horaFechamento" value="${chamado.horaFechamento}">
		<input type="text" hidden="true" name="diferencaTempoDeEntrega" value="${chamado.diferencaTempoDeEntrega}">	
			<div class="col-md-2 mb-3">
		      <label >Id Chamado</label>
		      <label class="form-control font-weight-bold">${chamado.id}</label>
		    </div>
			<div class="col-md-2 mb-3">
                  <label for="dataAbertura">Data Abertura</label>
                  <label class="form-control">
                   		<fmt:formatDate value="${chamado.dataAbertura.time}" pattern="dd/MM/yyyy"/>
                  </label>
		    </div>
		    <div class="col-md-2 mb-3">
                  <label for="horaAbertura">Hora Abertura</label>
                  <label class="form-control">${chamado.horaAbertura}</label>
		    </div>
		    <div class="col-md-2 mb-3">
				  <label for="DataFechamento">Data Fechamento</label>
				  <input type="date" name="dataFechamento" class="form-control" id="DataFechamento"
					value='<fmt:formatDate value="${chamado.dataFechamento.time}" 
					pattern="yyyy-MM-dd"/>'>
			</div>
			 <div class="col-md-2 mb-3">
                  <label for="horaFechamento">Hora Fechamento</label>
                  <label class="form-control">${chamado.horaFechamento}</label>
		    </div>
			<div class="col-md-2 mb-3">
		      <label >Entrega x Prazo</label>
		      <label class="form-control">${chamado.diferencaTempoDeEntrega}</label>
		    </div>
		</div>
		</div>    
			<div class="p-4 bg-light rounded">
				<div class="form-row">
					
					<div class="col-md-4 mb-3">
						<label for="validationLoja">Loja</label>
						<input type="text"	name="loja" class="form-control" value="${chamado.loja}"
							id="validationLoja" placeholder="Nome da Loja Solicitante" required>
						<div class="invalid-feedback">Informe a loja.</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="validationContato">Contato</label>
						<input type="text" name="contato" class="form-control" value="${chamado.contato}"
							id="validationContato" placeholder="Seu Nome" required>
						<div class="invalid-feedback">Informe seu nome de contato.</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="Protocolo">Protocolo do Atendimento</label>
						<input type="text" name="protocolo" class="form-control" value="${chamado.protocolo}"
							id="Protocolo" placeholder="Nº Protocolo gerado pelo setor">
					</div>
					<div class="col-md-4 mb-3">
						<label for="TecResponsavel">Téc. Responsavel</label>
						<input type="text" name="tecResponsavel" class="form-control" value="${chamado.tecResponsavel}"
							id="TecResponsavel" placeholder="Responsavel pelo atendimento solicitado" required>
						<div class="invalid-feedback">Informe o nome do Técnico.</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="Setor/Empresa">Setor/Empresa</label>
						<select	class="form-control" id="Setor/Empresa"
							name="setorResponsavel" required>
							<option value="${chamado.setorResponsavel}" selected="selected">${chamado.setorResponsavel}</option>
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
							<option value="${chamado.status}" selected="selected">${chamado.status}</option>
							<option value="ABERTO">ABERTO</option>
							<option value="EM ANDAMENTO">EM ANDAMENTO</option>
							<option value="FINALIZADO">FINALIZADO</option>
						</select>
					</div>
					<div class="col-md-4 mb-3">
						<label for="Titulo">Título</label>
						<input type="text" name="titulo" class="form-control" id="Titulo"
							placeholder="Assunto do Chamado" value="${chamado.titulo}" required>
							<div class="invalid-feedback">Informe um Título.</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="TipoAtendimento">Tipo Atendimento</label>
						<select	class="form-control" id="TipoAtendimento"
							name="tipoAtendimento" required>
							<option value="${chamado.tipoAtendimento}" selected="selected">${chamado.tipoAtendimento}</option>
							<option value="Hardware">Hardware</option>
							<option value="Sistema BM">Sistema BM</option>
							<option value="SO">SO</option>
							<option value="Software Instalacao/config">Software Instalação/config</option>
							<option value="Planilhas">Planilhas</option>
							<option value="Consultas SQL">Consultas SQL</option>
							<option value="Arquivos Fiscais">Arquivos Fiscais</option>
							<option value="Rede">Rede</option>
							<option value="Infraestrutura">Infraestrutura</option>
							<option value="Treinamento">Treinamento</option>
						</select>
						<div class="invalid-feedback">Selecione um tipo de atendimento.</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="PrazoSolucao">Prazo Solução</label>
						<input type="date" name="prazoSolucao" class="form-control" id="PrazoSolucao"
							value='<fmt:formatDate value="${chamado.prazoSolucao.time}" 
							pattern="yyyy-MM-dd"/>' required>
							<div class="invalid-feedback">Informe uma de Prazo Inicial.</div>
					</div>
					<div class="col-md-12 mb-3">
						<label for="Descricao">Descrição</label>
						<textarea name="descricao" class="form-control"	id="Descricao"
							placeholder="Digite uma descrição que identifique claramente o problema do Chamado"
							rows="6" required>${chamado.descricao}</textarea>
							<div class="invalid-feedback">A descrição é obrigatória.</div>
					</div>
					<div class="col-md-12 mb-3">
						<label for="Solucao">Solução</label>
						<textarea name="solucao" class="form-control text-success font-weight-bold"	id="Solucao"
							placeholder="Informe a Solução do Chamado"
							rows="6">${chamado.solucao}</textarea>
					</div>
					<div class="col-md-12 mb-3">
						<label for="OBS">Observação</label>
						<textarea name="observacao" class="form-control text-danger font-weight-bold" id="OBS"  
						placeholder="Observações" rows="2">${chamado.observacao}</textarea>
					</div>
				</div>
				<button class="btn btn-primary my-sm-3" type="submit">Salvar Alteração</button>
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