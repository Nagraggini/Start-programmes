package src.forSanFranciscobolJottem;

import java.util.ArrayList;

//Ez alapján csináltam:
//https://www.youtube.com/watch?v=0Z5dwtD-dfk&list=PLyriihBWoulz2Eb3-FvL5bPXSQ-8poCNm
//Az osztálynevet mindig nagy betűvel kezd.
public class ElsoProject {

//Ökölszabály: Osztályon belül statikus metódusból nem hívhatunk meg nem statikus metódust.
    public static void main(String[] args) {
        ArrayList<Object> cats = new ArrayList<>();
        Dog morzsa = new Dog();
        Cat sziamiau = new Cat("Sziamiau");
        cats.add(sziamiau);
        cats.add(morzsa);

        Cat cat = (Cat) cats.get(0);

        //cats.get(1) egy Dog, nem lehet Cat típusra kasztolni.
        Cat cat2 = (Cat) cats.get(1);

        cat2.purr();
    }

}
