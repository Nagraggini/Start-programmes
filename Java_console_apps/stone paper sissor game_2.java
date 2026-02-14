import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        
        boolean playAgain = true; // A ciklus vezérlésére
        System.out.println("Valasztasi lehetosegeid: \n 0-ko; 1-papir; 2-ollo");
        
        while (playAgain) {
            int number = -1; // Alapérték hibás bemenet esetére
            
            // Szám bekérése a felhasználótól
            System.out.println("\nAdd meg a valasztasodat (0, 1, 2): ");
            
            try {
                number = scanner.nextInt(); // Próbáljunk egész számot beolvasni
                
                if (number != 0 && number != 1 && number != 2) {
                    System.out.println("Legy szíves, adj meg egy érvényes számot (0, 1, 2).");
                    continue; // Ha hibás a bemenet, folytatjuk a ciklust
                }
            } catch (InputMismatchException e) {
                System.out.println("Hiba: Számot kellett volna megadni.");
                scanner.nextLine(); // Bemenet törlése a következő próbálkozáshoz
                continue;
            }
            
            // A gép választása
            int randomNumber = rand.nextInt(3); // (0, 1, 2)
            System.out.println("A gép választása: " + randomNumber);
            
            // Eredmény meghatározása
            if (number == randomNumber) {
                System.out.println("Döntetlen!");
            } else if (number == 0 && randomNumber == 1 ||
                       number == 1 && randomNumber == 2 ||
                       number == 2 && randomNumber == 0) {
                System.out.println("A gép nyert!");
            } else {
                System.out.println("Te nyertél!");
            }
            
            // Megkérdezzük, hogy a játékos újra akar-e játszani
            System.out.println("\nSzeretnél újra játszani? (i/n): ");
            String response = scanner.next();
            
            if (!response.equalsIgnoreCase("i")) {
                playAgain = false;
                System.out.println("Köszönöm a játékot!");
            }
        }
        
        scanner.close(); // Scanner lezárása
    }
}
