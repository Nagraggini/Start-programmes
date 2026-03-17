package kosar2004Gyakorlas;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GyakorlasSteamAPIKezdoSzint {

    // https://infojegyzet.hu/vizsgafeladatok/okj-programozas/szoftverfejleszto-200526/

    // Puska: https://github.com/Nagraggini/blog/blob/main/Java_basic_knowledge.md

    public static ArrayList<AbcKosarlabdaLiga> lista = new ArrayList<>();
    public static HashMap<Integer, AbcKosarlabdaLiga> hmap = new HashMap<>();

    /*
     * public static void main(String[] args) {
     * 
     * Fajlbeolvasasa("java-console-exams/eredmenyek.csv");
     * 
     * System.out.println("Sorok száma: " + lista.size()); //
     * nagyFolennyelNyertek(lista, 3); // hazaiGyozelmek(lista); //
     * bekertCsapatnevAlapjanSzur(lista, "Real Madrid"); // hazaiCsapatnevek(lista);
     * // idegenCsapatnevek(lista); // hazaCsapatSzaznalTobbPontotErtEl(lista); //
     * idegenCsapatBekertErteknelTobbetSzerzett(lista, 90);
     * hazaiCsapatKevesebbetDobottMintAMasik(lista);
     * bekertertekPontFelettiMeccsek(lista, 110); }
     */

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

    public static void nagyFolennyelNyertek(ArrayList<AbcKosarlabdaLiga> lista, int kulonbseg) {

        // A pontszámok közti különbség alapján szűrünk és megszámoljuk hány db ilyen
        // meccs volt.
        System.out.println(lista.stream()
                // Csak azokat hagyjuk meg, ahol a különbség pontosan ennyi
                .filter(l -> Math.abs(l.getIdegenPont() - l.getHazaiPont()) == kulonbseg)
                // Megszámoljuk hány ilyen meccs volt.
                .count() + " db olyan meccs volt, amiknél a pont különbség " + kulonbseg + " pont volt.");
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
        long hazaiMeccsek = lista.stream().filter(a -> a.getHazai().equals("Real Madrid")).count();

        long idegenMeccsek = lista.stream().filter(a -> a.getIdegen().equals("Real Madrid")).count();

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

    public static void bekertCsapatnevAlapjanSzur(ArrayList<AbcKosarlabdaLiga> lista, String csapatnev) {

        System.out.println(
                "Ki szűrjük az összes meccset, ahol a bekért csapat játszott hazai- vagy idegenként, külön-külön mecs darab számra.");

        long hazaiDB = lista.stream().filter(x -> x.getHazai().equals(csapatnev)).count();
        long idegenDB = lista.stream().filter(x -> x.getIdegen().equals(csapatnev)).count();

        System.out.println(csapatnev + " hazai csapatként " + hazaiDB + " db meccset játszott, idegenként, pedig "
                + idegenDB + "-szer/szor.");
    }

    public static void hazaiCsapatnevek(ArrayList<AbcKosarlabdaLiga> lista) {

        System.out.println("Itt a lista az összes hazai csapat nevével, ismétlődés nélkül:");

        lista.stream().map(x -> x.getHazai()).distinct().forEach(x -> System.out.println(x));

    }

    public static void idegenCsapatnevek(ArrayList<AbcKosarlabdaLiga> lista) {

        System.out.println("Az összes idegen csapat neve, ismétlődés nélkül:");

        lista.stream().map(x -> x.getIdegen()).distinct().forEach(x -> System.out.println(x));
    }

    public static void helyszinek(ArrayList<AbcKosarlabdaLiga> lista) {

        System.out.println("Itt a lista az összes helyszínnel, ismétlődés nélkül:");

        lista.stream().map(x -> x.getHelyszin()).distinct().forEach(x -> System.out.println(x));

    }

    public static void hazaCsapatSzaznalTobbPontotErtEl(ArrayList<AbcKosarlabdaLiga> lista) {

        System.out.println("Ennyi darab meccsen dobott a hazai csapat 100 pontnál többet: "
                + lista.stream().filter(x -> x.getHazaiPont() > 100).count());
    }

    public static void idegenCsapatBekertErteknelTobbetSzerzett(ArrayList<AbcKosarlabdaLiga> lista,
            int ennelTobbPontotSzerzett) {

        long meccsDB = lista.stream().filter(x -> (int) x.getIdegenPont() > ennelTobbPontotSzerzett).count();
        System.out.println(
                meccsDB + " db meccsen dobott az idegen csapat többet, mint " + ennelTobbPontotSzerzett + " pont.");
    }

    public static void hazaiCsapatKevesebbetDobottMintAMasik(ArrayList<AbcKosarlabdaLiga> lista) {

        System.out.println("Ezeken meccseken dobott a hazai csapat kevesebb pontot, mint az ellenfél: ");
        lista.stream().filter(x -> x.getHazaiPont() < x.getIdegenPont())
                .forEach(x -> System.out.println(x.getIdopont()));

        System.out.println(
                "Összesen " + lista.stream().filter(x -> x.getHazaiPont() < x.getIdegenPont()).count() + " db.");
    }

    public static void bekertertekPontFelettiMeccsek(ArrayList<AbcKosarlabdaLiga> lista, int bekertertek) {

        // 1. Szűrés és gyűjtés listába
        List<AbcKosarlabdaLiga> talalatok = lista.stream()
                .filter(x -> x.getHazaiPont() > bekertertek || x.getIdegenPont() > bekertertek)
                .collect(Collectors.toList());

        // 2. Ellenőrzés: Üres-e?
        if (!talalatok.isEmpty()) {
            // 3. Ha nem üres, jöhet a kiírás
            System.out.println(bekertertek + " pont feletti meccsek időpontjai: ");
            talalatok.forEach(x -> System.out.println("Helyszín: " + x.getHelyszin() + " időpont: " + x.getIdopont()));
        } else {
            System.out.println(
                    "Nincs olyan meccs, amin bármelyik csapat " + bekertertek + " pontnál többet ért volna el.");
        }
    }

}