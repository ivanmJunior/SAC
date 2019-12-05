$(document).ready(function() {
	
	if(document.querySelector('#checkAbas').checked){
		  $(document.querySelector('#perfil')).prop("hidden", true);
		  $(document.querySelector('#home')).prop("hidden", false);
		  $('#profile-tab').addClass('text-black-50');
		  $('#home-tab').removeClass('text-black-50');
	
	}else if(!document.querySelector('#checkAbas').checked){
		  $(document.querySelector('#home')).prop("hidden", true);
		  $(document.querySelector('#perfil')).prop("hidden", false);
		  $('#home-tab').addClass('text-black-50');
		  $('#profile-tab').removeClass('text-black-50');
	}
});

(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Busque todos os formulÃ¡rios que queremos aplicar estilos de validaÃ§Ã£o Bootstrap personalizados para
	  var forms = document.getElementsByClassName('needs-validation');
    // FaÃ§a um loop sobre eles e impeÃ§a a submissÃ£o
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();

$("a").on('click', function(){
	if(document.querySelector('#inputId').value == "" && (this.id == "linkAddProgresso" ||
			this.id == "linkFinalizarChamado" || this.id == "btnLinhaDoTempo" || this.id == "linkAtualizarPrazo")){
		alert("Selecione um Chamado na lista ao lado.");
		return false;
	}
});

//Função Adiciona cor na linha clicada
var idAnterior = 0;

$("tr").on('click', function () {
	
	colunas = this.getElementsByTagName('td'); //Pega as colunas da linha clicada
	idDoChamado = colunas[0].innerText; //Pega a informção da primeira coluna da linha
	
	document.querySelector('#lblIdChamado').innerText = "Chamado: "+idDoChamado;
	document.querySelector('#inputId').value = idDoChamado;
	document.querySelector('#btnLinhaDoTempo').href = "http://localhost:8080/SAC/abrirLinhaDoTempo?id="+idDoChamado;
	document.querySelector('#lblIdChamadoModalFinalizar').innerText = "Chamado: "+idDoChamado;
	document.querySelector('#inputIdModalFinalizar').value = idDoChamado;
	document.querySelector('#lblIdAtualizarPrazo').innerText = "Chamado: "+idDoChamado;
	document.querySelector('#inputIdAtualizarPrazo').value = idDoChamado;
	
    if(idAnterior == idDoChamado && $(this).hasClass("table-warning")) {//Verifica se é uma linha já clicada e com cor, se sim remove a cor
        $(this).removeClass("table-warning");
        document.querySelector('#lblIdChamado').innerText = "Chamado: "
    	document.querySelector('#inputId').value = '';
    	document.querySelector('#btnLinhaDoTempo').href = "#";
    	document.querySelector('#lblIdChamadoModalFinalizar').innerText = "Chamado: ";
    	document.querySelector('#inputIdModalFinalizar').value = '';
    	document.querySelector('#lblIdAtualizarPrazo').innerText = "Chamado: ";
    	document.querySelector('#inputIdAtualizarPrazo').value = '';
     
    	//verifica se uma linha anterior foi clicada e tem cor, se sim remove a cor e adiciona na nova linha   
    } else if(idAnterior != 0 && $('#linhaTbPendentes'+idAnterior).hasClass("table-warning")){
    	$('#linhaTbPendentes'+idAnterior).removeClass("table-warning");
    	$(this).addClass("table-warning");
        idAnterior = idDoChamado;
        
    }else{//Adiciona cor se nenhuma linha estiver com cor
    	$(this).addClass("table-warning");
        idAnterior = idDoChamado;
    	
    }
});

//Adiciona uma cor de nas linhas para cada linha onde o chamado está em atraso.

$('table tr').each(function() {
	
	var dataPrazo;
	var dataPrazoFinal;
	var dataAtual;
	var dataAtualFinal;
	
	dataPrazo = String($(this).find('td:nth-child(6)').html());
	dataPrazoFinal = new Date(dataPrazo.split('/').reverse().join('/'));
	
	dataAtual = new Date();
	dataEmTexto = dataAtual.toLocaleDateString();
	dataAtualFinal = new Date(dataEmTexto.split('/').reverse().join('/'));
	
	if(dataPrazoFinal < dataAtualFinal){
	   $(this).addClass("table-danger");
	}
	
});

//Atualiza a página
function atualizarPagina(){
	window.location.reload();
}

//Valida o novo prazo informado
$('#PrazoSolucao').on('blur', function () {
	novoPrazo = document.querySelector('#PrazoSolucao').value;
	arrayNovoPrazo = novoPrazo.split('-');
	novoPrazoFormatado = arrayNovoPrazo[2] +'/'+ arrayNovoPrazo[1] +'/'+ arrayNovoPrazo[0]; 
	dataPrazoFinal = new Date(novoPrazoFormatado.split('/').reverse().join('/'));
	
	dataHoje = new Date();
	dataEmTextoHoje = dataHoje.toLocaleDateString();
	dataHojeFinal = new Date(dataEmTextoHoje.split('/').reverse().join('/'));
	
	diferenca = dataPrazoFinal.getFullYear() - dataHojeFinal.getFullYear();
		
	if(dataPrazoFinal < dataHojeFinal){
		alert('Novo prazo nao pode ser menor que a data atual.');
		return false;
	}else if(diferenca > 1){
		alert('Prazo muito longo.');
		return false;
	}else if(dataPrazoFinal == 'Invalid Date'){
		alert('Informe uma data Valida.');
		return false;
	}
});

$(function() {
    $('#datetimepicker3').datetimepicker({
      pickDate: false
    });
  });

//Adiciona e remove a cor do texto das abas na index
$('.nav-link').on('click', function(){
	if(this.id == "home-tab"){
		window.location.href="http://localhost:8080/SAC/index";
		$('#home-tab').addClass('text-black-50');
		 
	}
	if(this.id == "profile-tab"){
		window.location.href="http://localhost:8080/SAC/indexTI";
		$('#profile-tab').addClass('text-black-50');
	}
});

