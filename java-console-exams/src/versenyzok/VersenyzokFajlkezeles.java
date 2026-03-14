package versenyzok;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class VersenyzokFajlkezeles {

    public ArrayList<Versenyzok> fajlBeolvasas(ArrayList<Versenyzok> lista, String fajlnev) {

        // A fájlnak a projekt gyökér könyvtárában kell lennie.
        File f = new File(fajlnev);

        // Lecsekkoljuk, hogy létezik-e a fájl.
        if (!f.exists()) {
            System.out.println("Nem létezik a fájl!");
            System.out.println("Itt keresem: " + System.getProperty("user.dir"));
        }

        // try with resources
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String sor;
            
            sor = br.readLine();// Az első sor az oszlopneveket tartalmazza.

            while ((sor = br.readLine()) != null) {// Van-e még sor.
                String[] adatok = sor.split(";");

                String rajtszam = "";

                if (adatok.length > 3) {
                    rajtszam = adatok[3];
                }

                Versenyzok v = new Versenyzok(adatok[0], adatok[1], adatok[2], rajtszam);

                lista.add(v);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

}
