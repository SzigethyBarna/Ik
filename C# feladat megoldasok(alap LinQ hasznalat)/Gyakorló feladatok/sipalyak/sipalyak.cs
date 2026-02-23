using System;
using System.Collections.Generic;
using System.Linq;

namespace Sipalyak
{
    class Program
    {
        static void Main(string[] args)
        {

                string[] sor = Console.ReadLine().Split();
                int napdb = int.Parse(sor[0]);
                int palyadb = int.Parse(sor[1]);
                if (napdb < 2 || napdb > 1000 || palyadb < 1 || palyadb > 1000)
                {
                Console.Error.WriteLine("HIBA: Nem megfelelő adatok!");
                }
                int[][] adatok = new int[napdb][];

                for (int i = 0; i < napdb; i++)
                {
                    string[] adat = Console.ReadLine().Split();
                    adatok[i] = new int[palyadb];
                    for (int j = 0; j < palyadb; j++)
                    {
                        adatok[i][j] = int.Parse(adat[j]);
                    if (adatok[i][j] > 100 || adatok[i][j] < 0)
                        {
                        Console.Error.WriteLine("HIBA: Nem megfelelő adatok!"); break;
                 
                        }
                    }
                }

                Console.WriteLine("#");
                int nagyobb50 = -1;
                for (int i = 0; i < palyadb; i++)
                {
                    if (adatok[0][i] > 50)
                    {
                        nagyobb50 = i + 1;
                        Console.WriteLine(nagyobb50);
                        break;
                    }
                }
                if (nagyobb50 == -1)
                {
                    Console.WriteLine(-1);
                }

                Console.WriteLine("#");
                int maxsorind = -1;
                int maxsor = -1;
                for (int i = 0; i < napdb; i++)
                {
                    if (adatok[i][0] < 50 && maxsor < adatok[i][0])
                    {
                        maxsorind = i + 1;
                        maxsor = adatok[i][0];
                    }
                }
                Console.WriteLine(maxsorind);

                Console.WriteLine("#");

                List<int> mindenesNapok = new List<int>();
                for (int i = 0; i < napdb; i++)
                {
                    int havasPalyakSzama = 0;
                    
                    for (int j = 0; j < palyadb; j++)
                    {
                        if (adatok[i][j] > 0)
                        {
                            havasPalyakSzama++;
                        }
                    }
                    if (havasPalyakSzama == palyadb)
                    {
                        mindenesNapok.Add(i + 1);
                        
                    }
                }
                if (mindenesNapok.Count>0)
                {
                    Console.WriteLine(mindenesNapok.Count +" "+ string.Join(" ", mindenesNapok));
                }
                else { Console.WriteLine(0); }

                Console.WriteLine("#");

                int[] noves = new int[palyadb];
                for(int j=0; j < palyadb; j++)
                {   int db = 0;
                    for (int i = 1;i < napdb; i++)
                    {
                        if (adatok[i][j] > adatok[i-1][j])
                        {
                            db++;
                        }
                    }
                    noves[j] = db;
                }
                Console.WriteLine(string.Join(" ", noves));
            




        }
    }
}
