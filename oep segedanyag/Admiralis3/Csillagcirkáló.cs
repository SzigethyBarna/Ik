using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Admiralis3
{
    public class Csillagcirkáló : Hajó
    {
        public Csillagcirkáló(int páncél, int pajzs, int sebzés)
            : base(páncél, pajzs, sebzés)
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
    }
}
