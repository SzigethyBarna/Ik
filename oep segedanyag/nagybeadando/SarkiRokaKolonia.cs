using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OepBeadandokod
{
    public class SarkiRokaKolonia : RagadozoKolonia
    {
        public SarkiRokaKolonia(string becenev, int letszam) : base(becenev, letszam) { }

        public override void Szaporodik(int kor)
        {
            if (kor % 3 == 0)
            {
                int utodok = (letszam / 4) * 3;
                LetszamValtozas(utodok);
            }
        }

        public override void Latogat(LemmingKolonia l)
        {
            int elejtett = (int)(l.Letszam * 0.30);
            l.LetszamValtozas(-1*elejtett);
            EhezesVizsgalata(elejtett, 2);
        }
        public override void Latogat(SarkiNyulKolonia ny)
        {
            int elejtett = (int)(ny.Letszam * 0.20);
            ny.LetszamValtozas(-1*elejtett);
            EhezesVizsgalata(elejtett, 1);
        }
        public override void Latogat(JavorszarvasKolonia sz)
        {
            EhezesVizsgalata(0, 0);
        }
    }
}
