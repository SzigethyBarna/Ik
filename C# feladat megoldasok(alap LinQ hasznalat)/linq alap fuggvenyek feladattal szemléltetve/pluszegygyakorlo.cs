using System;
using System.Collections.Generic;
using System.ComponentModel.Design;
using System.Linq;


namespace negyedik
{
    internal class Program
    {
        static void Main()
        {
            int n= int.Parse(Console.ReadLine());
            var autok = new List<(string nev, double fogyasztas, int ar)>();
            for (int i = 0; i < n; i++) 
            {
                var s = Console.ReadLine().Split();
                autok.Add((s[0], double.Parse(s[1]),int.Parse(s[2])));
            }
            double atlagfogy = autok.Average(a => a.fogyasztas);
            double atlagar= autok.Average(a => a.ar);
            var atlagalatt = autok.Where(a => a.fogyasztas<atlagfogy).Select(a => a.nev);
            var legdragabb= autok.OrderByDescending(a=>a.ar).First();
            var sorrend= autok.OrderBy(a=>a.ar).Select(x => x.nev);
            Console.WriteLine($"Atlag fogyasztas: {atlagfogy:F2}");
            Console.WriteLine($"Atlag ar: {atlagar:F2}");
            Console.WriteLine("atlag alatt fogyaszto autok: " + string.Join(",", atlagalatt));
            Console.WriteLine("Legdragabb auto adatai: " + string.Join(",", legdragabb));
            Console.WriteLine("Ar szerint novekvo: " + string.Join(",", sorrend));

        }
    }
}
