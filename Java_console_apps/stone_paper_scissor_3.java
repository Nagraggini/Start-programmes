import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

/* Újítás egy-egy változóban tároljuk hány kört nyert a játékos és hányat a gép, az állást pedig minden kör végén kiírjuk!*/
    
public class Main {

    public static int result_user=0; //Felhasznalo allasa
    public static int  result_npc=0; //Gep allasa
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
                result_npc=result_npc+1;
            } else {
                System.out.println("Te nyertél!");
                result_user=result_user+1;
            }
            System.out.println("\n Eddig ennyit nyertél:"+result_user);
            System.out.println("\n Eddig ennyit nyert a gép:"+result_npc);
            
            // Megkérdezzük, hogy a játékos újra akar-e játszani
            System.out.println("\nSzeretnél újra játszani? (i/n): ");
            
            //Bekérjük a választ a felhasználótól.
            String response = scanner.next();

            if (response.equalsIgnoreCase("i")) {
                
                // Játék folytatása.
                playAgain = true;
                System.out.println("Köszönöm a játékot!");
                    
                } 
            else 
                {    //Nem szeretné folytatni a játékot.
                    playAgain = true;
                    
                    System.out.println("Tuti?");
                    response = scanner.next();
                    
                    if (response.equalsIgnoreCase("i")){
                        playAgain = false;
                        System.out.println("Köszönöm a játékot!");
                    }
                    else
                    {
                        playAgain = true;
                        System.out.println("\n Folytassuk! :) ");
                    }
                    
                
                }
                //Biztosan nem akar újra játszani?
                //Ha igen, akkor kilépünk a ciklusból
                
                

                
             
        }

        scanner.close(); // Scanner lezárása
    }
}
