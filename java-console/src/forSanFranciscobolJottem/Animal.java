package src.forSanFranciscobolJottem;

//Minden objektum, amit létrehozol automatikusan kiterjeszto az Object osztályt, ezért van már alapjáratom egy rakás metódusa az osztályodnak.
abstract class Animal {

    private String name;
    private int weight;

    //A protected metódusokat, csak az örökléssel létrehozott osztályok használhatják. 
    protected void makeSound() {
        System.out.println("AAAA");
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
