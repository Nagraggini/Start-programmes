package alapokGyakorlasa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MatematikaiFuggvenyek {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	

	public static void HomersekletElteres() throws IOException {

		System.out.println("Adj meg két város napi hőmérsékletét pontosvesszővel elválasztva:");
		String ertekek = br.readLine();
		String []homerseklet=ertekek.trim().split(";");
		double kulonbseg=Math.round(Math.abs(Double.parseDouble(homerseklet[0])-Double.parseDouble(homerseklet[1])));
	//
		
		System.out.println("A két hőmérséklet közötti eltérés abszolút értéke és egész számra kerekítve: "+ kulonbseg);
	}
}
