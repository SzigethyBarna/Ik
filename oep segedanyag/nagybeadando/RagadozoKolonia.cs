using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OepBeadandokod
{
    public abstract class RagadozoKolonia : AllatKolonia
    {
        protected RagadozoKolonia(string becenev, int letszam) : base(becenev, letszam) { }
        public virtual void Eletciklus(int kor)
        {
            Szaporodik(kor);
        }

        public virtual void Tamad(ZsakmanyKolonia zsakmany)
        {
            if(zsakmany==null) throw new ArgumentNullException();
            zsakmany.Elfogad(this);
        }
        public virtual void Latogat(LemmingKolonia l) { if(l == null) throw new ArgumentNullException(); }
        public virtual void Latogat(SarkiNyulKolonia ny) { if (ny == null) throw new ArgumentNullException(); }
        public virtual void Latogat(JavorszarvasKolonia sz) { if(sz == null) throw new ArgumentNullException(); }

        protected void EhezesVizsgalata(int elejtettPeldanyok, double taplalekIgeny)
        {
            if (taplalekIgeny <= 0)
            {
                letszam = 0; 
                return;
            }

            int megmaradoLetszam = (int)(elejtettPeldanyok / taplalekIgeny);
            if (megmaradoLetszam < letszam)
            {
                letszam = megmaradoLetszam;
            }
        }
    }
}
