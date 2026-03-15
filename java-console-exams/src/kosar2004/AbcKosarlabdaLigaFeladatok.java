package kosar2004;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AbcKosarlabdaLigaFeladatok {
    // https://infojegyzet.hu/vizsgafeladatok/okj-programozas/szoftverfejleszto-200526/

    public static ArrayList<AbcKosarlabdaLiga> lista = new ArrayList<>();
    public static HashMap<Integer, AbcKosarlabdaLiga> hmap = new HashMap<>();

    public static void main(String[] args) {

        // 2. feladat:
        // Van amikor ez a jó: StandardCharsets.UTF_8
        // Van amikor ez a jó:Charset.forName("windows-1250")

        Path path = Path.of("java-console-exams/eredmenyek.csv");

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

                hmap.put(i, new AbcKosarlabdaLiga(t[0], t[1], Integer.parseInt(t[2]), Integer.parseInt(t[3]), t[4],
                        LocalDate.parse(t[5])));

            }
        } catch (IOException ex) {
            System.getLogger(AbcKosarlabdaLiga.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        // System.out.println("sorok száma: " + lista.size());

        // Kiiratas
        // lista.forEach(l -> System.out.println(l.toString()));
        /*
         * hmap.forEach((kulcs, ertek) -> { System.out.println(kulcs + " " +
         * ertek.toString()); });
         */

        // 3. feladat:
        long realDBHazai = lista.stream().filter(a -> a.getHazai().equals("Real Madrid")).count();

        long realDBIdegen = lista.stream().filter(a -> a.getIdegen().equals("Real Madrid")).count();

        System.out.println("3. feladat: Real Madrid: Hazai: " + realDBHazai + ", Idegen: " + realDBIdegen);

        // 4. feladat:
        // Megszámoljuk, hogy volt-e döntetlen.
        long dontetlen = lista.stream(). // Adatfolyammá alakítás.
                filter(x -> x.getHazaiPont() == x.getIdegenPont()) // Szűrési feltétel.
                .count(); // Megszámoljuk.

        // A három operandust zárójelbe kell rakni. Ternális operátor.
        System.out.println("4. feladat: Volt-e döntetlen? " + ((int) dontetlen == 0 ? "nem" : "igen"));

        // 5. feladat:
        /*
         * Határozza meg és írja ki a minta szerint, hogy a barcelonai csapatnak mi a
         * pontos neve! (Feltételezheti, hogy a csapat neve tartalmazza Barcelona város
         * nevét,
         * és a csapat játszott otthon is legalább egy mérkőzést.)
         */

        String barcelonaiCsapatNeve = lista.stream()
                .filter(x -> x.getHazai().contains("Barcelona")).map(x -> x.getHazai()).findFirst().get();
        System.out.println("5. feladat: barcelonai csapat neve: " + barcelonaiCsapatNeve);

        // 6. feladat:
        /*
         * Határozza meg és írja ki a minta szerint, hogy 2004. november 21-én mely
         * csapatok játszottak mérkőzéseket, és milyen eredmény született!
         */
        LocalDate datum = LocalDate.of(2004, 11, 21);

        System.out.println("6. feladat:");
        lista.stream().filter(x -> x.getIdopont().isEqual(
                datum)).forEach(x -> System.out.println(
                        "\t" + x.getHazai() + "-" + x.getIdegen() + " (" + x.getHazaiPont() + ":" + x.getIdegenPont()
                                + ")"));

        // 7. feladat
        /*
         * Határozza meg és írja ki a minta szerint, hogy melyek azok a stadionok,
         * amelyek 20-nál több alkalommal voltak kosárlabdamérkőzések helyszínei! A
         * stadionok neve mögött jelenjen meg a mérkőzések száma is! A feltételnek
         * megfelelő stadionok tetszőleges sorrendben jelenhetnek meg.
         */

        System.out.println("7. feladat: ");
        lista.stream() // Az első stream az eredeti adatokat dolgozza fel.
                // Ez a "lelke" az egésznek. Egy Map<String, Long> struktúrát hoz létre, ahol a
                // kulcs a helyszín, az érték pedig az adott helyszínhez tartozó elemek száma.
                .collect(Collectors.groupingBy(AbcKosarlabdaLiga::getHelyszin, Collectors.counting()))
                // Mivel a Map-en nem tudunk közvetlenül streamelni, le kell kérnünk a
                // kulcs-érték párok halmazát (entrySet), majd abból indítunk egy új streamet.
                .entrySet()
                .stream() // A második stream, már a statisztikát (a Map-et) dolgozza fel.
                .filter(e -> e.getValue() > 20) // Feltétel.
                .forEach(e -> System.out.println("\t" + e.getKey() + " : " + e.getValue())); // Kiíratás.
    }

}
