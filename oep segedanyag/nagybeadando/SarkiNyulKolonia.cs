using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OepBeadandokod
{
    public class SarkiNyulKolonia : ZsakmanyKolonia
    {
        public SarkiNyulKolonia(string becenev, int letszam) : base(becenev, letszam) { }
        public override void Szaporodik(int kor)
        {
            if (kor % 2 == 0) letszam = (int)(letszam * 1.5);
        }

        protected override void Elvandorol()
        {
            if (letszam >= 100) letszam = 20;
        }

        public override void Elfogad(RagadozoKolonia latogato)
        {
            if (latogato == null) throw new ArgumentNullException();
            latogato.Latogat(this);
        }
    }
}
