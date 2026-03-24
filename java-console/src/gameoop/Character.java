package gameoop;

//abstract class = sablon, amit örökölnek
// Nem lehet példányosítani.
abstract class Character {

    public Character() {
        System.out.println("Character osztály konstruktora.");
    }

    public void tamad() {
        System.out.println("Támadás indul...");
        specialisTamadas(); //Polimorfizmus, a leszármazott metódusát hívja meg.
    }

    // Kötelezően felül kell írni a leszármazott osztályokban.
    // Ugyanabban a csomagban + leszármazott osztályok férnek hozzá.
    protected abstract void specialisTamadas();
}
