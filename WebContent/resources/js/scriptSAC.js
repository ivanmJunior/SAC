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


function pegarIdChamado(linhaClicada){
	
	colunas = linhaClicada.getElementsByTagName('td');
	idDoChamado = colunas[0].innerText;
	document.querySelector('#lblIdChamado').innerText = "Chamado: "+idDoChamado;
	document.querySelector('#inputId').value = idDoChamado;
	document.querySelector('#btnLinhaDoTempo').href = "http://localhost:8080/SAC/abrirLinhaDoTempo?id="+idDoChamado;
	document.querySelector('#lblIdChamadoModalFinalizar').innerText = "Chamado: "+idDoChamado;
	document.querySelector('#inputIdModalFinalizar').value = idDoChamado;
	document.querySelector('#lblIdAtualizarPrazo').innerText = "Chamado: "+idDoChamado;
	document.querySelector('#inputIdAtualizarPrazo').value = idDoChamado;
	
}

//Função Adiciona cor na linha clicad

var idAnterior = 0;

$("tr").on('click', function () {
	
	colunas = this.getElementsByTagName('td'); //Pega as colunas da linha clicada
	idDoChamado = colunas[0].innerText; //Pega a informção da primeira coluna da linha
	
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


//

var dataPrazo;
var dataPrazoFinal;
var dataAtual;
var dataAtualFinal;

$('table tr').each(function() {
	
	dataPrazo = String($(this).find('td:nth-child(6)').html());
	dataPrazoFinal = new Date(dataPrazo.split('/').reverse().join('/'));
	
	dataAtual = new Date();
	dataEmTexto = dataAtual.toLocaleDateString();
	dataAtualFinal = new Date(dataEmTexto.split('/').reverse().join('/'));
	
	if(dataPrazoFinal < dataAtualFinal){
	   $(this).addClass("table-danger");
	}
	
});

function atualizarPagina(){
	window.location.reload();
}