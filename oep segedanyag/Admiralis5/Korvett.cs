using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Admiralis5
{
    public class Korvett : Hajó
    {
        public Korvett(int páncél, int pajzs, int sebzés)
            : base(páncél, pajzs, sebzés)
        {
        }

        public override void Támad(Hajó ellenfél)
        {
            if (ellenfél is Csillagromboló)
            {
                ellenfél.TalálatotKap(Sebzés * 3);
            }
            else
            {
                ellenfél.TalálatotKap(Sebzés);
            }
        }
    }
}
