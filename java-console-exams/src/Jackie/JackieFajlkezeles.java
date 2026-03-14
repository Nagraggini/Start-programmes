package Jackie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JackieFajlkezeles {

    // Összetett adatszerkezet, amiben tároljuk az adatokat.
    ArrayList<Jackie> lista = new ArrayList<>();

    public ArrayList fajlBeolvasas(ArrayList<Jackie> lista, String fajlnev) {

        File f = new File(fajlnev);

        // Létezik-e a fájl.
        if (!f.exists()) {
            System.out.println("Nem létezik a fájl.");
            System.out.println("Itt keresem: " + System.getProperty("user.dir"));
        }

        // try with resources
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fajlnev)))) {

            String sor;
            sor = br.readLine();// Az első sorban az oszlopnevek vannak.

            while ((sor = br.readLine()) != null) {// Addig megyünk, ameddig van sor.
                // Sor szétválasztása tabulátor alapján
                String[] adatok = sor.split("\t");

                // length csekk, hány darab oszlopunk van
                if (adatok.length == 6) {
                    Jackie jackie = new Jackie(Integer.parseInt(adatok[0]), Integer.parseInt(adatok[1]),
                            Integer.parseInt(adatok[2]), Integer.parseInt(adatok[3]), Integer.parseInt(adatok[4]),
                            Integer.parseInt(adatok[5]));

                    lista.add(jackie);
                }
                // System.out.println("Szöveg: " + sor);
            }
        } catch (IOException ex) {
            System.getLogger(JackieFajlkezeles.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        System.out.println("3. feladat: " + lista.size());

        return lista;
    }

    /*
     * Hozzon létre jackie.html néven UTF-8 kódolású szöveges állományt! Az állomány
     * szabványos HTML5 formátumú legyen, azzal a kitétellel, hogy a head elem
     * tartalma üresen hagyható!
     * 
     * Az állományban táblázatos formában jelenjen meg a versenyzés éve, a versenyek
     * és a győzelmek száma!
     * 
     * A táblázat felett első szintű címsorral jelenjen meg Jackie Stewart neve!
     * 
     * Oldja meg, hogy a táblázat cellái egy képpont vastag folytonos fekete
     * vonallal legyenek keretezve.
     */

    public ArrayList htmlFajlLetrehozasEsFeltoltes(ArrayList<Jackie> lista, String fajlnev) {

        // A PrintWriter magasabb szintű író. Könnyebb vele sorokat és formázott
        // szöveget írni.
        // try with resource megoldással
        try (PrintWriter ki = new PrintWriter(fajlnev, "UTF-8")) {

            ki.println("<!DOCTYPE html>");
            ki.println("<html lang=\"hu\">");
            ki.println("<head>");
            ki.println("</head>");
            ki.println("<body>");

            ki.println("<h1>Jackie Stewart</h1>");

            ki.println("<table style='border-collapse: collapse;'>");

            ki.println("<tr>");
            ki.println("<th style='border:1px solid black;'>Év</th>");
            ki.println("<th style='border:1px solid black;'>Versenyek</th>");
            ki.println("<th style='border:1px solid black;'>Győzelmek</th>");
            ki.println("</tr>");

            for (Jackie jackie : lista) {
                ki.println("<tr>");
                ki.println("<td style='border:1px solid black;'>" + jackie.getYear() + "</td>");
                ki.println("<td style='border:1px solid black;'>" + jackie.getRaces() + "</td>");
                ki.println("<td style='border:1px solid black;'>" + jackie.getWins() + "</td>");
                ki.println("</tr>");
            }

            ki.println("</table>");
            ki.println("</body>");
            ki.println("</html>");

            ki.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("6. feladat: " + fajlnev);
        return lista;
    }

}
