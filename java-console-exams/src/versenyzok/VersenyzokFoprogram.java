package versenyzok;

import java.util.ArrayList;

//Feladatok:
// https://infojegyzet.hu/vizsgafeladatok/okj-programozas/szoftverfejleszto-210209/

public class VersenyzokFoprogram {

    public static void main(String[] args) {

        ArrayList<Versenyzok> lista = new ArrayList<>();

        VersenyzokFajlkezeles fajlkezeles = new VersenyzokFajlkezeles();
        fajlkezeles.fajlBeolvasas(lista, "java-console-exams/pilotak.csv");

        VersenyzokFeladatok feladatok = new VersenyzokFeladatok();

        // feladatok.kilistazas(lista);

        System.out.println("3. feladat: " + lista.size());

        feladatok.utolsoNevKiirasa(lista);
        feladatok.tizenkilencedikSzazadiPilotak(lista);
        feladatok.legkisebbRajtszamuPilotaNemzetisege(lista);
        feladatok.tobbszorIsmetlodoRajtszamok(lista);
    }
}
