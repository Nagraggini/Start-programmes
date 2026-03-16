package alapokGyakorlasa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;



public class AdatBekeresekSzamEsSzoveg {

	static String[] keresztnevekTomb = new String[10];

	public static void main(String[] args) {
		
		System.out.println("abc");
	}

	public static void EgyEsTizKozottiSzamBekerese() {
		Scanner sc = new Scanner(System.in);
		int number;

		do {
			System.out.println("Adj meg egy számot, ami 1 és 10 között van.");

			if (!sc.hasNextInt()) {
				System.out.println("Hibás érték, próbáld újra!");
				sc.next(); // hibás input eldobása
				number = 0; // a while feltétel miatt
				continue; // Ez azért kell hogy két rontás után, ne álljon le a program.
			}

			// Megadjuk a változó értékét, inicializáljuk.
			number = sc.nextInt();

			if (number < 1 || number > 10) {// Hibás érték.
				System.out.println("Túl nagy vagy túl kicsi az érték. \n Újra!");
			}

		} while (number < 1 || number > 10);

		System.out.println("Jó a szám, 1 és 10 között van!");
		sc.close();
	}

	/** Do-while és if gyarkolása. */
	public static void ParosSzamBekerese() {
		Scanner sc = new Scanner(System.in);
		int number;

		do {

			System.out.println("Adj meg egy páros számot:");
			number = sc.nextInt();
			if (number % 2 == 0) {
				System.out.println("Páros a szám.");
			}

		} while (number % 2 != 0); // Páros a szám.
		sc.close();
	}

	public static void NevekBekerese() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int i = 0;
		String keresztnev = "0";

		while (i < keresztnevekTomb.length) {
			do { // do-while kell ahhoz, hogy addig kérjük be a szöveget,
					// amíg nem nulla, vagy szám.
				System.out.println(i + 1 + ". keresztnév bekérése: ");
				keresztnev = br.readLine().toUpperCase();

				if (keresztnev.isEmpty() || !nevEllenorzese(keresztnev)) {// Ha üres, vagy ha hamis.
					break;
				}

				keresztnevekTomb[i] = keresztnev.toUpperCase();

				i++;
			} while (keresztnev.isEmpty() || !nevEllenorzese(keresztnev));
		}

	}

	/** Lecsekkoljuk, hogy nincs-e benne szám stb. */
	private static boolean nevEllenorzese(String keresztnev) {

		// Szám, írásjel, szóköz nem számít karakternek.
		for (char karakter : keresztnev.toCharArray()) {// toCharArray átkonvertálja a string-et egy char-ra.
			// A szóközt, így nem veszi hibás inputnak.
			if (!Character.isLetter(karakter) && karakter != ' ') {
				return false; // Nincsen benne betű.
			}
		}
		return true;
	}
}
