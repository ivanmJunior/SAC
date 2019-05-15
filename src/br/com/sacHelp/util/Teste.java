package br.com.sacHelp.util;

import java.util.Timer;
import java.util.TimerTask;

public class Teste {

	public static void main(String[] args) {

		/*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(sdf.format(new Date()));
		
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		//hora.setTime(new Date());
		DateFormat formatarData = DateFormat.getDateInstance();
		System.out.println(formatarData.format(data));*/
		
	
				final long time = 1000; // a cada X ms
				Timer timer = new Timer();
				TimerTask tarefa = new TimerTask() {
					public void run() {
		                               //método
						try {
							System.out.println("teste");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				timer.scheduleAtFixedRate(tarefa, time, time);
			}
}
