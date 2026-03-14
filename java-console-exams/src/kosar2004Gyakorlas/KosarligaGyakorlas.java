package kosar2004Gyakorlas;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static java.util.stream.Collectors.groupingBy;

public class KosarligaGyakorlas {

    // https://infojegyzet.hu/vizsgafeladatok/okj-programozas/szoftverfejleszto-200526/

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

    // =====================================================================================
    // STREAM API GYORSSEGÉD (A három szint)
    // =====================================================================================
    // SZINT | MŰVELET | LEÍRÁS
    // =====================================================================================
    // 1. FORRÁS | .stream() | Elindítja a folyamatot. (KÖTELEZŐ)
    // =====================================================================================
    // 2. KÖZTES | .filter(x -> ...) | Szűrés (csak az marad, ami TRUE).
    // (Bármennyi | .map(x -> x.get) | Átalakítás (pl. objektumból csak egy mező).
    // lehet) | .sorted() | Sorba rendezés.
    // | .distinct() | Ismétlődések törlése.
    // | .limit(n) | Csak az első n darab elemet hagyja meg.
    // =====================================================================================
    // 3. LEZÁRÓ | .count() | Megszámolja az elemeket -> eredmény: long.
    // (Csak EGY | .forEach(x -> ...) | Művelet minden elemen (pl. kiírás) -> void.
    // lehet a | .toList() | Új listába gyűjt -> eredmény: List<T>.
    // végén!) | .anyMatch(x -> ...) | Van-e legalább egy ilyen? -> boolean.
    // | .findFirst() | Visszaadja a legelsőt -> Optional<T>.
    // =====================================================================================

    // PÉLDA GYORSAN:
    // long db = lista.stream().filter(x -> x.getHazai().equals("Real
    // Madrid")).count();
    // lista.stream().filter(x -> x.getHazaiPont() >
    // 100).forEach(System.out::println);
    // List<String> varosok = lista.stream().map(x ->
    // x.getHelyszin()).distinct().toList();

    // PÉLDÁK A HASZNÁLATRA:

    // 1. Megszámolás: Hány meccs volt Madridban?
    // long db = lista.stream().filter(x ->
    // x.getHelyszin().equals("Madrid")).count();

    // 2. Kiíratás: Írd ki a 100 pont feletti hazai meccseket!
    // lista.stream().filter(x -> x.getHazaiPont() >
    // 100).forEach(System.out::println);

    // 3. Gyűjtés: Add vissza a városok neveit ABC sorrendben, ismétlődés nélkül!
    // List<String> varosok = lista.stream()
    // .map(AbcKosarlabdaLiga::getHelyszin)
    // .distinct()
    // .sorted()
    // .toList();

    public static ArrayList<AbcKosarlabdaLiga> lista = new ArrayList<>();
    public static HashMap<Integer, AbcKosarlabdaLiga> hmap = new HashMap<>();

    public static void main(String[] args) {

        Fajlbeolvasasa("java-console-exams/eredmenyek.csv");

        System.out.println("Sorok száma: " + lista.size());
        melyikVarosbanHanyMeccsVolt(lista);

    }

    public static void Fajlbeolvasasa(String fajlneve) {

        // 2. feladat:
        // Van amikor ez a jó: StandardCharsets.UTF_8
        // Van amikor ez a jó:Charset.forName("windows-1250")

        Path path = Path.of(fajlneve);

        // Ellenőrzés és beolvasás egyben
        if (!Files.exists(path)) {
            System.out.println("Nem létezik a fájl!");
            System.out.println("Itt keresem: " + System.getProperty("user.dir"));
            return; // Ha nincs fájl, ne is menjünk tovább a try-ra
        }

        try {
            List<String> sorok = Files.readAllLines(path, Charset.forName("windows-1250"));

            // 1-től megyünk, mert van oszlopnév is.
            for (int i = 1; i < sorok.size(); i++) {
                String[] t = sorok.get(i).split(";");

                lista.add(new AbcKosarlabdaLiga(t[0], t[1], Integer.parseInt(t[2]), Integer.parseInt(t[3]), t[4],
                        LocalDate.parse(t[5])));
            }
        } catch (IOException ex) {
            System.getLogger(AbcKosarlabdaLiga.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static void kiiratasArrayList(ArrayList<AbcKosarlabdaLiga> lista) {

        lista.forEach(l -> System.out.println(l.toString()));

        // A stream ilyen esetben felesleges, mert nincs filter, vagy map.
        // lista.stream().forEach(x -> System.out.println(x.getHelyszin()));

    }

    public static void kiiratasHashMap(HashMap<Integer, AbcKosarlabdaLiga> hmap) {
        // foreach+lambda
        hmap.forEach((kulcs, ertek) -> {
            System.out.println("Kulcs: " + kulcs + " érték: " + ertek);

        });

        // Egy elem kiirasa.
        // 0. vagy 1. elemet írja ki ez attól függ, hogy van-e fejléc.
        System.out.println("Első elem: " + (hmap.get(0) == null ? hmap.get(1) : hmap.get(0)));
        System.out.println("Utolsó elem: " + hmap.get(hmap.size()));

    }

    public static void melyikVarosbanHanyMeccsVolt(ArrayList<AbcKosarlabdaLiga> lista) {

        // Első megoldás.
        System.out.println("\nMegoldás ArrayList+HashMap-el.");
        HashMap<String, Integer> stat = new HashMap<>();

        // Megszámoljuk, hogy melyik városban hányszor volt meccs.
        for (AbcKosarlabdaLiga a : lista) {
            stat.put(a.getHelyszin(), stat.getOrDefault(a.getHelyszin(), 0) + 1);
        }

        stat.forEach((kulcs, ertek) -> System.out.println("Kulcs: " + kulcs + " érték: " + ertek));

        // Második szebbik megoldás stream apival.
        lista.stream().collect(groupingBy(AbcKosarlabdaLiga::getHelyszin, java.util.stream.Collectors // Csoportosítunk
                                                                                                      // helyszín
                                                                                                      // alapján.
                .counting())) // Megszámoljuk.
                .forEach((varos, db) -> System.out.println(varos + " : " + db + " meccs.")); // Kiiratás.

    }

    public static void nagyFolennyelNyertek(ArrayList<AbcKosarlabdaLiga> lista, int kulonbseg) {

        // A pontszámok közti különbség alapján szűrünk.
        lista.stream()
                // Csak azokat hagyjuk meg, ahol a különbség pontosan ennyi
                .filter(l -> Math.abs(l.getIdegenPont() - l.getHazaiPont()) == kulonbseg)
                // A maradékot pedig kiíratjuk
                .forEach(l -> System.out.println(l.toString()));

    }

    public static void realMadridMeccsek(ArrayList<AbcKosarlabdaLiga> lista) {

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

    }

    public static void voltEDontetlen(ArrayList<AbcKosarlabdaLiga> lista) {

        // Megszámoljuk, hogy volt-e döntetlen.
        long dontetlen = lista.stream(). // Adatfolyammá alakítás.
                filter(x -> x.getHazaiPont() == x.getIdegenPont()) // Szűrési feltétel.
                .count(); // Megszámoljuk.

        // A három operandust zárójelbe kell rakni. Ternális operátor.
        System.out.println("4. feladat: Volt-e döntetlen? " + ((int) dontetlen == 0 ? "nem" : "igen"));

    }

}
