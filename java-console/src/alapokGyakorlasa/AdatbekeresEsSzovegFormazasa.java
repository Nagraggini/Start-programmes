package alapokGyakorlasa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AdatbekeresEsSzovegFormazasa {

	
	public static void Formazokarakterek() {
		int a = 5;
		double b = 2.345;
		String s = "Java";

		System.out.printf("a=%d %n", a); // %n -> Új sor.
		System.out.printf("b=%.1f %n", b); // lebegőpontos szám (float, double), 1 tizedesjegyig.
		System.out.printf("s=%s %n", s);
		// Output:
		// a=5
		// b=2,3
		// s=Java
		System.out.printf("%%"); // maga a % jel kiírása

	}

	public static void Vezerlokarakterek() {
		System.out.println("Ez a tabulátor -->\t<---");
		System.out.println("Sortörés-->\n<--27");
		System.out.println("Backspace -->\b<--");
	}

	public static void KorKeruletEsTeruletSzamitasa() throws NumberFormatException, IOException {
		// Mindig kivételkezelésre kényszerít.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		final double PI = 3.1416;
		System.out.println("Kérem adja meg a kör sugarát: ");
		double sugar = Double.parseDouble(br.readLine()); // katt a br-re -> add throws declaration

		System.out.println("A kör területe: " + PI * sugar * sugar);
		System.out.println("A kör területe: " + PI * sugar * 2);

		// Ha csak két tizedes hosszan szeretnénk kiíratni az eredményt, akkor formázni
		// kell a kimenetet.
		// printf és a format ugyanaz. Tizedes veszőt ír ki, mert nézi a lokalizációt.
		System.out.printf("A kör területe %.1f %n", PI * sugar * sugar); // f =float 2 tizedesjegyik pl.:2,03
		// %n új sor.
		System.out.format("A kör kerülete %.3f", PI * sugar * 2); // f =float 3 tizedesjegyik
	}

	public static void TipusbaParszoloHibasMegoldas() {
		Scanner sc = new Scanner(System.in);
		/*
		 * Adatbekérésnél használható nextLine helyett közvetlenül a típusba is parszoló
		 * metódust is (pl.: nextInt()), DE: a bemenetet lezáró Enter benne marad a
		 * bemeneti csatornában (nextInt nem veszi figyelembe) ezért ha később szöveget
		 * is beolvasunk, azt üres Enterként fogja értelmezni.
		 */
		// Java programozói vizsgán is többször elrontják.
		// Inkább nextLine és parszolást használj!
		System.out.println("Adjon meg egy egész számot:");
		// int a =Integer.parseInt(sc.nextLine());
		int a = sc.nextInt();

		// Benne marad az enter is, emiatt nem tudjuk megadni a nevet.
		// Inkább érdemes a nextLine-t használni.
		sc.reset();
		System.out.println("Adja meg a nevét:");
		String nev = sc.nextLine();

		System.out.println(a);
		System.out.println(nev);

		// Output:
		// Adjon meg egy egész számot:
		// 5
		// Adja meg a nevét:
		// 5
		// És vége a programnak, meg se tudtam adni a nevet.

		sc.close(); // Nem lehet korábban lezárni a bemeneti csatornát.

	}

	public static void TernalisIf() {
		String eredmeny = (0 != 1) ? "Igaz ág." : "Hamis ág.";
		System.out.println(eredmeny);
	}

	public static void SzamokSzaztolHarmassaval() {

		// Írja ki száztól hármasával a számokat, az első 16-tal maradék nélkül osztható
		// számig.
		for (int i = 100; i % 16 != 0; i += 3) {
			System.out.println(i);
		}

		int j = 100;
		while (j % 16 != 0) {
			System.out.println(j);
			j += 3;

		}
	}

	public static void SzamBekereseAmigNemParos() throws NumberFormatException, IOException {
		// Kérjen be egy számot mindaddig, amíg a felhasználó nem páros számot ad meg.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int szam;
		do {
			System.out.println("Adj meg egy páros számot:");
			szam = Integer.parseInt(br.readLine());

		} while (szam % 2 != 0);
	}

	public static void SzamBekeresAmigNemParosEsKivetelkezeles() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int szam;

		while (true) {
			System.out.println("Adj meg egy egész számot: ");
			try {
				szam = Integer.parseInt(br.readLine());
				if (szam % 2 == 0) {
					break;
				}
				System.out.println("Nem páros szám.");
			} catch (Exception e) {
				System.out.println("Hibás input!");
			}
		}

	}
}
