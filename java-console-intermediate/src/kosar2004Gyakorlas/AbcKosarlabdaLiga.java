package kosar2004Gyakorlas;

import java.time.LocalDate;

public class AbcKosarlabdaLiga {

    // Adat tagok
    private String hazai;
    private String idegen;
    private int hazaiPont;
    private int idegenPont;
    private String helyszin;
    private LocalDate idopont;

    // Konstruktor
    public AbcKosarlabdaLiga(String hazai, String idegen, int hazaiPont, int idegenPont, String helyszin,
            LocalDate idopont) {
        this.hazai = hazai;
        this.idegen = idegen;
        this.hazaiPont = hazaiPont;
        this.idegenPont = idegenPont;
        this.helyszin = helyszin;
        this.idopont = idopont;
    }

    // Getterek
    public String getHazai() {
        return hazai;
    }

    public String getIdegen() {
        return idegen;
    }

    public int getHazaiPont() {
        return hazaiPont;
    }

    public int getIdegenPont() {
        return idegenPont;
    }

    public String getHelyszin() {
        return helyszin;
    }

    public LocalDate getIdopont() {
        return idopont;
    }

    @Override
    public String toString() {
        return hazai + " - " + idegen + " eredmény: " + hazaiPont + " : " + idegenPont + " helyszín: " + helyszin
                + " időpont: " + idopont;
    }

}