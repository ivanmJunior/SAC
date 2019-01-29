package br.com.sacHelp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Teste {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		//System.out.println(sdf.format(new Date()));
		
		Calendar hora = new GregorianCalendar();
		hora.setTime(new Date());
		System.out.println(sdf.format(new Date()));

	}

}
