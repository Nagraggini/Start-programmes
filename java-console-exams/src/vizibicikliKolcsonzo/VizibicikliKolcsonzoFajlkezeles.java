package vizibicikliKolcsonzo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VizibicikliKolcsonzoFajlkezeles {

    // A fájl első sora az oszlopneveket tartalmazza.
    // A beolvasott értékeket összetett adatszerkezetben kell tárolni.

    // Használom az osztályt típusként, ahol az adatszerkezetek vannak megadva.
    public ArrayList<Kolcsonzes> lista = new ArrayList<>();

    public ArrayList tesztAdatFeltoltese(ArrayList lista) {

        lista.add(new Kolcsonzes("Teszt", "A001", 1, 20, 6, 0));

        return lista;
    }

    // Az elso sorban az oszlopnevek vannak.
    public void fajlBeolvasasa(String fajlnev, ArrayList<Kolcsonzes> lista) {

        // A fájlnak a projekt gyökér könyvtárában kell lennie.
        File f = new File(fajlnev);

        if (!f.exists()) {
            System.out.println("Nem létezik a fájl.");
            System.out.println("Itt keresem: " + System.getProperty("user.dir"));
            return; // Így nem is megy tovább a program, ha nem létezik a fájl.
            // TODO: Kérdés, hogy akkor létrehozzon-e egy újat.
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String sor;

            // Az első sor az oszlopneveket tartalmazza.
            sor = br.readLine(); // kihagyjuk az oszlopneveket

            while ((sor = br.readLine()) != null) {
                // Sor szétválasztása pontosvesszővel.
                String[] adatok = sor.split(";");

                // Feltételezzük, hogy az adatok a Kolcsonzes konstruktorával egyeznek.
                /*
                 * Példa: new Kolcsonzes(String nev, String jAzon, int eOra, int ePerc, int
                 * vOra, int vPerc)
                 */
                if (adatok.length == 6) { // ellenőrzés, hogy elég adat van
                    Kolcsonzes k = new Kolcsonzes(adatok[0], adatok[1], Integer.parseInt(adatok[2]),
                            Integer.parseInt(adatok[3]), Integer.parseInt(adatok[4]), Integer.parseInt(adatok[5]));
                    lista.add(k);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList gyanusitottakKiiratasaFajlba(ArrayList<String> gyanusitottak) {

        // Létrehozzuk a fájlt.
        try (FileWriter writer = new FileWriter("F.txt")) {

            for (String egyGyanusitott : gyanusitottak) {
                writer.write(egyGyanusitott + "\n");
            }

            System.out.println("9. feladat: F.txt létrehozva.");
        } catch (IOException e) {
            System.out.println(e);
        }
        return gyanusitottak;
    }

}
