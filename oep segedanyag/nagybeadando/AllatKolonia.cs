using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OepBeadandokod
{
    public abstract class AllatKolonia
    {
        protected string becenev;
        protected int letszam;

        public string Becenev => becenev;
        public int Letszam
        {
            get => letszam;
            set => letszam = value;
        }

        protected AllatKolonia(string becenev, int letszam)
        {
            if (becenev==null) throw new ArgumentException();

            this.becenev = becenev;
            this.letszam = letszam < 0 ? 0 : letszam;
        }

        public void LetszamValtozas(int ertek)
        {
            letszam += ertek;
            if (letszam < 0)
            {
                letszam = 0;
            }
        }
        public virtual void Szaporodik(int kor) { }
    }
}
