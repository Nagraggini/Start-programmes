package hasznosInfok;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApikGrouping {

    record Felhasznalo(String nev) {
    }

    public static void main(String[] args) {
        List<Felhasznalo> lista = Arrays.asList(
                new Felhasznalo("Andrea"), new Felhasznalo("Béla"),
                new Felhasznalo("Andrea"), new Felhasznalo("Andrea"),
                new Felhasznalo("Andrea"), new Felhasznalo("Béla"));

        System.out.println("Szűrt eredmények:");

        lista.stream()
                // 1. Csoportosítás és számlálás (létrejön egy Map a háttérben)
                .collect(Collectors.groupingBy(Felhasznalo::nev, Collectors.counting()))
                // 2. Belépünk a Map bejegyzéseibe
                .entrySet().stream()
                // 3. Szűrés: csak a 3-nál nagyobb előfordulások
                .filter(entry -> entry.getValue() > 3)
                // 4. Azonnali kiíratás tárolás nélkül
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue() + "-szor szerepel a listában"));

    }

}
