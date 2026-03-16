package alapokGyakorlasa;

import java.util.ArrayList;

public class ArrayListHasznalata {

    public static void arrayHasznalata() {
        String[] simpleArray0 = new String[2];
        simpleArray0[0] = "alma";
        simpleArray0[1] = "körte";

        // A tömb statikus, ha törölsz belőle egy elemet, akkor sem megy össze.
        ArrayList<String> list = new ArrayList<>();

        // Az array tudja változtatni a méretét.
        list.add("alma");
        list.add("körte");

        list.remove(0);
        System.out.println("0. elem: " + list.get(0));
        System.out.println(list.size());
    }
}
