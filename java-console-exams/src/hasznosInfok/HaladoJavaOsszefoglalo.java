package hasznosInfok;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// Puska: https://github.com/Nagraggini/blog/blob/main/Java_basic_knowledge.md
public class HaladoJavaOsszefoglalo {

        public static void main(String[] args) {
                HaladoJavaOsszefoglalo h = new HaladoJavaOsszefoglalo();
                h.StreamFunkciok();
        }

        public void FajlBeolvasas(String fajlneve) {

                // Van amikor ez a jó: StandardCharsets.UTF_8
                // Van amikor ez a jó:Charset.forName("windows-1250")

                Path path = Path.of(fajlneve);

                // Ellenőrzés és beolvasás egyben
                if (!Files.exists(path)) {
                        System.out.println("Nem létezik a fájl!");
                        System.out.println("Itt keresem: " + System.getProperty("user.dir"));
                        return; // Ha nincs fájl, ne is menjünk tovább a try-ra
                }

                ArrayList<Operatorok2> lista = new ArrayList<>();

                try {
                        List<String> sorok = Files.readAllLines(path, StandardCharsets.UTF_8);

                        // Itt mehetünk 0-túl, mert nincs oszlopnév.
                        for (int i = 0; i < sorok.size(); i++) {
                                String[] t = sorok.get(i).split(" ");

                                lista.add(new Operatorok2(Integer.parseInt(t[0]), t[1], Integer.parseInt(t[2])));
                        }
                } catch (IOException ex) {
                        System.getLogger(Operatorok2.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
        }

        public void FajlKiiras() {
                // 1️⃣ Lista létrehozása
                ArrayList<Operatorok2> lista = new ArrayList<>();

                // 2️⃣ Példa adatok hozzáadása
                lista.add(new Operatorok2(10, "+", 5));
                lista.add(new Operatorok2(20, "-", 3));
                lista.add(new Operatorok2(4, "*", 6));

                try {

                        // 3️⃣ Lista kiírása fájlba Stream segítségével
                        Files.write(Path.of("eredmeny.txt"), // fájl neve
                                        lista.stream() // listából stream
                                                        .map(o -> o.toString())
                                                        // objektum -> String
                                                        .toList(), // Stream -> List<String>
                                        StandardCharsets.UTF_8 // karakterkódolás
                        );

                        System.out.println("Fájl kiírva: eredmeny.txt");

                } catch (IOException e) {
                        System.out.println("Hiba történt a fájl írásakor.");
                }
        }

        public void StreamFunkciok() {
                // Példa lista létrehozása
                List<Integer> szamok = Arrays.asList(5, 10, 15, 20, 25, 10, 5);

                // 1️⃣ Stream létrehozása a listából
                szamok.stream(); // Adatfolyammá alakítjuk a számoka.
                szamok.parallelStream(); // A feladatot több részre bontja és párhuzamosan a gép több prociján egyzserre
                                         // csinálja.

                // 2️⃣ Szűrés (Filtering)
                List<Integer> nagyobbMint10 = szamok.stream().filter(x -> x > 10) // csak a 10-nél nagyobb számokat
                                                                                  // tartjuk meg
                                .collect(Collectors.toList()); // visszaadjuk List-ként
                System.out.println("Számok > 10: " + nagyobbMint10);

                // 3️⃣ Ismétlődések eltávolítása
                List<Integer> distinctSzamok = szamok.stream().distinct() // eltávolítja az ismétlődő elemeket
                                .collect(Collectors.toList());
                System.out.println("Ismétlődés nélkül: " + distinctSzamok);

                // 4️⃣ Transzformálás (Mapping)
                List<Integer> szamokNegyzet = szamok.stream().map(x -> x * x) // minden számot négyzetre emelünk
                                .collect(Collectors.toList());
                System.out.println("Számok négyzete: " + szamokNegyzet);

                // 5️⃣ Rendezés
                List<Integer> rendezett = szamok.stream().sorted() // természetes sorrend (növekvő)
                                .collect(Collectors.toList());
                System.out.println("Rendezett számok: " + rendezett);

                // Egyedi comparator
                List<Integer> csokkeno = szamok.stream().sorted(Comparator.reverseOrder()) // csökkenő sorrend
                                .collect(Collectors.toList());
                System.out.println("Csökkenő sorrend: " + csokkeno);

                // 6️⃣ Limitálás és kihagyás
                List<Integer> elso3 = szamok.stream().limit(3) // csak az első 3 elem
                                .collect(Collectors.toList());
                System.out.println("Első 3 szám: " + elso3);

                List<Integer> kihagy3 = szamok.stream().skip(3) // az első 3 elem kihagyása
                                .collect(Collectors.toList());
                System.out.println("Az első 3 elem kihagyva: " + kihagy3);

                // 7️⃣ Feldolgozás / Iteráció
                System.out.print("Minden szám kiírása: ");
                szamok.stream().forEach(x -> System.out.print(x + " ")); // minden elemre művelet
                System.out.println();

                // 8️⃣ Aggregálás / Visszatérő érték
                long darab = szamok.stream().count(); // elemek számolása
                System.out.println("Összes szám: " + darab);

                boolean van10 = szamok.stream().anyMatch(x -> x == 10); // legalább egy 10-es van-e
                System.out.println("Van 10-es szám: " + van10);

                boolean mindenPozitiv = szamok.stream().allMatch(x -> x > 0); // minden szám pozitív-e
                System.out.println("Minden szám pozitív: " + mindenPozitiv);

                boolean nincs0 = szamok.stream().noneMatch(x -> x == 0); // egyik sem 0
                System.out.println("Nincs 0: " + nincs0);

                Optional<Integer> elso = szamok.stream().findFirst(); // első elem Optional-ként
                System.out.println("Első szám: " + elso.orElse(-1));

                Optional<Integer> barmelyik = szamok.stream().findAny(); // bármelyik elem Optional-ként
                System.out.println("Bármelyik szám: " + barmelyik.orElse(-1));

                // 9️⃣ Redukció
                int osszeg = szamok.stream().reduce(0, Integer::sum); // összegzés
                System.out.println("Összeg: " + osszeg);

                Optional<Integer> max = szamok.stream().reduce(Integer::max); // maximum keresés
                System.out.println("Maximum: " + max.orElse(-1));

                // 10️⃣ Gyűjtés (Collectors)
                String szoveg = szamok.stream().map(String::valueOf).collect(Collectors.joining(", ")); // String-é
                                                                                                        // fűzés
                System.out.println("Számok String-ként: " + szoveg);

                List<Integer> listaGyujtes = szamok.stream().filter(x -> x > 10).collect(Collectors.toList());
                System.out.println("Számok > 10 (collect): " + listaGyujtes);

                // 11️⃣ Peek (debug)
                szamok.stream().peek(x -> System.out.println("Peek: " + x)) // minden elem előtt
                                .map(x -> x * 2).forEach(x -> {
                                }); // csak demonstráció, nincs kimenet
        }

        public void HashMap() {

                // 1️⃣ HashMap létrehozása
                Map<String, Integer> gyumolcsok = new HashMap<>(); // kulcs-érték párokat tároló adatszerkezet (kulcs:
                                                                   // String,
                                                                   // érték: Integer)

                // 2️⃣ Elem hozzáadása
                gyumolcsok.put("alma", 3); // új kulcs-érték pár hozzáadása
                gyumolcsok.put("körte", 5); // another element hozzáadása
                gyumolcsok.put("banán", 2); // harmadik elem

                System.out.println("HashMap tartalma: " + gyumolcsok);

                // 3️⃣ Elem lekérése
                int almaDb = gyumolcsok.get("alma"); // az "alma" kulcshoz tartozó érték lekérése
                System.out.println("Alma darab: " + almaDb);

                // 4️⃣ Kulcs létezésének ellenőrzése
                boolean vanAlma = gyumolcsok.containsKey("alma"); // ellenőrzi, hogy van-e ilyen kulcs
                System.out.println("Van alma kulcs: " + vanAlma);

                // 5️⃣ Érték létezésének ellenőrzése
                boolean van5 = gyumolcsok.containsValue(5); // ellenőrzi, hogy van-e ilyen érték
                System.out.println("Van 5 érték: " + van5);

                // 6️⃣ Elem módosítása
                gyumolcsok.put("alma", 10); // ha már létezik a kulcs, akkor az érték felülíródik
                System.out.println("Módosított alma érték: " + gyumolcsok.get("alma"));

                // 7️⃣ Elem törlése
                gyumolcsok.remove("banán"); // a kulcshoz tartozó pár törlése
                System.out.println("Banán törölve: " + gyumolcsok);

                // 8️⃣ HashMap mérete
                int meret = gyumolcsok.size(); // hány kulcs-érték pár van a map-ben
                System.out.println("HashMap mérete: " + meret);

                // 9️⃣ Iterálás a kulcsokon
                System.out.println("Kulcsok:");
                for (String kulcs : gyumolcsok.keySet()) { // végigmegy az összes kulcson
                        System.out.println(kulcs);
                }

                // 🔟 Iterálás kulcs-érték párokon
                System.out.println("Kulcs-érték párok:");
                for (Map.Entry<String, Integer> elem : gyumolcsok.entrySet()) { // kulcs és érték egyszerre elérhető
                        System.out.println(elem.getKey() + " -> " + elem.getValue());
                }

                // 🔟/1️⃣ Iterálás kulcs-érték párokon foreach-el

                gyumolcsok.forEach((kulcs, ertek) -> {
                        System.out.println(kulcs + " " + ertek);
                });

                // 1️⃣1️⃣ getOrDefault használata
                int narancs = gyumolcsok.getOrDefault("narancs", 0); // ha nincs ilyen kulcs, akkor alapértelmezett
                                                                     // értéket ad
                System.out.println("Narancs darab: " + narancs);

                // 1️⃣2️⃣ clear
                gyumolcsok.clear(); // az összes elem törlése
                System.out.println("HashMap ürítés után: " + gyumolcsok);
        }

        public void Osszefoglalo() {

                List<Integer> lista = Arrays.asList(1, 2, 2, 3); // duplikáció lehet
                Set<Integer> halmaz = new HashSet<>(lista); // duplikáció eltűnik
                Map<String, Integer> map = new HashMap<>(); // kulcs -> érték

                List<Integer> szamok = Arrays.asList(5, 10, 15, 20, 25, 10, 5);

                // Set használata (nagyon gyakori): duplikáció eltávolítása
                Set<Integer> egyedi = new HashSet<>(szamok); // ismétlődések eltávolítása
                System.out.println(egyedi);

                // String műveletek
                String szoveg = "alma körte banán";

                szoveg.length(); // hossz
                szoveg.toUpperCase(); // nagybetű
                szoveg.toLowerCase(); // kisbetű
                szoveg.contains("alma"); // tartalmazza-e
                szoveg.split(" "); // szétvágás

                String[] szavak = szoveg.split(" "); // szóköz mentén feldarabolás

                // Gyakori algoritmus: szavak számolása (HashMap + Stream)

                String szoveg2 = "alma körte alma banán alma";
                Map<String, Integer> szamlalo = new HashMap<>();

                for (String szo : szoveg2.split(" ")) {
                        szamlalo.put(szo, szamlalo.getOrDefault(szo, 0) + 1);
                }

                System.out.println(szamlalo);
                /*
                 * Eredmény: alma=3 körte=1 banán=1
                 */

                // Comparator (egyedi rendezés)
                List<String> nevek = Arrays.asList("Anna", "Béla", "Zoli");

                nevek.sort((a, b) -> a.length() - b.length()); // hossz szerint rendez

                // Optional használata
                Optional<Integer> szam = Optional.of(10);

                szam.isPresent(); // van-e érték
                szam.get(); // érték
                szam.orElse(0); // ha nincs érték

                // Lambda alapok
                // bemenet -> művelet
                // x -> x * 2

                List<Integer> szamok2 = Arrays.asList(5, 10, 15, 20, 25, 10, 5);

                szamok2.stream().map(x -> x * 2);

                // ArrayList -> gyors index
                // LinkedList -> gyors beszúrás
                // HashSet -> egyedi elemek
                // HashMap -> kulcs → érték

                // Exception kezelés
                try {
                        int a = 10 / 0;
                } catch (Exception e) {
                        System.out.println("Hiba történt");
                }

                /*
                 * OOP alapok: Encapsulation * Inheritance * Polymorphism * Abstraction
                 */
        }

        public void ArrayListFunkciok() {
                ArrayList<Operatorok2> lista = new ArrayList<>();

                // ^ Kiíratás. forEach+lambda
                lista.forEach(l -> System.out.println(l.getBal() + " " + l.getOperator() + " " + l.getJobb()));
        }

      
}