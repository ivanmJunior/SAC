(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Busque todos os formulários que queremos aplicar estilos de validação Bootstrap personalizados para
    var forms = document.getElementsByClassName('needs-validation');
    // Faça um loop sobre eles e impeça a submissão
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
	
}


$("tr").on('click', function () {
    if($(this).hasClass("table-warning")) {
        $(this).removeClass("table-warning");
    } else {
        $(this).addClass("table-warning");
    }
});

var dataPrazo;
var dataPrazoFinal;
var dataAtual;

$('table tr').each(function() {
	
	dataPrazo = String($(this).find('td:nth-child(6)').html());
	dataPrazoFinal = new Date(dataPrazo.split('/').reverse().join('/'));
	dataAtual = new Date();
	
		   
	if(dataPrazoFinal < dataAtual){
	   $(this).addClass("table-danger");
	   
	}
	
});