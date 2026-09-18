using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OepBeadandokod
{
    public class LemmingKolonia : ZsakmanyKolonia
    {
        public LemmingKolonia(string becenev, int letszam) : base(becenev, letszam) { }

        public override void Szaporodik(int kor)
        {
            if (kor % 2 == 0) letszam *= 2;
        }
        protected override void Elvandorol()
        {
            if (letszam >= 200) letszam = 30;
        }
        public override void Elfogad(RagadozoKolonia latogato)
        {
            if (latogato == null) throw new ArgumentNullException();
            latogato.Latogat(this);
        }
    }
}
