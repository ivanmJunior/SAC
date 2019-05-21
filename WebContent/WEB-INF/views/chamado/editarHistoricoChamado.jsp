<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SacHelp - Editar Hist�rico Chamado</title>
</head>
<body>
	<div class="container">
		<div class="fixed-top">
		<jsp:include page="/WEB-INF/views/navBarDarkCorporation.jsp"></jsp:include>
			<div class="p-2 bg-info text-white">
				<h4 class="mt-5">Editar Hist�rico Chamado</h4>
			</div>
		</div>
		
		<h1 class="mt-5"><hr/></h1>
		<h1 class="mt-5"><hr/></h1>
		<h1 class="mt-5"><hr/></h1>

	<form id="formFuncionario" class="needs-validation" novalidate
			action="<%=request.getContextPath()%>/editarHistorico" method="post">
		<div class="p-4 bg-white">
		<div class="form-row">
			<div class="col-md-4 mb-3">
				<label >Id Chamado</label>
				<label class="form-control font-weight-bold">${historicoChamado.chamado.id}</label>
			</div>
		</div>
		<div class="form-row">
		<input type="text" hidden="true" name="id" value="${historicoChamado.id}">
		<input type="text" hidden="true" name="chamado.id" value="${historicoChamado.chamado.id}">
			
			<div class="col-md-4 mb-3">
			  <label >Id Historico</label>
		      <label class="form-control font-weight-bold">${historicoChamado.id}</label>
		       
		    </div>
			
		   	<div class="col-md-4 mb-3">
				<label for="DataOcorrencia">Data Ocorr�ncia</label>
				<input type="date" name="data" class="form-control" id="DataOcorrencia"
					value='<fmt:formatDate value="${historicoChamado.data.time}" 
					pattern="yyyy-MM-dd"/>' required>
				<div class="invalid-feedback">Informe uma data.</div>
			</div>
			<div class="col-md-4 mb-3">
                  <label for="hora">Hora</label>
                  <input type="text" class="form-control" name="hora" value="${historicoChamado.hora}">
		    </div>
					<div class="col-md-12 mb-3">
						<label for="ocorrencia">Descri��o Ocorr�ncia</label>
						<textarea name="ocorrencia" class="form-control" id="ocorrencia"
							rows="6" required>${historicoChamado.ocorrencia}</textarea>
							<div class="invalid-feedback">A descri��o � obrigat�ria.</div>
					</div>
					
				</div>
				<button class="btn btn-primary my-sm-3" type="submit">Salvar Altera��o</button>
				<a href="<%=request.getContextPath()%>/index"
					class="btn btn-secondary my-sm-3">P�gina Inicial</a>
			</div>
		</form>
	</div>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<script	src="<%=request.getContextPath()%>/resources/js/validaFormClienteUser.js"></script>
</body>
</html>