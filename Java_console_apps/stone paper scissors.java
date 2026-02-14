import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException; // A try catch-hez kell ez az import.

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Valasztasi lehetosegeid: \n 0-ko; 1-papir; 2-ollo");
        
        int number = -1; // Kezdoertek, hogy hibas bemenet eseten is legyen ertek.
        
        // Szam bekerese a felhasznalótól
        System.out.println(" \n Add meg a valasztasodat (0, 1, 2): ");
        
        try {
            number = scanner.nextInt(); // Probalunk egy egesz szamot beolvasni.
                if (number != 0 && number != 1 && number != 2) {
                    /*Ez a feltetel akkor lesz igaz, ha a number erteke sem 0, sem 1, sem 2. Vagyis, ha a number barmilyen mas erteku, mint 0, 1, vagy 2, akkor a feltetel teljesül, es kiirja a lenti hibaüzenetet:*/
                    
                    System.out.println("Legy szíves, adj meg egy ervenyes szamot                                     (0, 1, 2).");
                    return; // Kilepünk, mert nem megfelelo szamot adott meg
                }
        } catch (InputMismatchException e) {
            System.out.println("Hiba: Szamot kellett volna megadni.");
            return; // Kilepünk, mert nem szamot adott meg a felhasznalo.
        } finally {
            scanner.close(); // Scanner lezarasa
                     
        }
               

            //a gep valasztasa
            Random rand = new Random();
            int randomNumber = rand.nextInt(3); // (0, 1, 2)

            System.out.println("A gep valasztasa: " + randomNumber);

            //Kiírom, hogy kinyert.
            if (number == randomNumber) {
                System.out.println("Dontetlen!");
            } else if (number == 0 && randomNumber == 1) {
                System.out.println("A gep nyert!");
            } else if (number == 1 && randomNumber == 2) {
                System.out.println("A gep nyert!");
            } else if (number == 2 && randomNumber == 0) {
                System.out.println("A gep nyert!");
            } else {
                System.out.println("Te nyertel!");
            }
            
        
    }
}
