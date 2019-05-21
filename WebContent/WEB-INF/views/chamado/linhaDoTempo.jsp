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
	href="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"
	rel="stylesheet">

<title>SacHelp - Chamados</title>
</head>
<body>
	<div class="container">
		<div class="fixed-top">
			<jsp:include page="/WEB-INF/views/navBarDarkCorporation.jsp"></jsp:include>
			<div class="p-3 bg-info text-white">
				<h4 class="mt-5">Linha do Tempo do Chamado: ${chamado.id} -
					${chamado.titulo}</h4>
			</div>
		</div>
		<br> <br> <br> <br> <br> <br>
		<div class="form-row">
			<input type="text" hidden="true" name="id" value="${chamado.id}">
			<input type="date" hidden="true" name="dataAbertura"
				value='<fmt:formatDate value="${chamado.dataAbertura.time}" pattern="yyyy-MM-dd"/>'>
			<input type="text" hidden="true" name="horaAbertura"
				value="${chamado.horaAbertura}"> <input type="text"
				hidden="true" name="horaFechamento"
				value="${chamado.horaFechamento}"> <input type="text"
				hidden="true" name="diferencaTempoDeEntrega"
				value="${chamado.diferencaTempoDeEntrega}">


			<div class="col-md-2 mb-3">
				<label for="dataAbertura" class="font-weight-bold">Data
					Abertura</label> <label class="form-control"> <fmt:formatDate
						value="${chamado.dataAbertura.time}" pattern="dd/MM/yyyy" />
				</label>
			</div>
			<div class="col-md-2 mb-3">
				<label for="horaAbertura" class="font-weight-bold">Hora
					Abertura</label> <label class="form-control">${chamado.horaAbertura}</label>
			</div>
			<div class="col-md-2 mb-3">
				<label for="DataFechamento" class="font-weight-bold">Data
					Fechamento</label> <input type="date" name="dataFechamento"
					class="form-control" id="DataFechamento"
					value='<fmt:formatDate value="${chamado.dataFechamento.time}" 
					pattern="yyyy-MM-dd"/>'>
			</div>
			<div class="col-md-2 mb-3">
				<label for="horaFechamento" class="font-weight-bold">Hora
					Fechamento</label> <label class="form-control">${chamado.horaFechamento}</label>
			</div>
			<div class="col-md-2 mb-3">
				<label for="protocolo" class="font-weight-bold">Protocolo</label> <label
					class="form-control">${chamado.protocolo}</label>
			</div>
			<div class="col-md-2 mb-3">
				<label class="font-weight-bold">Entrega x Prazo</label> <label
					class="form-control">${chamado.diferencaTempoDeEntrega}</label>
			</div>
			<div class="col-md-12 mb-3">
				<label for="Descricao" class="font-weight-bold">Descrição</label>
				<textarea name="descricao" class="form-control font-weight-bold"
					id="Descricao"
					placeholder="Digite uma descrição que identifique claramente o problema do Chamado"
					rows="6" required>${chamado.descricao}</textarea>
				<div class="invalid-feedback">A descrição é obrigatória.</div>
			</div>
		</div>

		<!-- <div style="overflow: auto; width: 840px; height: 500px;"> -->
		<table id="tbPendentes" class="table table-hover">
			<thead>
				<tr>
					<th scope="col">Data</th>
					<th scope="col">Hora</th>
					<th scope="col">Descrição</th>
					<th scope="col">Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${chamado.historicosChamado}"
					var="historicoChamado">
					<tr>
						<td><fmt:formatDate type="date"
								value="${historicoChamado.data.time}" pattern="dd/MM/yyyy" /></td>
						<td>${historicoChamado.hora}</td>
						<td>${historicoChamado.ocorrencia}</td>
						<td><a href="<%=request.getContextPath() %>/abrirEditarHistoricoChamado?id=${historicoChamado.id}"
							class="btn btn-light btn-xs" title="Editar"><span
								class="ui-icon ui-icon-pencil"></span></a>
							<a href="<%=request.getContextPath() %>/?id=${historicoChamado.id}"
							class="btn btn-light btn-xs" title="Excluir"><span
								class="ui-icon ui-icon-trash"></span></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	

	
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.min.js"></script>

</body>
</html>