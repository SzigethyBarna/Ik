using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Admiralis3
{
    public abstract class Hajó
    {
        public int Páncél { get; protected set; }
        protected int Pajzs { get; private set; }
        protected int Sebzés { get; private set; }

        public Hajó(int páncél, int pajzs, int sebzés)
        {
            Páncél = páncél;
            Pajzs = pajzs;
            Sebzés = sebzés;
        }

        public abstract void Támad(Hajó ellenfél);

        public void TalálatotKap(int sebzés)
        {
            if (Pajzs > 0)
            {
                if (Pajzs - sebzés < 0)
                {
                    sebzés -= Pajzs;
                    Pajzs = 0;
                }
                else
                {
                    Pajzs -= sebzés;
                    return;
                }
            }
            Páncél -= sebzés;
        }
    }
}
