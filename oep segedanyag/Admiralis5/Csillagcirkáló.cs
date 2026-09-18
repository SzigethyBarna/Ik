using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Admiralis5
{
    public class Csillagcirkáló : Csatahajó
    {
        public Csillagcirkáló(int reaktor, int páncél, int pajzs, int sebzés)
            : base(reaktor, páncél, pajzs, sebzés)
        {
        }

        public override void Támad(Hajó ellenfél)
        {
            if (ellenfél is Korvett)
            {
                ellenfél.TalálatotKap(Sebzés * 2);
            }
            else
            {
                ellenfél.TalálatotKap(Sebzés);
            }
        }

        public override void Javít()
        {
            if (Páncél > 0)
            {
                Páncél = Math.Min(maxPáncél, Páncél + Páncél / 5);
                Pajzs = Math.Min(maxPajzs, Pajzs + Pajzs / 3 + reaktorErő);
            }
        }
    }
}
