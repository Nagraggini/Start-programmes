package kosar2004gyakorlas;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;

public class GyakorlasSteamAPIKozepesSzint {

    // https://infojegyzet.hu/vizsgafeladatok/okj-programozas/szoftverfejleszto-200526/

    // Puska: https://github.com/Nagraggini/blog/blob/main/Java_basic_knowledge.md

    public static ArrayList<AbcKosarlabdaLiga2> lista = new ArrayList<>();
    public static HashMap<Integer, AbcKosarlabdaLiga2> hmap = new HashMap<>();

    // Adat tagok: String hazai, String idegen, int hazaiPont, int idegenPont,
    // String helyszin, LocalDate idopont

    public static void main(String[] args) {

        Fajlbeolvasasa("java-console-exams/eredmenyek.csv");

        System.out.println("Ügyes vagy!");

        // helyszinek(lista);
        // legnagyobbPontKulonbseg(lista);
        // osszesPont(lista);
        // hazaiCsapatok(lista);
        // voltDontetlen(lista);
        // kisPontKulonbseguMeccsek(lista);
        // meccsekSzama(lista);
        // hazaiGyozelemDb(lista);
        // atlagHazaiPont(lista);
        // varosMeccsei(lista, "Palacio Vistalegre");
        // elsoMeccs(lista);
        // nincsNagyKulonbseg(lista);
        pontKulonbsegek(lista);
        legkevesebbPont(lista);

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

                lista.add(new AbcKosarlabdaLiga2(t[0], t[1], Integer.parseInt(t[2]), Integer.parseInt(t[3]), t[4],
                        LocalDate.parse(t[5])));
            }
        } catch (IOException ex) {
            System.getLogger(AbcKosarlabdaLiga2.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static void legnagyobbPontKulonbseg(ArrayList<AbcKosarlabdaLiga2> lista) {

        // Segéd lista, ahol a meccsek és a pont különbség van felsorolva.
        HashMap<AbcKosarlabdaLiga2, Integer> stat = new HashMap<>();
        for (AbcKosarlabdaLiga2 a : lista) { // Berakjuk az elemeket.
            stat.put(a, (Math.abs(a.getHazaiPont() - a.getIdegenPont())));
        }

        stat.entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(max -> {
            System.out.println("A legnagyobb különbségű meccs adatai: \n" + max.getKey());
            System.out.println("Pontkülönbség: " + max.getValue());
        });
    }

    public static void melyikVarosbanHanyMeccsVolt(ArrayList<AbcKosarlabdaLiga2> lista) {

        // Első megoldás.
        System.out.println("\nMegoldás ArrayList+HashMap-el.");
        HashMap<String, Integer> stat = new HashMap<>();

        // Megszámoljuk, hogy melyik városban hányszor volt meccs.
        for (AbcKosarlabdaLiga2 a : lista) {
            stat.put(a.getHelyszin(), stat.getOrDefault(a.getHelyszin(), 0) + 1);
        }

        stat.forEach((kulcs, ertek) -> System.out.println("Kulcs: " + kulcs + " érték: " + ertek));

        // Második szebbik megoldás stream apival.
        lista.stream().collect(groupingBy(AbcKosarlabdaLiga2::getHelyszin, java.util.stream.Collectors // Csoportosítunk
                                                                                                       // helyszín
                                                                                                       // alapján.
                .counting())) // Megszámoljuk.
                .forEach((varos, db) -> System.out.println(varos + " : " + db + " meccs.")); // Kiiratás.

    }

    public static void hazaiGyozelmek(ArrayList<AbcKosarlabdaLiga2> lista) {

        // Csoportosítás és számolás.
        Map<String, Long> hazaiCsapatSzerintiCsoportositas = lista.stream() // Adatfolyammá alakítás.
                .filter(x -> (x.getHazaiPont() > x.getIdegenPont())) // Szűrési feltétel.
                // Összegyűjtés (típus, darabszám)
                .collect(Collectors.groupingBy(AbcKosarlabdaLiga2::getHazai, Collectors.counting()));

        System.out.println("A hazai csapatok ennyi meccsen nyertek, csapatok szerint csoportosítva: ");

        // 2. A Map rendezése és kiíratása.
        hazaiCsapatSzerintiCsoportositas.entrySet().stream()
                // Rendezés az érték (győzelmek száma) szerint fordított sorrendben, hogy a
                // csökkenő legyen.
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                // Kiíratás.
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

    }

    public static void pontkulonbsegSzerintiRendezes(ArrayList<AbcKosarlabdaLiga2> lista) {

        System.out.println("Pont különbség szerinti rendezés:");

        // Segéd lista, ahol a meccsek és a pont különbség van felsorolva.
        HashMap<AbcKosarlabdaLiga2, Integer> stat = new HashMap<>();
        for (AbcKosarlabdaLiga2 a : lista) { // Berakjuk az elemeket.
            stat.put(a, (Math.abs(a.getHazaiPont() - a.getIdegenPont())));
        }

        // Rendezés.
        Map<AbcKosarlabdaLiga2, Integer> rendezettStat = stat.entrySet().stream()
                // Rendezés.
                .sorted(Map.Entry.comparingByValue(
                        // Csökkenő sorrend.
                        Comparator.reverseOrder()))
                // Kigyűjtés.
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                        // Ütközéskezelés (itt nem lesz)
                        LinkedHashMap::new // Ez garantálja a sorrend megtarttását.
                ));

        // Kiíratás.
        rendezettStat.forEach((k, v) -> System.out.println(k + " , pont különbség: " + v));
    }

    private static void osszesPont(ArrayList<AbcKosarlabdaLiga2> lista) {
        System.out.println("Összes dobott pont:" + lista.stream().mapToInt(m -> m.getHazaiPont() + m.getIdegenPont())
                .sum());
    }

    private static void hazaiCsapatok(ArrayList<AbcKosarlabdaLiga2> lista) {
        System.out.println("Hazai csapatok nevei:");
        lista.stream().map(AbcKosarlabdaLiga2::getHazai).distinct().toList().forEach(x -> System.out.println(x));
    }

    private static void voltDontetlen(ArrayList<AbcKosarlabdaLiga2> lista) {
        System.out.println(
                (lista.stream().anyMatch(x -> (x.getIdegenPont() - x.getHazaiPont()) == 0)) ? "Van döntetlen meccs."
                        : "Nincs döntetlen meccs.");
    }

    private static void kisPontKulonbseguMeccsek(ArrayList<AbcKosarlabdaLiga2> lista) {
        System.out.println("Max 10 pont különbségű meccsek: ");
        lista.stream().filter(x -> (Math.abs(x.getHazaiPont() - x.getIdegenPont())) > 10)
                .forEach(x -> System.out.println(x.toString()));
    }

    private static void meccsekSzama(ArrayList<AbcKosarlabdaLiga2> lista) {
        System.out.println(
                "Ennyi meccs volt összesen: " + lista.stream().map(AbcKosarlabdaLiga2::getIdopont).count() + " db");
    }

    private static void hazaiGyozelemDb(ArrayList<AbcKosarlabdaLiga2> lista) {
        System.out.println(
                "Hazai győzelmek: " + lista.stream().filter(x -> (x.getHazaiPont() > x.getIdegenPont())).count());
    }

    private static void atlagHazaiPont(ArrayList<AbcKosarlabdaLiga2> lista) {
        System.out.println("Átlag hazai pont: " + Math.round(lista.stream().mapToInt(x -> x.getHazaiPont()).average()
                // ha üres lenne, 0-t ad vissza
                .orElse(0)));
    }

    private static void varosMeccsei(ArrayList<AbcKosarlabdaLiga2> lista, String varosNev) {
        lista.stream().filter(x -> x.getHelyszin().equals(varosNev))
                .forEach(x -> System.out.println("Meccsek " + varosNev + "-ban/ben: \n" + x.toString()));
    }

    private static void elsoMeccs(ArrayList<AbcKosarlabdaLiga2> lista) {
        lista.stream().findFirst().ifPresentOrElse( // Ha van találat...
                x -> System.out.println("Első meccs: " + x.toString()),
                () -> System.out.println("Nincs ilyen játékos a listában.") // Ha nincs...
        );
    }

    private static void nincsNagyKulonbseg(ArrayList<AbcKosarlabdaLiga2> lista) {
        System.out.println("Ezeken a meccseken kevesebb, mint 20 pont a különbség: ");
        lista.stream().filter(x -> (Math.abs(x.getHazaiPont() - x.getIdegenPont()) > 20))
                .forEach(x -> System.out.println(x.toString()));
    }

    private static void pontKulonbsegek(ArrayList<AbcKosarlabdaLiga2> lista) {
        lista.stream()
                .forEach(x -> System.out.println(
                        "Pont különbség: " + (Math.abs(x.getHazaiPont() - x.getIdegenPont())) + "\n" + x.toString()));
    }

    private static void legkevesebbPont(ArrayList<AbcKosarlabdaLiga2> lista) {

        OptionalInt hazaiMin = lista.stream()
                .mapToInt(AbcKosarlabdaLiga2::getHazaiPont)
                .min();

        OptionalInt idegenMin = lista.stream()
                .mapToInt(AbcKosarlabdaLiga2::getIdegenPont)
                .min();

        if (idegenMin.getAsInt() < hazaiMin.getAsInt()) {
            System.out.println("Legkevesebb idegen pont: " + idegenMin.getAsInt());
        } else {
            System.out.println("Legkevesebb hazai pont: " + hazaiMin.getAsInt());
        }
    }

}
/*
 * // TODO: Ezeket gyakorold.
 * 
 * // TODO logikai feltételek és intervallumok
 * /*
 * Gyakorold: Logikai feltételek és az "Intervallum-vizsgálat". De Morgan
 * azonosságok (Egyszerűsítés). Az isAfter(), isBefore() és isEqual() metódusok
 * használatát.
 */
