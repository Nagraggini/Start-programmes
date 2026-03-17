package src.forSanFranciscobolJottem;

public class Cat extends Animal {

    //Konstruktor, ha nem hozod létre, akkor az IDE automatikusan létrehozza.
    //Amikor létre jön az objektum, akkor automatikusan lefut. 
    public Cat() {
        //Az Animal konstruktorát hívja meg.

        //Az interfész metódusát is meg lehet hívni.
        // super.makeSound();
    }

    public Cat(String name) {
        this.setName(name);
    }

    public void purr() {
        System.out.println("Dorombolok.");
    }

    public Cat(String name,
            int weight
    ) {
        this.setName(name);
        this.setWeight(weight);

    }

    @Override //Felülírás.
    public void makeSound() {
        System.out.println("MEOW!");
    }

}
