package balkezesek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import static java.nio.file.Files.readAllLines;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BalkezesekFeladatok {

    public static void main(String[] args) {

        Path path = Path.of("java-console-exams/balkezesek.csv");

        if (!Files.exists(path)) {
            System.out.println("A fájl nem található.\n Itt keresem: " + System.getProperty("user.dir"));
            return; // Ha nincs fájl, ne is menjünk tovább.
        }

        ArrayList<Balkezesek> lista = new ArrayList<>();

        try {// Vs code-ban: Katt a Standard-ra, majd sárga körte ikon -> Surround statemnt
             // with
             // try-catch.
            List<String> sorok = readAllLines(path, Charset.forName("windows-1250"));

            // A 0. sorban az oszlopnevek vannak.
            for (int i = 1; i < sorok.size(); i++) {
                String[] t = sorok.get(i).split(";");

                lista.add(new Balkezesek(t[0], LocalDate.parse(t[1]), LocalDate.parse(t[2]), Integer.parseInt(t[3]),
                        Integer.parseInt(t[4])));

            }
        } catch (IOException ex) {
            System.getLogger(BalkezesekFeladatok.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        // 3. feladat
        System.out.println("3. feladat: " + lista.size());

        /*
         * Határozza meg, és írja ki a minta szerint, azoknak a játékosoknak a nevét és
         * testmagasságát centiméterben (1 inch = 2,54 cm), akik utoljára 1999
         * októberében
         * léptek pályára! Az eredményt egy tizedesjegyre kerekítve írja ki a
         * képernyőre!
         */

        // 4. feladat
        System.out.println("4. feladat: ");
        LocalDate hatarido1 = LocalDate.of(1999, 10, 1);
        LocalDate hatarido2 = LocalDate.of(1999, 10, 31);
        lista.stream().filter(x -> (x.getUtolso().isAfter(
                hatarido1)
                && x.getUtolso().isBefore(
                        hatarido2)))
                .forEach(x -> System.out.printf("        %s, %.1f cm%n", x.getNev(), (x.getMagassag() * 2.54)));

        /*
         * Kérjen be a felhasználótól egy évszámot a minta szerint! Az évszámra
         * teljesülni kell
         * az 1990 <= évszám <= 1999 feltételnek, amennyiben a felhasználó hibás
         * évszámot adott meg, írja ki a„Hibás adat, kérek egy 1990 és 1999 közötti
         * évszámot!"
         * hibaüzenetet és kérje be újra! Feltételezheti, hogy az inputadat
         * konvertálható pozitív
         * egész számra.
         */

        // 5. feladat
        menu();

        /*
         * Határozza meg és írja ki a minta szerint, mennyi az átlagsúlya a
         * játékosoknak, akik az
         * előző feladatban bekért évben pályára léptek! Az eredményt két tizedesjegyre
         * kerekítve írja ki a képernyőre! Feltételezheti, hogy az első és az utolsó
         * pályára lépés
         * dátuma között minden évben játszottak a játékosok. Ha az előző feladatot nem
         * tudta
         * megoldani, akkor dolgozzon az 1995-ös évvel!
         */

        // 6. feladat
        System.out.println("6. feladat: \n");

        // TODO
    }

    // 5. feladat.
    public static void menu() {

        System.out.print("5. feladat: \n");

        // try-with-resource
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            String bekertSor;

            LocalDate hatarido1 = LocalDate.of(1989, 12, 31);
            LocalDate hatarido2 = LocalDate.of(2000, 01, 01);

            while (true) {
                System.out.print("Kérek egy 1990 és 1999 közötti évszámot!:");

                bekertSor = br.readLine();

                int ev = Integer.parseInt(bekertSor);

                if (ev >= 1990 && ev <= 1999) {
                    break; // Kilépünk a ciklusból.
                } else {
                    System.out.print("Hibás adat!");
                }
            }
        } catch (IOException e) {
        }

    }
}
