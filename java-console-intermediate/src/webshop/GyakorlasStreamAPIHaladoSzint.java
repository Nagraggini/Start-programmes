package webshop;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GyakorlasStreamAPIHaladoSzint {
    // puska: https://github.com/Nagraggini/blog/blob/main/Java_basic_knowledge.md

    private static ArrayList<Order> lista = new ArrayList<>();

    public static void main(String[] args) {

        Fajlbeolvasasa("java-console-intermediate/webshopdata.csv");

    }

    // TODO later fájlbeolvasása

    public static void Fajlbeolvasasa(String fajlneve) {
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
            List<String> sorok = Files.readAllLines(path, StandardCharsets.UTF_8);

            // 1-től megyünk, mert van oszlopnév is.
            for (int i = 1; i < sorok.size(); i++) {
                String[] t = sorok.get(i).split(";");
                /*
                 * public Order(String orderId, String customerName, LocalDate orderDate,
                 * OrderStatus status, List<OrderItem> items, String country)
                 */
                // Mivel a CSV-ben valószínűleg nincs benne az összes OrderItem,
                // itt egy üres listát adunk át, vagy dummy adatokat a teszteléshez.
                lista.add(new Order(
                        t[0],
                        t[1],
                        LocalDate.parse(t[2]),
                        OrderStatus.valueOf(t[3]), // Feltételezve, hogy a 4. oszlop a státusz
                        new ArrayList<>(), // Itt kellene lennie a tételeknek
                        t[5]));
            }
        } catch (IOException ex) {
            System.getLogger(Order.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        lista.stream().forEach(x -> System.out.println(x.toString()));
    }

    /*
     * TODO later
     * Ez egy kiváló alap! Az Order és OrderItem struktúra lehetővé teszi, hogy
     * túllépjünk az egyszerű szűréseken, és bonyolultabb adatelemzési feladatokat
     * oldjunk meg.
     * 
     * Íme 5 haladó szintű kihívás, amik lefedik a cikkben említett összes komplex
     * Collector technikát:
     * 
     * 1. Feladat: Országonkénti forgalom elemzése
     * Készíts egy statisztikát, amely megmutatja, hogy országonként (country)
     * mekkora a teljes bevétel. A végeredmény egy Map<String, Double> legyen.
     * 
     * Tipp: Használd a groupingBy-t egy beágyazott summingDouble collectorral.
     * 
     * 2. Feladat: A legnépszerűbb termékkategória
     * Határozd meg, melyik termékkategóriából (Category) adták el a legtöbb darabot
     * (összesített darabszám alapján).
     * 
     * Tipp: Itt szükséged lesz a flatMap-re az elején, hogy a rendelésekből eljuss
     * a tételekig, majd egy groupingBy és summingInt kombinációra.
     * 
     * 3. Feladat: Prémium vásárlók azonosítása
     * Válaszd szét a rendeléseket két csoportra (Partitioning): azokra, amelyek
     * tartalmaznak 500 eurónál drágább egyedi tételt, és azokra, amelyek nem.
     * 
     * Tipp: A partitioningBy lesz a barátod, a feltételben pedig egy anyMatch
     * predikátumot használj a tételekre.
     * 
     * 4. Feladat: Komplex jelentés készítése (Multi-level grouping)
     * Hozz létre egy olyan adatszerkezetet, amely országonként, azon belül pedig
     * státuszonként (OrderStatus) csoportosítja a rendelések azonosítóit (orderId).
     * 
     * Tipp: Ez egy "nested grouping" feladat: groupingBy(Order::getCountry,
     * groupingBy(Order::getStatus, mapping(...))).
     * 
     * 5. Feladat: "A hónap dolgozója" analógia – Legértékesebb rendelés keresése
     * Keresd meg minden egyes országhoz a legnagyobb összértékű rendelést. A
     * végeredmény egy Map<String, Optional<Order>> legyen.
     * 
     * Tipp: Használd a groupingBy-t a maxBy collectorral kombinálva, ahol a
     * komparátor a Order::getTotalAmount metódust használja.
     * 
     */
}
