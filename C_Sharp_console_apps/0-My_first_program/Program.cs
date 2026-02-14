using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace My_first_program
{
    internal class Program
    {
        static void Main(string[] args)
        {
            
            //Először a deklaráció típusát adjuk meg, majd a változó nevét, végül értéket adunk neki.

            //Szöveg típusok
            string nev = "Andi";
            char karakter = 'b';

            //Szám típusok
            int egesz = 2;
            double tortszam = 2.76;
            
            int Szam_1;
            int Szam_2;
            Console.Write("Kérek egy számot:"); //Console.WriteLine -> shortcut: cw + tab + tab (új sorba ír)

            //Két féle konvertálási módszer a konzolról beolvasott számra.
            Szam_1 = Convert.ToInt32(Console.ReadLine());
            //Szam=int.Parse(Console.ReadLine));

            Console.Write("Kérek egy számot:"); //Console.WriteLine -> shortcut: cw + tab + tab (új sorba ír)
            Szam_2 = Convert.ToInt32(Console.ReadLine());

            Console.WriteLine($"Összesen: {Szam_1+Szam_2}");

            //Program vár egy gombnyomásra, utána zárul be.
            Console.ReadKey(); 


           
        }
    }
}
