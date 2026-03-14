package vizibicikliKolcsonzo;

import java.util.ArrayList;

public class VizibicikliKolcsonzesFoprogram {
    public static void main(String[] args) {
        System.out.println("3. feladat");

        // Használom az osztályt típusként, ahol az adatszerkezetek vannak megadva.
        ArrayList<Kolcsonzes> lista = new ArrayList<>();

        // Példányosítás.
        VizibicikliKolcsonzoFajlkezeles fajlBeolvasas = new VizibicikliKolcsonzoFajlkezeles();
        FeladatokBicikli feladatok = new FeladatokBicikli();
        try {
            // fajlBeolvasas.tesztAdatFeltoltese(lista);
            fajlBeolvasas.fajlBeolvasasa("java-console-exams/kolcsonzesek.txt", lista);
            // feladatok.kiListazas(lista);
            System.out.println("5. feladat: Napi kölcsönzések száma: " + lista.size());

            feladatok.keresesNevAlapjan(lista);
            feladatok.idopontAlapjanTortenoKilistazas(lista);// 10:9
            feladatok.napiBevetel(lista);
            fajlBeolvasas.gyanusitottakKiiratasaFajlba(feladatok.jarmuRongalas(lista, "F"));
            feladatok.statisztika(lista);

        } catch (Exception e) {
            System.out.println("Hibás input!");
        }
    }
}
