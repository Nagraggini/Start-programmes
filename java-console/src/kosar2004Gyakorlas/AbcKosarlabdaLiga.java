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
        StringBuilder sb = new StringBuilder();
        sb.append("AbcKosarlabdaLiga{");
        sb.append("hazai=").append(hazai);
        sb.append(", idegen=").append(idegen);
        sb.append(", hazai_pont=").append(hazaiPont);
        sb.append(", idegen_pont=").append(idegenPont);
        sb.append(", helyszin=").append(helyszin);
        sb.append(", idopont=").append(idopont);
        sb.append('}');
        return sb.toString();
    }

}
