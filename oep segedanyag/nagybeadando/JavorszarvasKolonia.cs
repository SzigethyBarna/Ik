using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OepBeadandokod
{
    public class JavorszarvasKolonia : ZsakmanyKolonia
    {
        public JavorszarvasKolonia(string becenev, int letszam) : base(becenev, letszam) { }

        public override void Szaporodik(int kor)
        {
            if (kor % 4 == 0) letszam = (int)(letszam * 1.2);
        }

        protected override void Elvandorol()
        {
            if (letszam >= 200) letszam = 40;
        }

        public override void Elfogad(RagadozoKolonia latogato)
        {
            if (latogato == null) throw new ArgumentNullException();
            latogato.Latogat(this);
        }
    }
}
