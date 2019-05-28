<header>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top bg-secondary">
		<a title="Página Inicial" class="navbar-brand" href="index">SacHelp</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">Chamado</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item"
						href="<%=request.getContextPath()%>/novoChamado">Novo</a>
					<a class="dropdown-item"
						href="<%=request.getContextPath()%>/listarChamados">Listar</a>
						<a class="dropdown-item"
						href="#">Linha do Tempo</a>
						<a class="dropdown-item"
						href="#">Adicionar Progresso</a>
						<a class="dropdown-item"
						href="#">Finalizar</a>
						<a class="dropdown-item"
						href="#">Novo Prazo</a>
						<a class="dropdown-item"
						href="#">Excluir</a>
				</div>
			</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Conficurações </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="<%=request.getContextPath()%>/listarUsuarios">Usuários</a>
					<a	class="dropdown-item"
						href="<%=request.getContextPath()%>/listarNiveisAcesso">Nives de Usuários</a>
				</div>
				</li>
				<li class="nav-item"><a class="nav-link " href="#">Ajuda</a></li>
				
				<li class="nav-item active dropdown">
					<a class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Usuario: ${usuarioLogado.nomeExibicao} </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="<%=request.getContextPath()%>/logoutSistema">Sair</a>
					</div>
				</li>
			</ul>

		</div>
	</nav>
</header>