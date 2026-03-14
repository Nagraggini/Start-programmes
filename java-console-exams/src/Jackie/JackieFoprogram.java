package Jackie;

import java.util.ArrayList;

/*Feladat:
https://infojegyzet.hu/vizsgafeladatok/okj-programozas/szoftverfejleszto-210511/ */

//A projekt gyökér könyvtára mellé hoztam létre a Jackie.txt-t.

public class JackieFoprogram {

    public static void main(String[] args) {

        // Használom az osztályt típusként, ahol az adatszerkezetek vannak megadva.
        ArrayList<Jackie> lista = new ArrayList<>();

        JackieFajlkezeles fajlkezeles = new JackieFajlkezeles();
        JackieFeladatok feladatok = new JackieFeladatok();

        // Feltöltjük a listát.
        fajlkezeles.fajlBeolvasas(lista, "java-console-exams/jackie.txt");
        feladatok.legtobbVerseny(lista);
        feladatok.evenkentiEredenyek(lista);
        fajlkezeles.htmlFajlLetrehozasEsFeltoltes(lista, "jackie.html");

    }
}
