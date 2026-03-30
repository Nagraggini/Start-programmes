package gameoop;

public class MainGame {

    public static void main(String[] args) {

        System.out.println("1");
        Character mage = new Mage(); // Példányosításkor egyből lefut a konstruktor!
        System.out.println("2");

        Character warrior = new Warrior(); // Példányosításkor egyből lefut a konstruktor!
        System.out.println("3");
        mage.tamad();

        System.out.println("4");
        warrior.tamad();
        /*
         * 1
         * Character osztály konstruktora.
         * Mage konstruktora.
         * Támadás indul...
         * Tűzlabda!
         * 2
         * Character osztály konstruktora.
         * 3
         * Támadás indul...
         * Tűzlabda!
         * 4
         * Támadás indul...
         * Kardcsapás!
         */
        // TODO oop gyakorlása és absztrakt osztály.
    }
}
