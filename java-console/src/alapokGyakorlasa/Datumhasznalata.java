package alapokGyakorlasa;

import java.util.Calendar;
import java.util.Date;

public class Datumhasznalata {

	public static void datum() {
		
		//Új megoldás:
		Date most = new Date();

		System.out.println(most); // output: Mon Feb 09 09:36:37 CET 2026

		// Lekérdezés millisec-ben:
		long ido = most.getTime();
		System.out.println(ido); // output: 1770626244569

		// Régi megoldás:
		Calendar cal = Calendar.getInstance();

		int ev = cal.get(Calendar.YEAR);
		int honap = cal.get(Calendar.MONTH) + 1;
		int nap = cal.get(Calendar.DAY_OF_MONTH);

		System.out.println(ev + "." + honap + "." + nap); //output: 2026.2.9
		System.out.println("Óra: "+cal.get(Calendar.HOUR_OF_DAY)); //output: Óra: 9
		System.out.println("Perc: "+cal.get(Calendar.MINUTE)); //output: Perc: 39

	}

}
