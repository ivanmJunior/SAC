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
<link
	href="<%=request.getContextPath()%>/resources/css/sticky-footer-navbar.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/botai_iOS.css"
	rel="stylesheet">

<title>SacHelp - Lembretes</title>
</head>
<body>

	<div class="container-fluid">
		<div class="fixed-top">
			<jsp:include page="/WEB-INF/views/navBarDarkCorporation.jsp"></jsp:include>
		</div>
		<br> <br>
	</div>

	<!--Tela Editar Lembrete -->
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Editar Lembrete</h5>
			</div>
			<div class="modal-body">
				<form class="needs-validation" novalidate
					action="<%=request.getContextPath()%>/editarLembrete" method="post">
					<div class=row>
						<div class="col-md-2 mb-3">
								<label>Ativo</label>
								<main>
								<div class="liga-desliga">
									<input type="checkbox" class="liga-desliga__checkbox"
										id="checkAtivo" name="ativo" value="true" ${lembrete.ativo? 'checked' : '' }>
										<label for="checkAtivo" class="liga-desliga__botao"></label>
								</div>
								</main>
							</div>
						<div class="col-md-2 mb-3">
							<label id="lblIdLembreteModalEditar" for="inputIdModalEditar">Id
							</label> <label class="form-control"> ${lembrete.id}</label> <input
								type="text" hidden="true" name="id" value="${lembrete.id}"
								class="form-control" id="inputIdModalEditar">
						</div>
						<div class="col-md-4 mb-3">
							<label id="lblIdLembreteModalEditar" for="inputIdModalEditar">Data
								Registro </label> <label class="form-control"> <fmt:formatDate
									value="${lembrete.dataRegistro.time}" pattern="dd/MM/yyyy" /></label>
							<input type="date" hidden="true" name="dataRegistro" class="form-control" id="dataRegistro"
								value='<fmt:formatDate value="${lembrete.dataRegistro.time}" 
							pattern="yyyy-MM-dd"/>'>
						</div>
						<div class="col-md-4 mb-3">
							<label id="lblIdLembreteModalEditar" for="inputIdModalEditar">Hora
								Registro </label> <label class="form-control">
								${lembrete.horaRegistro}</label> <input type="text" hidden="true"
								name="horaRegistro" value="${lembrete.horaRegistro}"
								class="form-control" id="inputDataModalEditar">
						</div>
					</div>
					<div class=row>
						<div class="col-md-12 mb-3">
							<label for="descricaoLembrete">Assusnto</label>
							<textarea name="assunto" class="form-control"
								id="descricaoLembrete" placeholder="O que deseja lembrar?"
								rows="4" required>${lembrete.assunto}</textarea>
							<div class="invalid-feedback">A descrição é obrigatória.</div>
						</div>
					</div>
					<div class=row>
						<div class="col-md-6 mb-3">
							<label for="data">Data</label> <input type="date" name="data"
								class="form-control" id="data"
								value='<fmt:formatDate value="${lembrete.data.time}" 
							pattern="yyyy-MM-dd"/>' required>
						</div>
						<div class="col-md-6 mb-3">
							<label for="hora">Hora</label> <input type="time" name="hora"
								value="${lembrete.hora}" class="form-control" id="hora" required>
							<div class="invalid-feedback">Informe a hora.</div>
						</div>
					</div>
					<div class=row>
						<div class="col-md-2 mb-3">
							<label>Repetir</label>
							<main>
							<div class="liga-desliga">
								<input type="checkbox" class="liga-desliga__checkbox"
									name="repetir" id="checkRepetir"
									value="true" ${lembrete.repetir? 'checked' : ''}>
									<label for="checkRepetir" class="liga-desliga__botao"></label>
							</div>
							</main>
						</div>
						<div class="col-md-4 mb-3">
							<label>A partir da data?</label>
							<main>
							<div class="liga-desliga">
								<input disabled type="checkbox" class="liga-desliga__checkbox"
									id="checkAPartirData" name="repetirAPartirData"
									value="true" ${lembrete.repetirAPartirData? 'checked' : ''}>
									<label for="checkAPartirData" class="liga-desliga__botao"></label>
							</div>
							</main>
						</div>
					</div>
					<div class=row>
						<div class="col-md-12 mb-3">
							<select disabled class="form-control" id="cbBoxRepetir"
								name="tipoRepetir">
								<option value="${lembrete.tipoRepetir}">Selecione aqui</option>
								<option value="d">Diário</option>
								<option value="m">Por Mês</option>
								<option value="s">Por Semana</option>
								<option value="n">Na Data</option>
							</select>
						</div>
					</div>
					<div class=row>
						<div class="col-md-2 mb-3">
							<label>Seg</label>
							<main>
							<div class="liga-desliga">
								<input disabled type="checkbox" class="liga-desliga__checkbox"
									id="checkSeg" name="seg" value="true"
									${lembrete.seg? 'checked' : ''}> <label for="checkSeg"
									class="liga-desliga__botao"></label>
							</div>
							</main>
						</div>
						<div class="col-md-2 mb-3">
							<label>Ter</label>
							<main>
							<div class="liga-desliga">
								<input disabled type="checkbox" class="liga-desliga__checkbox"
									id="checkTer" name="ter" value="true"
									${lembrete.ter? 'checked' : ''}> <label for="checkTer"
									class="liga-desliga__botao"></label>
							</div>
							</main>
						</div>
						<div class="col-md-2 mb-3">
							<label>Qua</label>
							<main>
							<div class="liga-desliga">
								<input disabled type="checkbox" class="liga-desliga__checkbox"
									id="checkQua" name="qua" value="true"
									${lembrete.qua? 'checked' : ''}> <label for="checkQua"
									class="liga-desliga__botao"></label>
							</div>
							</main>
						</div>
						<div class="col-md-2 mb-3">
							<label>Qui</label>
							<main>
							<div class="liga-desliga">
								<input disabled type="checkbox" class="liga-desliga__checkbox"
									id="checkQui" name="qui" value="true"
									${lembrete.qui? 'checked' : ''}> <label for="checkQui"
									class="liga-desliga__botao"></label>
							</div>
							</main>
						</div>
						<div class="col-md-2 mb-3">
							<label>Sex</label>
							<main>
							<div class="liga-desliga">
								<input disabled type="checkbox" class="liga-desliga__checkbox"
									id="checkSex" name="sex" value="true"
									${lembrete.sex? 'checked' : ''}> <label for="checkSex"
									class="liga-desliga__botao"></label>
							</div>
							</main>
						</div>
						<div class="col-md-2 mb-3">
							<label>Sab</label>
							<main>
							<div class="liga-desliga">
								<input disabled type="checkbox" class="liga-desliga__checkbox"
									id="checkSab" name="sab" value="true"
									${lembrete.sab? 'checked' : ''}> <label for="checkSab"
									class="liga-desliga__botao"></label>
							</div>
							</main>
						</div>
					</div>
					<div class="modal-footer">
						<a href="<%=request.getContextPath()%>/listarLembretes"
							class="btn btn-secondary my-2 my-sm-3">Cancelar</a>
						<button type="submit" class="btn btn-success">Confirmar</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!--Fim Tela -->

	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/lembretesScript.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/jquery-ui-1.12.1/jquery-ui.min.js"></script>

</body>
</html>