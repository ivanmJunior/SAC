var TelaLembretes = {
	
	inputTipoRepetir: document.querySelector('#inputTipoRepetir'),
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
	
	getInputTipoRepetir: function(){
		return this.inputTipoRepetir;
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

$(document).ready(function() {
	if(TelaLembretes.getCheckRepetir().checked){
		if(TelaLembretes.getInputTipoRepetir().value != ""){
			$(TelaLembretes.getCombo()).prop("disabled", false);
			for(i = 1; i < TelaLembretes.getCombo().options.length; i++){
				if(TelaLembretes.getCombo().options[i].value == TelaLembretes.getInputTipoRepetir().value){
					TelaLembretes.getCombo().selectedIndex = i;
					break;
				}
			}
		}
	}
	
	if(TelaLembretes.getCheckRepetir().checked){
		
		$(TelaLembretes.getCheckAPartirData()).prop("disabled", false);
		if(TelaLembretes.getCheckAPartirData().checked){
			$(TelaLembretes.getInputData()).prop("disabled", false);
		}else{
			$(TelaLembretes.getInputData()).prop("disabled", true);
		}
		$(TelaLembretes.getCheckSeg()).prop("disabled", false);
		$(TelaLembretes.getCheckTer()).prop("disabled", false);
		$(TelaLembretes.getCheckQua()).prop("disabled", false);
		$(TelaLembretes.getCheckQui()).prop("disabled", false);
		$(TelaLembretes.getCheckSex()).prop("disabled", false);
		$(TelaLembretes.getCheckSab()).prop("disabled", false);
		
		if(TelaLembretes.getCheckSeg().checked || TelaLembretes.getCheckTer().checked ||
				TelaLembretes.getCheckQua().checked || TelaLembretes.getCheckQui().checked ||
				TelaLembretes.getCheckSex().checked || TelaLembretes.getCheckSab().checked){
			
			TelaLembretes.getCombo().selectedIndex = 0;
			$(TelaLembretes.getCombo()).prop("disabled", true);
		
		}
	}
		
});

//Valida formulários da página editarLembretes
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
	
});

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

//Adiciona uma cor verde na linha para cada lembrete que está ativo.

$('table tr').each(function() {
	
	var ativo;
	
	ativo = String($(this).find('td:nth-child(7)').html());
	
	if(ativo == "Sim"){
	   $(this).addClass("table-success");
	}
});

//Atualiza a página
function atualizarPagina(){
	window.location.reload();
}

