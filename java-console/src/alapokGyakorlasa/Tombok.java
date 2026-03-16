package alapokGyakorlasa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tombok {

	public static void tombok() throws NumberFormatException, IOException {
		/*
		 * Írj egy Java programot, amely: Bekéri 5 dolgozat pontszámát (0–100 között).
		 * Az adatokat tömbben tárolja. * Kiírja a pontszámokat egymás mellé, szóközzel
		 * elválasztva. Kiszámítja és kiírja: az átlagpontszámot (2 tizedesre kerekítve)
		 * a legnagyobb pontszámot a legkisebb pontszámot
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] dolgozatPontszama = new int[5];

		int szum = 0;
		int legnagyobbPontszam = 0;
		int legkisebbPontszam = 0;
		for (int i = 0; i < dolgozatPontszama.length; i++) {
			System.out.println(i + 1 + " dolgozat pontszáma (0-100): ");
			dolgozatPontszama[i] = Integer.parseInt(br.readLine());
			szum += dolgozatPontszama[i];

			if (i == 0) {
				legnagyobbPontszam = dolgozatPontszama[i];
				legkisebbPontszam = dolgozatPontszama[i];
			} else {
				// Ternális if:
				legnagyobbPontszam = (legnagyobbPontszam > dolgozatPontszama[i]) ? legnagyobbPontszam
						: dolgozatPontszama[i];

				legkisebbPontszam = (legkisebbPontszam < dolgozatPontszama[i]) ? legkisebbPontszam
						: dolgozatPontszama[i];
			}
		}

		System.out.println("Dolgozatok pontszámai: ");

		// For-each
		for (int i : dolgozatPontszama) {
			System.out.print(i + " ");

		}
		System.out.printf("%nÁtlag %.2f%n", ((double) szum / dolgozatPontszama.length));
		System.out.println("Legnagyobb pontszam: " + legnagyobbPontszam);
		System.out.println("Legkisebb pontszam: " + legkisebbPontszam);

	}

}
