using System;
using System.Collections.Generic;
using System.Linq;

namespace Foci
{
    class Program
    {
        struct Meccsek
        {
            public string cse;
            public string csm;
            public int csegoal;
            public int csmgoal;
        };
        static void Main(string[] args)
        {
            string[] sor = Console.ReadLine().Split();
            int meccsdb = int.Parse(sor[0]);
            string szoveg = sor[1];
            Meccsek[] meccsek = new Meccsek[meccsdb];
            for(int i = 0; i < meccsdb; i++)
            {
                meccsek[i] = new Meccsek();
                string[] adat= Console.ReadLine().Split();
                meccsek[i].cse=adat[0];
                meccsek[i].csm = adat[1];
                meccsek[i].csegoal = int.Parse(adat[2]);
                meccsek[i].csmgoal = int.Parse(adat[3]);
            }
            Console.WriteLine("#");
            int maxgol = 0;
            string maxcs = meccsek[0].cse;
            for (int i = 0; i < meccsdb; i++) 
            {
                if (maxgol < meccsek[i].csegoal || maxgol < meccsek[i].csmgoal)
                {
                    if (meccsek[i].csegoal < meccsek[i].csmgoal)
                    {
                        maxgol = meccsek[i].csmgoal;
                        maxcs = meccsek[i].csm;
                    }
                    else { maxgol = meccsek[i].csegoal; maxcs = meccsek[i].cse; }
                }
            }
            Console.WriteLine(maxcs);
            Console.WriteLine("#");
            List<string> tobbgol = new List<string>();
            int count = 0;
            for (int i = 0; i < meccsdb; i++) 
            {
                if (meccsek[i].csegoal < meccsek[i].csmgoal)
                {
                    if (!tobbgol.Contains(meccsek[i].csm))
                    {
                        tobbgol.Add(meccsek[i].csm);
                        count++;
                    }
                }
                else if(meccsek[i].csegoal > meccsek[i].csmgoal)
                {
                    if (!tobbgol.Contains(meccsek[i].cse))
                    {
                        tobbgol.Add(meccsek[i].cse);
                        count++;
                    }
                }
            }
            Console.WriteLine(count + " " + string.Join(" ",tobbgol));
            Console.WriteLine("#");

            List<string> csapatok = new List<string>();
            List<string> meccscsapatok = new List<string>();

            for (int i = 0; i < meccsdb; i++) {
                if (!csapatok.Contains(meccsek[i].cse))
                {
                    csapatok.Add(meccsek[i].cse);
                    
                }
                if (!csapatok.Contains(meccsek[i].csm))
                {
                    csapatok.Add(meccsek[i].csm);
                    
                }
                meccscsapatok.Add(meccsek[i].cse);
                meccscsapatok.Add(meccsek[i].csm);
            }
            Console.WriteLine(csapatok.Count());
            Console.WriteLine("#");
            int rugott = 0;
            int kapott = 0;
            int pont = 0;
            for (int i = 0; i < meccsdb; i++)
            {
                if (meccsek[i].cse==szoveg)
                {
                    rugott += meccsek[i].csegoal;
                    kapott += meccsek[i].csmgoal;
                    if (meccsek[i].csegoal > meccsek[i].csmgoal)
                    {
                        pont += 3;
                    }else if(meccsek[i].csegoal == meccsek[i].csmgoal)
                    {
                        pont += 1;
                    }
                }
                else if(meccsek[i].csm == szoveg)
                {
                    rugott += meccsek[i].csmgoal;
                    kapott += meccsek[i].csegoal;
                    if (meccsek[i].csegoal < meccsek[i].csmgoal)
                    {
                        pont += 3;
                    }
                    else if (meccsek[i].csegoal == meccsek[i].csmgoal)
                    {
                        pont += 1;
                    }
                }
            }
            Console.WriteLine(rugott + " " + kapott + " " + pont);
            Console.WriteLine("#");
            for(int i=0;i< csapatok.Count; i++)
            {
                Console.WriteLine(csapatok[i] + " " + meccscsapatok.Count(x=>x==csapatok[i]));
            }
        }
    }
}
