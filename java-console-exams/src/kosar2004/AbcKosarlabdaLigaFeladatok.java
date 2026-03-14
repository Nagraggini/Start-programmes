package kosar2004;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AbcKosarlabdaLigaFeladatok {
    // https://infojegyzet.hu/vizsgafeladatok/okj-programozas/szoftverfejleszto-200526/

    public static ArrayList<AbcKosarlabdaLiga> lista = new ArrayList<>();
    public static HashMap<Integer, AbcKosarlabdaLiga> hmap = new HashMap<>();

    public static void main(String[] args) {

        // 2. feladat:
        // Van amikor ez a jó: StandardCharsets.UTF_8
        // Van amikor ez a jó:Charset.forName("windows-1250")
        try {
            List<String> sorok = Files.readAllLines(Path.of("eredmenyek.csv"), Charset.forName("windows-1250"));

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

        // System.out.println("3. feladat: Real Madrid: Hazai: " + realDBHazai + " ,
        // Idegen: " + realDBIdegen);

        // 4. feladat:
        // Megszámoljuk, hogy volt-e döntetlen.
        long dontetlen = lista.stream(). // Adatfolyammá alakítás.
                filter(x -> x.getHazaiPont() == x.getIdegenPont()) // Szűrési feltétel.
                .count(); // Megszámoljuk.

        // A három operandust zárójelbe kell rakni. Ternális operátor.
        System.out.println("4. feladat: Volt-e döntetlen? " + ((int) dontetlen == 0 ? "nem" : "igen"));

        KosarligaGyakorlas gyakorlas = new KosarligaGyakorlas();
        // gyakorlas.kiiratasHashMap(hmap);
        // gyakorlas.kiiratasArrayList(lista);
        gyakorlas.melyikVarosbanHanyMeccsVolt(hmap);
        

    }

}
