package versenyzok;

import java.util.ArrayList;
import java.util.HashMap;

public class VersenyzokFeladatok {

    public void kilistazas(ArrayList<Versenyzok> lista) {

        for (Versenyzok versenyzok : lista) {
            System.out.println(versenyzok.toString());
        }
    }

    // Határozza meg és írja ki a minta szerint, hogy az állomány utolsó sorában
    // melyik pilóta neve szerepel!
    public void utolsoNevKiirasa(ArrayList<Versenyzok> lista) {

        Versenyzok utolsoVersenyzo = lista.get(lista.size() - 1);

        System.out.println("4. feladat: " + utolsoVersenyzo.getPilotaNeve());
    }

    /*
     * Határozza meg és írja ki a minta szerint, hogy mely pilóták születtek a XIX.
     * században
     * (azaz 1901. január 1-je előtt)! Feltételezheti, hogy a van olyan pilóta, aki
     * 1901 előtt
     * született. Írja ki a minta szerint a pilóták születési dátumát is!
     */
    // Minta: Lois Chiron (1899. 08. 03.)
    public void tizenkilencedikSzazadiPilotak(ArrayList<Versenyzok> lista) {

        // Ide mentjük el a leszűrt listát.
        ArrayList<Versenyzok> tizenkilencedikSzazadiPilotak = new ArrayList<>();

        for (Versenyzok versenyzok : lista) {

            String szulDatum = versenyzok.getSzuletesiDatuma();

            int szulEv = Integer.parseInt(szulDatum.substring(0, 4));
            int szulHonap = Integer.parseInt(szulDatum.substring(5, 7));
            int szulNap = Integer.parseInt(szulDatum.substring(8, 10));

            if (szulEv < 1901) {
                tizenkilencedikSzazadiPilotak.add(versenyzok);
            }
        }
        nevEsSzuletesiIdoKiiratasa(tizenkilencedikSzazadiPilotak);

    }

    public void nevEsSzuletesiIdoKiiratasa(ArrayList<Versenyzok> lista) {

        System.out.println("5. feladat: ");
        // Kiíratás
        for (Versenyzok versenyzok : lista) {
            String szulDatum = versenyzok.getSzuletesiDatuma();

            int szulEv = Integer.parseInt(szulDatum.substring(0, 4));
            int szulHonap = Integer.parseInt(szulDatum.substring(5, 7));
            int szulNap = Integer.parseInt(szulDatum.substring(8, 10));

            System.out.println(
                    "\t" + versenyzok.getPilotaNeve() + " (" + szulEv + ". " + szulHonap + ". " + szulNap + ".)");
        }
    }

    /*
     * Határozza meg, és írja ki a minta szerint, hogy a legkisebb értékű rajtszám
     * pilótájának
     * mi a nemzetisége! Ne vegye figyelembe a rajtszám nélküli pilóták adatait!
     * Feltételezheti, hogy a legkisebb értékű rajtszámot csak egy pilóta kapta meg
     * az
     * idényben!
     */
    public void legkisebbRajtszamuPilotaNemzetisege(ArrayList<Versenyzok> lista) {

        int legkisebbRajtszam = 1;

        // Egy reális értéket meg kell adni a rajtszámnak.
        for (Versenyzok versenyzok : lista) {

            if (!versenyzok.getRajtszam().equals("")) {
                legkisebbRajtszam = Integer.parseInt(versenyzok.getRajtszam());
            }
        }

        String legkisebbRajtszamuPilotaNemzetisege = null;

        // Végig megyünk foreach-el.
        for (Versenyzok versenyzok : lista) {

            // Lehet üres az értéke.
            if (!versenyzok.getRajtszam().equals("")) {
                if (Integer.parseInt(versenyzok.getRajtszam()) < legkisebbRajtszam) {

                    // Lementjük a legkisebb rajtszám értékét.
                    legkisebbRajtszam = Integer.parseInt(versenyzok.getRajtszam());
                    legkisebbRajtszamuPilotaNemzetisege = versenyzok.getNemzetiseg();
                    // System.out.println("\t !!!!" + versenyzok.getPilotaNeve());
                }
            }
        }
        System.out.println("6. feladat: " + legkisebbRajtszamuPilotaNemzetisege);

    }

    /*
     * Egy-egy rajtszámot több pilóta is megkaphat az idényben. Határozza meg és
     * írja ki a
     * minta szerint, hogy melyek ezek a rajtszámok!
     */
    public void tobbszorIsmetlodoRajtszamok(ArrayList<Versenyzok> lista) {

        // Ismétlődő rajtszámok keresése. key és value páros
        HashMap<Integer, Integer> rajtszamokEsElofordulasuk = new HashMap<>();

        for (Versenyzok v : lista) {

            // Lecsekkoljuk, hogy üres-e.
            if (v.getRajtszam() != null && !v.getRajtszam().isEmpty()) {
                int egyRajtszam = Integer.parseInt(v.getRajtszam());

                /*
                 * Egyik módszer.
                 * if (rajtszamokEsElofordulasuk.containsKey(egyRajtszam)) { // true-t ad
                 * vissza, ha létezik.
                 * // Van a listában, megkeressük és növeljük az értékét.
                 * rajtszamokEsElofordulasuk.put(
                 * egyRajtszam,
                 * rajtszamokEsElofordulasuk.get(egyRajtszam) + 1);
                 * } else {
                 * // Nincs a listában.
                 * rajtszamokEsElofordulasuk.put(egyRajtszam, 1);
                 * }
                 */

                // Másik módszer.
                rajtszamokEsElofordulasuk.put(
                        egyRajtszam,
                        rajtszamokEsElofordulasuk.getOrDefault(egyRajtszam, 0) + 1);
                /*
                 * getOrDefault() jelentése:
                 * ha létezik a kulcs → visszaadja az értéket
                 * ha nem létezik → visszaadja az alapértéket
                 */
            }
        }

        // Csak a rajtszámokat írjuk ki.
        System.out.print("7. feladat: "); // Minta: 7.feladat: 88, 99, 10, 4, 28

        boolean elso = true;
        for (Integer key : rajtszamokEsElofordulasuk.keySet()) {

            if (rajtszamokEsElofordulasuk.get(key) > 1) {
                if (!elso) {
                    System.out.print(", ");
                }
                System.out.print(key);
                elso = false;
            }
        }
        System.out.println();
        // for (Integer key : rajtszamokEsElofordulasuk.keySet()) {
        // System.out.println("key: " + key + " -> " + " value: " +
        // rajtszamokEsElofordulasuk.get(key));
        // }

    }
}
