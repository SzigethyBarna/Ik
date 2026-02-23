using System;
using System.Collections.Generic;
using System.Linq;
namespace Karacsonyi_Slagerek
{
    class Program
    {
        struct Dalok
        {
            public int radiaz;
            public int dalaz;
            public int dalhossz; 
        };
        static void Main(string[] args)
        {
            string[] sor = Console.ReadLine().Split();
            int adb = int.Parse(sor[0]);
            int rdb = int.Parse(sor[1]);
            int ddb = int.Parse(sor[2]);
            if(adb<1 || adb>1000 || rdb<1 || rdb>1000 ||ddb<1 || ddb > 1000)
            {
                Console.Error.WriteLine("Hibás bemenet");
            }
            Dalok[] dalok = new Dalok[adb];
            for(int i = 0; i < adb; i++)
            {
                dalok[i] = new Dalok();
                string[] adat= Console.ReadLine().Split();
                dalok[i].radiaz = int.Parse(adat[0]);
                dalok[i].dalaz = int.Parse(adat[1]);
                dalok[i].dalhossz = int.Parse(adat[2]);
            }
            sor = Console.ReadLine().Split();
            int dal = int.Parse(sor[0]);
            int k=int.Parse(sor[1]);

            Console.WriteLine("#");
            int leghosszabbdal = -1;
            int maxind = -1;
            for (int i = 0; i < adb; i++)
            {
                if (dalok[i].dalhossz > leghosszabbdal)
                {
                    leghosszabbdal = dalok[i].dalhossz;
                    maxind = dalok[i].radiaz;
                }
            }
            Console.WriteLine(maxind);

            Console.WriteLine("#");
            int minhossz = 10000;
            for(int i = 0;i < adb; i++)
            {
                if (dalok[i].dalaz == dal && minhossz > dalok[i].dalhossz) 
                {
                    minhossz = dalok[i].dalhossz;
                }
            }
            if (minhossz == 10000)
            {
                Console.WriteLine(-1);
            }
            else
            {
                Console.WriteLine(minhossz);
            }
            Console.WriteLine("#");

            List<int> egyediDalok = new List<int>();
            int db = 0;
            for(int i = 0; i < adb; i++)
            {
                if (!egyediDalok.Contains(dalok[i].dalaz))
                {
                    egyediDalok.Add(dalok[i].dalaz);
                    db++;
                }
            }
            Console.WriteLine(db+" "+string.Join(" ", egyediDalok));

            Console.WriteLine("#");
            int count = 0;
            int keresett=-1;
            for(int i = 0; i <adb; i++)
            {

                if (dalok[i].dalhossz > 180)
                {
                    count++;

                    if (count == k)
                    {
                        keresett = i-k+2;
                        break;
                    }
                }
                else
                {
                    count = 0;
                }
            }
            Console.WriteLine(keresett);
        }
    }
}
