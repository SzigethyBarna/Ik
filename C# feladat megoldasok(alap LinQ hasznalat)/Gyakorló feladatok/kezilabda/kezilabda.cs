using System;
using System.Collections.Generic;
using System.Linq;


namespace Kezilabda
{
    class Program
    {
        struct Gollovo
        {
            public string orszag;
            public int lovesek;
            public int goal;
            public int lovhat;
        };
        static void Main(string[] args)
        {
            string[] sor=Console.ReadLine().Split(' ');
            int N = int.Parse(sor[0]);
            string M = sor[1];
            Gollovo[] lista = new Gollovo[N];
            for (int i = 0; i < N; i++) {
                lista[i]=new Gollovo();
                string[] adat = Console.ReadLine().Split(' ');
                lista[i].orszag = adat[0];
                lista[i].lovesek = int.Parse(adat[1]);
                lista[i].goal = int.Parse(adat[2]);
                lista[i].lovhat = int.Parse(adat[3]);

            }
            Console.WriteLine("#");
            int golszam = -1;
            for (int i = 0; i < N; ++i) {
                golszam = lista[i].goal;
                if (golszam > 100)
                {
                    Console.WriteLine(i+1);
                    break;
                }
            }
            if (golszam <100)
            {
                Console.WriteLine(-1);
            }
            Console.WriteLine("#");
            int lovesszam = 0;
            for (int i = 0; i < N; ++i)
            {
                if (M == lista[i].orszag && lovesszam< lista[i].lovesek)
                {
                    lovesszam=lista[i].lovesek;
                }
            }
            Console.WriteLine(lovesszam);
            Console.WriteLine("#");
            int jobbmintel3 = -1;
            for (int i = 3; i < N; ++i)
            {
                if (lista[i].lovhat> lista[i-1].lovhat && lista[i].lovhat > lista[i - 2].lovhat && lista[i].lovhat > lista[i - 3].lovhat)
                {
                    jobbmintel3 = i;
                    Console.WriteLine(i+1);
                    break;
                }
            }
            if (jobbmintel3 == -1) {
                Console.WriteLine(jobbmintel3);
            }
            Console.WriteLine("#");
            string leggyakoribb = lista.GroupBy(x => x.orszag).OrderByDescending(g => g.Count()).First().Key;
            Console.WriteLine(leggyakoribb);
        }
    }
}
