using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Admiralis5
{
    public class Csillagromboló : Csatahajó
    {
        public Csillagromboló(int reaktor, int páncél, int pajzs, int sebzés)
            : base(reaktor, páncél, pajzs, sebzés)
        {
        }

        public override void Támad(Hajó ellenfél)
        {
            int újSebzés = Sebzés * reaktorErő;
            if (ellenfél is Csillagcirkáló)
            {
                ellenfél.TalálatotKap(újSebzés * 2);
            }
            else
            {
                ellenfél.TalálatotKap(újSebzés);
            }
        }
    }
}
