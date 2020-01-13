var TelaLembretes = {
	
	inputData: document.querySelector('#data'),
	checkAtivo: document.querySelector('#checkAtivo'),
	checkAPartirData: document.querySelector('#checkAPartirData'),
	checkRepetir: document.querySelector('#checkRepetir'),	
	combo: document.querySelector('#cbBoxRepetir'),
	checkSeg: document.querySelector('#checkSeg'),
	checkTer: document.querySelector('#checkTer'),
	checkQua: document.querySelector('#checkQua'),
	checkQui: document.querySelector('#checkQui'),
	checkSex: document.querySelector('#checkSex'),
	checkSab: document.querySelector('#checkSab'),
	
	setInputData: function(data){
		this.inputData.value = data;
	},
	
	getInputData: function(){
		return this.inputData;
	},
	
	getCheckAtivo: function(){
		return this.checkAtivo;
	},
	
	getCheckAPartirData: function(){
		return this.checkAPartirData;
	},
	
	getCheckRepetir: function(){
		return this.checkRepetir;
	},
	
	getCombo: function(){
		return this.combo;
	},

	getCheckSeg: function(){
		return this.checkSeg;
	},
	
	getCheckTer: function(){
		return this.checkTer;
	},
	
	getCheckQua: function(){
		return this.checkQua;
	},
	
	getCheckQui: function(){
		return this.checkQui;
	},
	
	getCheckSex: function(){
		return this.checkSex;
	},
	
	getCheckSab: function(){
		return this.checkSab;
	}
}
//Aplicada na tabela de chamados da página index
//Ao carregar a página verifica o estado do input checkAbas e define o que será exibido
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

//Valida formulários da página index
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
//Aplicado aos checkbox dias da semana
$("input").on('click', function(){
	
	if(TelaLembretes.getCheckRepetir().checked){
		if((this.checked) && (this.id == TelaLembretes.getCheckSeg().id || this.id == TelaLembretes.getCheckTer().id ||
				this.id == TelaLembretes.getCheckQua().id || this.id == TelaLembretes.getCheckQui().id ||
				this.id == TelaLembretes.getCheckSex().id || this.id == TelaLembretes.getCheckSab().id)){
			
			TelaLembretes.getCombo().selectedIndex = 0;
			$(TelaLembretes.getCombo()).prop("disabled", true);
		
		}else if(!TelaLembretes.getCheckSeg().checked && !TelaLembretes.getCheckTer().checked &&
				!TelaLembretes.getCheckQua().checked && !TelaLembretes.getCheckQui().checked &&
				!TelaLembretes.getCheckSex().checked && !TelaLembretes.getCheckSab().checked){
			
			$(TelaLembretes.getCombo()).prop("disabled", false);
		}
	}
})

$("#checkRepetir").on('click', function(){

	if(this.checked)
	  {
		TelaLembretes.setInputData("");
		$(TelaLembretes.getCombo()).prop("disabled", false);
		$(TelaLembretes.getInputData()).prop("disabled", true);
		$(TelaLembretes.getCheckAPartirData()).prop("disabled", false);
		$(TelaLembretes.getCheckSeg()).prop("disabled", false);
		$(TelaLembretes.getCheckTer()).prop("disabled", false);
		$(TelaLembretes.getCheckQua()).prop("disabled", false);
		$(TelaLembretes.getCheckQui()).prop("disabled", false);
		$(TelaLembretes.getCheckSex()).prop("disabled", false);
		$(TelaLembretes.getCheckSab()).prop("disabled", false);
	  
	  }else if(!this.checked){
	  	TelaLembretes.getCombo().selectedIndex = 0;
	  	$(TelaLembretes.getCombo()).prop("disabled", true);
	  	$(TelaLembretes.getInputData()).prop("disabled", false);
	  	$(TelaLembretes.getCheckAPartirData()).prop("disabled", true);
	  	$(TelaLembretes.getCheckSeg()).prop("disabled", true);
		$(TelaLembretes.getCheckTer()).prop("disabled", true);
		$(TelaLembretes.getCheckQua()).prop("disabled", true);
		$(TelaLembretes.getCheckQui()).prop("disabled", true);
		$(TelaLembretes.getCheckSex()).prop("disabled", true);
		$(TelaLembretes.getCheckSab()).prop("disabled", true);
		
		$(TelaLembretes.getCheckSeg()).prop("checked", false);
		$(TelaLembretes.getCheckTer()).prop("checked", false);
		$(TelaLembretes.getCheckQua()).prop("checked", false);
		$(TelaLembretes.getCheckQui()).prop("checked", false);
		$(TelaLembretes.getCheckSex()).prop("checked", false);
		$(TelaLembretes.getCheckSab()).prop("checked", false); 
		$(TelaLembretes.getCheckAPartirData()).prop("checked", false);
	  }	
});

//Aplicado no campo data verificando os checks CheckRepetir e CheckApartirData.
$("#checkAPartirData").on('click', function(){
	if(TelaLembretes.getCheckRepetir().checked && TelaLembretes.getCheckAPartirData().checked){
		$(TelaLembretes.getInputData()).prop("disabled", false);
	}else{
		$(TelaLembretes.getInputData()).prop("disabled", true);
		TelaLembretes.setInputData("");
	}
});

$("a").on('click', function(){
	if(document.querySelector('#inputId').value == "" && (this.id == "linkAddProgresso" ||
			this.id == "linkFinalizarChamado" || this.id == "btnLinhaDoTempo" ||
			this.id == "linkAtualizarPrazo" || this.id == "linkAtualizarStatus")){
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
	document.querySelector('#lblIdAtualizarStatus').innerText = "Chamado: "+idDoChamado;
	document.querySelector('#inputIdAtualizarStatus').value = idDoChamado;
	
    if(idAnterior == idDoChamado && $(this).hasClass("table-warning")) {//Verifica se é uma linha já clicada e com cor, se sim remove a cor
        $(this).removeClass("table-warning");
        document.querySelector('#lblIdChamado').innerText = "Chamado: "
    	document.querySelector('#inputId').value = '';
    	document.querySelector('#btnLinhaDoTempo').href = "#";
    	document.querySelector('#lblIdChamadoModalFinalizar').innerText = "Chamado: ";
    	document.querySelector('#inputIdModalFinalizar').value = '';
    	document.querySelector('#lblIdAtualizarPrazo').innerText = "Chamado: ";
    	document.querySelector('#inputIdAtualizarPrazo').value = '';
    	document.querySelector('#lblIdAtualizarStatus').innerText = "Chamado: ";
    	document.querySelector('#inputIdAtualizarStatus').value = '';
    	
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

//Adiciona uma cor vermelha para cada linha onde o chamado está em atraso.

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

