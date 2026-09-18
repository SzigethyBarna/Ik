using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Admiralis3
{
    public class Csillagromboló : Hajó
    {
        private int reaktorErő;

        public Csillagromboló(int páncél, int pajzs, int sebzés, int reaktorErő)
            : base(páncél, pajzs, sebzés)
        {
            this.reaktorErő = reaktorErő;
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
