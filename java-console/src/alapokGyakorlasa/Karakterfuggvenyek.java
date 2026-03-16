package alapokGyakorlasa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Karakterfuggvenyek {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	

	public static void KarakterfuggvenyEsStringfuggvenyGyakorlas() throws IOException {

		System.out.println("Add meg a neved: ");

		String nev = br.readLine();

		int hanyDBaBetuVanBenne = 0;
		int karakterDB = 0;
		int szamjegy = 0;

		nev = nev.toLowerCase();

		for (char karakter : nev.toCharArray()) {
			System.out.println(karakter);

			// Ha 0-ad vissza, akkor van megegyezik az érték.
			if (Character.compare(karakter, 'a') == 0) {
				hanyDBaBetuVanBenne++;
			}

			// Szám, írásjel, szóköz nem számít karakternek.
			if (Character.isLetter(karakter)) {
				karakterDB++;
			}

			if (Character.isDigit(karakter)) {
				szamjegy++;
			}

		}

		System.out.println("Ennyi db a betű van a nevedben: " + hanyDBaBetuVanBenne
				+ "\nEnnyi karakter van a nevedben: " + karakterDB + "\nEnnyi számjegy van a nevedben: " + szamjegy);

		if (Character.isLowerCase(nev.charAt(0)) == true) {

			System.out.println("Ejnye-bejnye, nem nagybetűvel kezdted a neved.");
		}
	}

	public static void SztingfuggvenyGyakorlas() throws IOException {

		System.out.println("Írj be bármilyen szöveget: ");

		String szoveg = br.readLine(); // abcd
		System.out.println("Leszedem az első betűt: " + szoveg.substring(1)); // output: bcd

		if (szoveg.length() > 3) {
			System.out.println("Kiírom, csak a másodiktől a harmadik karakterig: " + szoveg.substring(1, 3)); // output:
																												// bc
		}
		String[] tomb = szoveg.split(""); // Valami alapján szétválasztom és lementem egy tömbbe betűnként.

		for (String betuk : tomb) {
			System.out.println(betuk);

		}

		// Ternális operátor.
		System.out.println((szoveg.startsWith("a")) ? "a betűvel kezdődik" : "Nem a betűvel kezdődik.");

		System.out.println((szoveg.lastIndexOf("a") == -1) ? "Nincsen benne a."
				: "Az a betű utolsó előfordulási index: " + szoveg.lastIndexOf("a"));
	}
}
