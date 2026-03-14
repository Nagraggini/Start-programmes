package versenyzokHaladobb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Versenyzok2 {

    private String nev;
    private LocalDate datum;
    private String nemzetiseg;
    private int rajtszam;

    public Versenyzok2(String sor) {

        String[] t = sor.split(";");

        this.nev = t[0];

        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        this.datum = LocalDate.parse(t[1], f);

        this.nemzetiseg = t[2];

        if (t.length == 4) {
            this.rajtszam = Integer.parseInt(t[3]);
        } else {
            this.rajtszam = -1;
        }
    }

    public String getNev() {
        return nev;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String getNemzetiseg() {
        return nemzetiseg;
    }

    public int getRajtszam() {
        return rajtszam;
    }
}
