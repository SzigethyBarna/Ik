using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OepBeadandokod
{
    public class JegesmedveKolonia : RagadozoKolonia
    {
        public JegesmedveKolonia(string becenev, int letszam) : base(becenev, letszam) { }

        public override void Szaporodik(int kor)
        {
            if (kor % 8 == 0)
            {
                int utodok = (letszam / 4) * 1;
                LetszamValtozas(utodok);
            }
        }

        public override void Latogat(LemmingKolonia l)
        {
            int elejtett = (int)(l.Letszam * 0.02);
            l.LetszamValtozas(-1*elejtett);
            EhezesVizsgalata(elejtett, 20);
        }

        public override void Latogat(SarkiNyulKolonia ny)
        {
            int elejtett = (int)(ny.Letszam * 0.01);
            ny.LetszamValtozas(-1*elejtett);
            EhezesVizsgalata(elejtett, 10);
        }

        public override void Latogat(JavorszarvasKolonia sz)
        {
            int elejtett = (int)(sz.Letszam * 0.25);
            sz.LetszamValtozas(-1*elejtett);
            EhezesVizsgalata(elejtett, 0.5);
        }
    }
}

