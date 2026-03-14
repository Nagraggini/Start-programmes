package kosar2004;

import java.util.ArrayList;
import java.util.HashMap;

public class KosarligaGyakorlas {

    /*
     * Mit használj a vizsgán?
     * Beolvasás: Mindig olvass be egy ArrayList-be (ez az alap).
     * 
     * Keresés/Szűrés/Statisztika: Használd a listát és a Stream API-t.
     * 
     * Egyedi kulcsos keresés: Csak akkor készíts HashMap-et, ha a feladat
     * kifejezetten kéri, hogy egy azonosító alapján keress ki valamit
     * villámgyorsan.
     */

    public HashMap kiiratasHashMap(HashMap<Integer, AbcKosarlabdaLiga> hmap) {
        // foreach+lambda
        hmap.forEach((kulcs, ertek) -> {
            // System.out.println("Kulcs: " + kulcs + " érték: " + ertek);
            // System.out.println("Kulcs: " + kulcs + " érték: " + ertek.getHazai());
        });

        // Egy elem kiirasa.
        // 0. vagy 1. elemet írja ki ez attól függ, hogy van-e fejléc.
        System.out.println("Első elem: " + (hmap.get(0) == null ? hmap.get(1) : hmap.get(0)));
        System.out.println("Utolsó elem: " + hmap.get(hmap.size()));

        return hmap;
    }

    public ArrayList kiiratasArrayList(ArrayList<AbcKosarlabdaLiga> lista) {
        lista.stream().forEach(x -> System.out.println(x.getHelyszin()));

        return lista;
    }

    public HashMap melyikVarosbanHanyMeccsVolt(HashMap<Integer, AbcKosarlabdaLiga> hmap) {

        // & Megszámoljuk, hogy melyik városban hányszor volt meccs.
        // Segéd hashmap a stathoz.
        HashMap<String, Integer> stat = new HashMap<>();

        // foreach
        for (AbcKosarlabdaLiga a : hmap.values()) {
            stat.put(a.getHelyszin(), // Kulcs és érték párt rögzítünk ebben.
                    stat.getOrDefault // A darabszámhoz kell.
                    (a.getHelyszin() // Keresett érték.
                            , 0)// Ha nem létezik az elem, akkor 0-át adunk vissza.
                            + 1);// Ha létezik, akkor növeljük eggyel az értéket.
        }

        stat.forEach((kulcs, ertek) -> System.err.println(kulcs + " városban  " + ertek + " db meccs volt."));

        return hmap;
    }

    public ArrayList realMadridMeccsek(ArrayList<AbcKosarlabdaLiga> lista) {

        // Real Madrid hány mérkőzést játszott hazai és idegen csapatként. Külön-külön
        // írd ki.

        // Egyik megoldás foreach+lamda.
        int[] realDB = { 0, 0 };

        lista.forEach(a -> {
            if (a.getHazai().equals("Real Madrid")) {
                realDB[0]++;
            } else if (a.getIdegen().equals("Real Madrid")) {
                realDB[1]++;
            }

        });

        // Másik megoldás stream api+lambda.
        long hazaiMeccsek = lista.stream()
                .filter(a -> a.getHazai().equals("Real Madrid"))
                .count();

        long idegenMeccsek = lista.stream()
                .filter(a -> a.getIdegen().equals("Real Madrid"))
                .count();

        System.out.println("3. feladat: Real Madrid: Hazai: " + hazaiMeccsek + ", Idegen: " + idegenMeccsek);

        return lista;
    }

    public HashMap realMadridMeccsek(HashMap<Integer, AbcKosarlabdaLiga> hmap) {

        // Real Madrid hány mérkőzést játszott hazai és idegen csapatként. Külön-külön
        // írd ki.

        long hazaiMeccsek = hmap.values().stream()
                .filter(a -> a.getHazai().equals("Real Madrid"))
                .count();

        long idegenMeccsek = hmap.values().stream()
                .filter(a -> a.getIdegen().equals("Real Madrid"))
                .count();

        System.out.println("3. feladat: Real Madrid: Hazai: " + hazaiMeccsek + ", Idegen: " + idegenMeccsek);
        return hmap;
    }

    public ArrayList voltEDontetlen(ArrayList<AbcKosarlabdaLiga> lista) {

        // & Megszámoljuk, hogy volt-e döntetlen.
        long dontetlen = lista.stream(). // Adatfolyammá alakítás.
                filter(x -> x.getHazaiPont() == x.getIdegenPont()) // Szűrési feltétel.
                .count(); // Megszámoljuk.

        // A három operandust zárójelbe kell rakni. Ternális operátor.
        System.out.println("4. feladat: Volt-e döntetlen? " + ((int) dontetlen == 0 ? "nem" : "igen"));

        // TODO:
        // & Megszámoljuk, hogy melyik városban hányszor volt meccs.

        return lista;
    }

}
