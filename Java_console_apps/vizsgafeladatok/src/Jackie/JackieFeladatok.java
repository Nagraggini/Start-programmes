package Jackie;

import java.util.*;

public class JackieFeladatok {

    // Melyik évben indult el a legtöbbször, holtverseny nem volt.
    public void legtobbVerseny(ArrayList<Jackie> lista) {
        int legtobbIndulas = 0;
        int legtobbIndulasEve = 0;

        // foreach, bővített for ciklus
        for (Jackie jackie : lista) {

            // races -> hány versenyen indult
            if (legtobbIndulas < jackie.getRaces()) {

                legtobbIndulas = jackie.getRaces();
                legtobbIndulasEve = jackie.getYear();
            }
        }

        System.out.println("4. feladat: " + legtobbIndulasEve);

    }

    /*
     * Határozza meg és írja ki a minta szerint, hogy Jackie Stewart számára melyik
     * évtized mennyire volt sikeres a megnyert versenyek száma alapjan! Az évtized
     * alatt az évek tízes csoportját érjük, azaz például a 70-es évek alatt az
     * 1970-1979-ig terjedő tartományt.
     */
    // Minta: 70-es évek: 21 megnyert verseny
    // 60-as évek: 32 megnyert verseny

    public void evenkentiEredenyek(ArrayList<Jackie> lista) {
        // year races wins podiums poles fastests
        // key=évtized | value=győzelmek száma
        HashMap<Integer, Integer> gyozelmek = new HashMap<>();

        // for each
        for (Jackie jackie : lista) {

            String ev = jackie.getYear() + ""; // konkatenáció
            int evtized = Integer.parseInt(ev.substring(2, 3));

            if (gyozelmek.containsKey(evtized)) {
                // Van ilyen érték, szóval hozzáadjuk a győzelmekszámát.
                // .get(key) -> visszatér a value.
                gyozelmek.put(evtized, (gyozelmek.get(evtized) + jackie.getWins()));
            } else {
                // Nincs ilyen évtized. Felvisszük az értéket.
                gyozelmek.put(evtized, jackie.getWins());
            }
        }

        // HashMap rendezése.
        Map<Integer, Integer> rendezettStat = new TreeMap<>(gyozelmek); // import java.util.*;

        System.out.println("5. feladat: ");

        // foreach, bővített for ciklus
        for (int i : rendezettStat.keySet()) {
            // Minta: 70-es évek: 21 megnyert verseny
            System.out.println("\t" + i + "-es/-as évek: " + rendezettStat.get(i) + " megnyert verseny");
        }
    }

}