/*
 * 
 * hazai_pont + idegen_pont 9️⃣ 2010 utáni meccsek
 * 
 * Írd ki az összes meccset ahol
 * 
 * idopont > 2010 🔟 200 pont feletti meccsek száma
 * 
 * Számold meg hány olyan meccs volt ahol
 * 
 * hazai_pont + idegen_pont > 200 Kicsit nehezebb 11️⃣ Csapatok száma
 * 
 * Számold meg hány különböző csapat szerepel az adatbázisban (hazai + idegen
 * együtt).
 * 
 * 12️⃣ Meccsek száma csapatonként
 * 
 * Készíts egy Map<String, Long> statisztikát ahol:
 * 
 * csapat -> hány meccset játszott
 * 
 * (Hazai + idegen együtt.)
 * 
 * 13️⃣ Legtöbb pontot szerző csapat hazai pályán
 * 
 * Találd meg azt a csapatot amelyik
 * 
 * hazai_pont összesen
 * 
 * alapján a legtöbb pontot dobta.
 * 
 * 14️⃣ Hány különböző helyszín van
 * 
 * Számold meg hány különböző:
 * 
 * helyszin
 * 
 * van az adatbázisban.
 * 
 * 15️⃣ Legtöbb meccset játszó csapat
 * 
 * Találd meg melyik csapat szerepel a legtöbb meccsen (hazai + idegen együtt).
 * 
 * & Ezután a haladó fájlt csináld!
 */
