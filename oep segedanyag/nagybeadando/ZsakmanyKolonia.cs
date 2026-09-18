using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OepBeadandokod
{
    public abstract class ZsakmanyKolonia : AllatKolonia 
    {
        private static readonly Random rnd = new Random();

        protected ZsakmanyKolonia(string becenev, int letszam) : base(becenev, letszam) { }

        public virtual void Eletciklus(int kor)
        {
            Szaporodik(kor);
            Elvandorol();
        }
        protected virtual void Elvandorol() 
        {
            letszam = 0;
        }

        public virtual void Elfogad(RagadozoKolonia latogato)
        {
            if (latogato == null) throw new ArgumentNullException();
        }

        public static ZsakmanyKolonia RandomVal(List<ZsakmanyKolonia> zsakmanyok)
        {
            if (zsakmanyok == null || zsakmanyok.Count == 0) throw new ArgumentNullException();

            int randind = rnd.Next(0, zsakmanyok.Count);
            return zsakmanyok[randind];
        }
    }
}
