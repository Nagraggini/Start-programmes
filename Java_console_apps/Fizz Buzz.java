import java.util.Scanner;

public class Main {

  /*„FizzBuzz” kódolási kihívás, ahol a 3-mal osztható számnál „Fizz”, az 5-tel osztható számnál „Buzz”, a kettővel osztható számnál pedig „FizzBuzz” szót írja ki a program.*/
  
  public static void main(String[] args) {
  
  Scanner scanner = new Scanner(System.in);

    // Asking for an integer input
  System.out.print("Adj meg egy számot: ");
   
    // Asking for an integer input with validation
    int num = 0;
    while (true) {
        if (scanner.hasNextInt()) {
            num = scanner.nextInt();
            break;
        } else {
            System.out.println("Egy valid számot adj meg.");
            scanner.next(); // discard invalid input
        }
    }
    
  if (num % 3 == 0 && num % 5 == 0) // osztható 3-mal és 5-tel
  {
    System.out.println("FizzBuzz");
  } else if (num %3 == 0) // osztható 3-al.
  {
    System.out.println("Fizz");
  } else if (num %5 == 0){
    System.out.println("Buzz");
  } 
    scanner.close();
  }
}

