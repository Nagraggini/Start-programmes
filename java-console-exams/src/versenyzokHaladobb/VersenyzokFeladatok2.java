package versenyzokHaladobb;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://infojegyzet.hu/vizsgafeladatok/okj-programozas/szoftverfejleszto-210209/

// Tesztek:
// https://infojegyzet.hu/vizsgafeladatok/szoftverfejleszto-interaktiv/teszt/?tesztkod=K31G-MZYR

public class VersenyzokFeladatok2 {

    public static void main(String[] args) throws ParseException {

        List<Versenyzok2> emberek = new ArrayList<>();

        try {

            List<String> sorok = Files.readAllLines(Paths.get("java-console-exams/pilotak.csv"),
                    StandardCharsets.UTF_8);

            for (int i = 1; i < sorok.size(); i++) {
                emberek.add(new Versenyzok2(sorok.get(i)));
            }

        } catch (IOException e) {
            System.out.println("Hiba a fájl olvasásakor");
        }

        // 3. feladat
        System.out.println("3. feladat: " + emberek.size());

        // 4. feladat
        System.out.println("4. feladat: " + emberek.get(emberek.size() - 1).getNev());

        // 5. feladat
        System.out.println("5.feladat: ");

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy. MM. dd.");
        LocalDate cutoff = LocalDate.of(1901, 1, 1);

        for (Versenyzok2 ember : emberek) {
            LocalDate date = ember.getDatum(); // feltételezzük, hogy LocalDate típusú
            if (date.isBefore(cutoff)) {
                String formattedDate = date.format(outputFormatter);
                System.out.println(ember.getNev() + " (" + formattedDate + ")");
            }
        }

        // 6. feladat
        int min = Integer.MAX_VALUE;
        String nemzet = "";

        for (Versenyzok2 e : emberek) {
            if (e.getRajtszam() != -1 && e.getRajtszam() < min) {
                min = e.getRajtszam();
                nemzet = e.getNemzetiseg();
            }
        }

        System.out.println("6. feladat: " + nemzet);

        // 7. feladat
        System.out.print("7. feladat: ");

        Map<Integer, Integer> stat = new HashMap<>();

        for (Versenyzok2 e : emberek) {
            if (e.getRajtszam() != -1) {
                stat.put(e.getRajtszam(), stat.getOrDefault(e.getRajtszam(), 0) + 1);
            }
        }

        for (int r : stat.keySet()) {
            if (stat.get(r) > 1) {
                System.out.print(r + " ");
            }
        }
    }

}
