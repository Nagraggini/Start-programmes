package versenyzok;

public class Versenyzok {

    // Adattagok, melyek a csv fájl oszlopai.
    public String pilotaNeve;
    public String szuletesiDatuma;
    public String nemzetiseg;
    public String rajtszam; // Lehet, hogy üres lesz a mező értéke.
    // Csak az aktuális évben aktív pilótáknak van rajtszámuk, a többiek esetében a
    // rajtszám mező értéke üres. Több pilótának is lehet azonos a rajtszáma.

    // Konstruktor. Sorrendre figyelj!

    public Versenyzok(String pilotaNeve, String szuletesiDatuma, String nemzetiseg, String rajtszam) {
        this.nemzetiseg = nemzetiseg;
        this.pilotaNeve = pilotaNeve;
        this.rajtszam = rajtszam;
        this.szuletesiDatuma = szuletesiDatuma;
    }

    // Elég nekünk, csak a getterek.
    public String getPilotaNeve() {
        return pilotaNeve;
    }

    public String getSzuletesiDatuma() {
        return szuletesiDatuma;
    }

    public String getNemzetiseg() {
        return nemzetiseg;
    }

    public String getRajtszam() {
        return rajtszam;
    }

    // Csak a kilistázáshoz.
    @Override
    public String toString() {
        return "Versenyzok [pilotaNeve=" + pilotaNeve + ", szuletesiDatuma=" + szuletesiDatuma + ", nemzetiseg="
                + nemzetiseg + ", rajtszam=" + rajtszam + "]";
    }

    

}
