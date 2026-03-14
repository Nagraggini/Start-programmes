package operatorok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperatorokFeladatok {

    // https://infojegyzet.hu/vizsgafeladatok/okj-programozas/szoftverfejleszto-201006/
    // Tesztek:
    // https://infojegyzet.hu/vizsgafeladatok/szoftverfejleszto-interaktiv/teszt/?tesztkod=K31G-MZYR

    // 1. feladat
    public static ArrayList<Operatorok> lista = new ArrayList<>();

    public static void main(String[] args) {

        Path path = Path.of("java-console-exams/kifejezesek.txt");

        // Ellenőrzés és beolvasás egyben
        if (!Files.exists(path)) {
            System.out.println("Nem létezik a fájl!");
            System.out.println("Itt keresem: " + System.getProperty("user.dir"));
            return; // Ha nincs fájl, ne is menjünk tovább a try-ra
        }

        try {
            List<String> sorok = Files.readAllLines(path, StandardCharsets.UTF_8);

            // Itt mehetünk 0-túl, mert nincs oszlopnév.
            for (int i = 0; i < sorok.size(); i++) {
                String[] t = sorok.get(i).split(" ");

                lista.add(new Operatorok(Integer.parseInt(t[0]), t[1], Integer.parseInt(t[2])));
            }
        } catch (IOException ex) {
            System.getLogger(OperatorokFeladatok.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        // ^ Kiíratás. forEach+lambda
        // lista.forEach(l -> System.out.println(l.getElsoAdat() + " " + l.getOperator()
        // + " " + l.getMasodikAdat()));

        // 2. feladat
        System.out.println("2. feladat: Kifejezések száma: " + lista.size());

        // 3 . feladat
        /*
         * A maradékos osztás operátorát a mod szóval jelöltük az állományban. Határozza
         * meg és írja a képernyőre a maradékos osztást tartalmazó kifejezések számát!
         */

        int[] szamlalo = { 0 }; // A lambdához kell egy final változó.

        // Lambda + if elágazás (void forEach-hez, a három operandusúnál kell érték a
        // hamis ághoz is.)
        lista.forEach(l -> {
            if (l.getOperator().equals("mod")) {
                szamlalo[0]++; // növeljük a tömb első elemét
            }
        });

        System.out.println("3. feladat: Kifejezések maradékos osztással: " + szamlalo[0]);

        // 4. feladat
        /*
         * Döntse el, hogy a forrásállományban található-e olyan kifejezés, amelyben
         * mindkét operandus maradék nélkül osztható tízzel! Az eldöntés eredményét írja
         * a képernyőre! A keresést ne folytassa, ha a választ meg tudja adni!
         */

        /*
         * //Oldschool megoldás for (Operatorok o : lista) { if (o.getElsoAdat() % 10 ==
         * 0 && o.getMasodikAdat() % 10 == 0) {
         * System.out.println("4. feladat: Van ilyen kifejezés!"); break; } }
         */

        // Modernebb megoldás. stream() és lambda
        boolean vanIlyen = lista.stream().anyMatch(l -> l.getElsoAdat() % 10 == 0 && l.getMasodikAdat() % 10 == 0);

        if (vanIlyen) {
            System.out.println("4. feladat: Van ilyen kifejezés!");
        } else {
            System.out.println("4. feladat: Nincs ilyen kifejezés!");
        }

        // 5. feladat

        /*
         * Az egész osztás operátorát a div szóval jelöltük az állományban. Készítsen
         * statisztikát az összeadás (+), kivonás (-), szorzás (*), valós osztás (/),
         * egész osztás (div) és maradékos osztás (mod) operátorokat tartalmazó
         * kifejezések számáról!
         */

        // Operatorok osztály: elsoAdat, operator, masodikAdat

        HashMap<String, Integer> stat = new HashMap<>();

        for (Operatorok op : lista) {
            stat.put(op.getOperator(), stat.getOrDefault(op.getOperator(), 0) + 1);
        }
        System.out.println("5. feladat: Statisztika");

        // Kiiratás.
        for (String key : stat.keySet()) {

            if (key.equals("+") || key.equals("-") || key.equals("*") || key.equals("/") || key.equals("div")
                    || key.equals("mod")) {
                System.out.printf("%10s -> %d db%n", key, stat.get(key));
            }

        }

        // 6 és 7. feladat
        menu();

        // 8. feladat
        // eredmenyKiirasa(lista);

    }
    // 6. feladat

    /*
     * Készítsen szöveges típusú adattal visszatérő függvényt, metódust vagy
     * jellemzőt a kifejezés értékének meghatározására! A függvény az előző
     * feladatban felsorolt operátorokat tudja kezelni, ismeretlen operátor esetén
     * térjen vissza a „Hibás operátor!« üzenettel! Helyes operátor esetén sem lehet
     * egy kifejezés értékét mindig meghatározni (pl. túlcsordulás, nullával való
     * osztás stb.), ilyen esetben a függvény térjen vissza az ,Egyéb hiba!"
     * üzenettel!
     */

    /*
     * összeadás (+), kivonás (-), szorzás (*), valós osztás (/), egész osztás (div)
     * és maradékos osztás (mod) operátorok
     */

    public static String bekertKif(String bekertKifejezes) {

        try {
            String[] bekertAdat = bekertKifejezes.split(" ");

            int a = Integer.parseInt(bekertAdat[0]);
            int b = Integer.parseInt(bekertAdat[2]);
            String operator = bekertAdat[1];

            switch (operator) {

                case "+":
                    return String.valueOf(a + b);

                case "-":
                    return String.valueOf(a - b);

                case "*":
                    return String.valueOf(a * b);

                case "/": // valós osztás, az egyiknek double-nek kell lennie, hogy double legyen az
                          // eredménye
                          // 15 db tizedesjegyig
                          // pl.: 3 / 7 = 0.428571428571429
                    if (b == 0) {
                        return "Egyéb hiba!";
                    }
                    return String.format("%.15f", (double) a / b);

                case "div": // egész osztás, egész lesz az eredmény
                    if (b == 0) {
                        return "Egyéb hiba!";
                    }
                    return String.valueOf(a / b);

                case "mod": // maradékos osztás
                    if (b == 0) {
                        return "Egyéb hiba!";
                    }
                    return String.valueOf(a % b);

                default:
                    return "Hibás operátor!";
            }

        } catch (Exception e) {
            return "Egyéb hiba!";
        }
    }

    // 7. feladat

    /*
     * Kérjen be a felhasználótól egy kifejezést, amiről feltételezheti, hogy a
     * forrás állományban lévő kifejezések leírásának megfelel! Határozza meg az
     * előző feladatban definiált függvény felhasználásával a kifejezés értékét,
     * majd írja ki azt a minták szerint! A feladatot ismételje a,,vége" inputig!
     */

    public static void menu() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            String bekertSor;

            while (true) {
                System.out.print("7. feladat: Kérek egy kifejezést (pl.: 1 + 1): ");
                bekertSor = br.readLine();

                // System.out.println("DEBUG:[" + bekertSor + "]");

                // Kilépés a "vége" szóra
                if (bekertSor.equalsIgnoreCase("v�ge") || bekertSor.equalsIgnoreCase("vége")) {
                    break; // Kilépés a ciklusból.
                }

                System.out.println("\t" + bekertSor + " = " + bekertKif(bekertSor));

            }
        } catch (IOException e) {
        }

        // System.out.println("Kilépés a menüből.");
        eredmenyKiirasa(lista);

    }

    // 8. feladat

    /*
     * Készítsen szöveges állományt eredmenyek.txt néven a minta szerint, melyben
     * meghatározza a forrásállományban lévő kifejezések eredményeit!
     */

    public static ArrayList eredmenyKiirasa(ArrayList<Operatorok> lista) {
        /*
         * for (Operatorok op : lista) { bekertKif(op.toString()); }
         */
        // lista.forEach(l -> System.out.println(l.getElsoAdat() + " " + l.getOperator()
        // + " " + l.getMasodikAdat()));

        try {

            // 3️⃣ Lista kiírása fájlba Stream segítségével
            Files.write(Path.of("eredmenyek.txt"), // fájl neve
                    lista.stream() // listából stream
                            .map(o -> bekertKif(o.toString()))
                            // objektum -> String
                            .toList(), // Stream -> List<String>
                    StandardCharsets.UTF_8 // karakterkódolás
            );

            System.out.println("8. feladat: eredmeny.txt");

        } catch (IOException e) {
            System.out.println("Hiba történt a fájl írásakor.");
        }
        return lista;
    }
}
