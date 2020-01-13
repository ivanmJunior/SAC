package br.com.sacHelp.model.service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sacHelp.model.entity.Lembrete;
import br.com.sacHelp.model.percistence.interfaces.IRepositorioLembrete;
import br.com.sacHelp.util.TipoRepeticao;

@Service
public class LembreteService {

	@Autowired
	IRepositorioLembrete repLembrete;
	
	public void adicionar(Lembrete lembrete) throws SQLException{
		if(lembrete.isRepetir()) {
			lembrete.setAno(lembrete.getDataRegistro().get(Calendar.YEAR));
			lembrete.setMes(lembrete.getDataRegistro().get(Calendar.MONTH) + 1);
		}
		
		if(lembrete.getTipoRepetir() != TipoRepeticao.NA_DATA) {
			if(lembrete.getTipoRepetir() != TipoRepeticao.DIARIO) {
				lembrete.setDia(diaRepeticao(lembrete));
			}else {
				lembrete.setDiaEmHoras(diaRepeticao(lembrete));
			}
		}
		System.out.println(lembrete);
		//repLembrete.adicionar(lembrete);
		
	}
	
	public List<Lembrete> consultar()throws SQLException{
		return repLembrete.consultar();
	}
	
	/**O método diaRepeticao(Lembrete lembrete) serve para selecionar como o dia será
	 * preenhido de acordo com o tipo de repetição selecionada pelo usuário.
	 * Pode ser o dia da semana sendo de 1 a 7, dia do mês de 1 a 31
	 * ou um dia em horas, no caso 24h. Retornando um valor inteiro.*/
	public int diaRepeticao(Lembrete lembrete) {
		
		if (lembrete.getTipoRepetir() == TipoRepeticao.MENSAL) {
			return lembrete.getDataRegistro().get(Calendar.DAY_OF_MONTH); //Retorna o dia do mês da data atual. De 1 a 31.
		} else if (lembrete.getTipoRepetir() == TipoRepeticao.SEMANAL) {
			return lembrete.getDataRegistro().get(Calendar.DAY_OF_WEEK); //Retorna o dia da semana da data atual. De 1 a 7.
		} else if (lembrete.getTipoRepetir() == TipoRepeticao.DIARIO) {
			return 24; //Retorna 24 no dia. Esse valor representa 24h. Poede ser prosório até criar outra solução
		}else {
	
			return 0;
		}
	}
	
	public Lembrete consultarPorId(int id) throws SQLException {
		return repLembrete.consultarPorId(id);
	}
	
	public void editar(Lembrete lembreteAlterado) throws SQLException {
		repLembrete.editar(lembreteAlterado);
	}
}
