package alapokGyakorlasa;

public class Matekfuggvenyek {

	public static void matekFuggvenyek() {

		System.out
				.println("ceil: Visszaadja a legkisebb egész számot (double formában), ami nem kisebb, mint az érték.\n"
						+ "Negatív számnál a felfelé kerekítés → az érték kevésbé negatív lesz.\n");
		System.out.println("6.31 -> " + Math.ceil(6.31)); // 7.0
		System.out.println("6.99 -> " + Math.ceil(6.99)); // 7.0
		System.out.println("-6.3 -> " + Math.ceil(-6.3)); // -6.0
		System.out.println("-6.99 -> " + Math.ceil(-6.99)); // -6.0

		System.out.println(
				"\nfloor: Visszaadja a legnagyobb egész számot (double formában), ami nem nagyobb, mint az érték.\n"
						+ "Negatív számnál lefelé kerekít → az érték kisebb lesz.\n");
		System.out.println("6.99 -> " + Math.floor(6.99)); // 6.0
		System.out.println("6.01 -> " + Math.floor(6.01)); // 6.0
		System.out.println("-6.01 -> " + Math.floor(-6.01)); // -7.0
		System.out.println("-6.99 -> " + Math.floor(-6.99)); // -7.0
	}
}
