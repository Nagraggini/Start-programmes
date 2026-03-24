package gameoop;

public class Mage extends Character {

    public Mage() {
        // A super() mindig az első sorban kell legyen
        super(); // A szülő osztály konstruktora. Ha nem írod ki, Java automatikusan beteszi.
        System.out.println("Mage konstruktora.");
        // Ezzel a super()-el bármelyik szülő metódus el lehet érni , nem csak a
        // konstruktort.
        super.tamad(); // A szülő osztály metódusát hívod meg. Általában nem szokás, ilyet meghívni itt.
    }

    @Override
    protected void specialisTamadas() {
        System.out.println("Tűzlabda!");
    }

}