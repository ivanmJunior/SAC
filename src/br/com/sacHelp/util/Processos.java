package br.com.sacHelp.util;

import java.time.LocalDateTime;
import java.util.TimerTask;


public class Processos extends TimerTask{


	@Override
	public void run() {
		System.out.println("veificando: ..."+ LocalDateTime.now());
		
	}
}
