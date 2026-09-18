using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Admiralis3
{
    public class Bolygó
    {
        public string Név { get; private set; }
        private int méret;

        public Bolygó(string név, int méret)
        {
            this.Név = név;
            this.méret = méret;
        }

        public int MaxFérőhely()
        {
            return méret * 2;
        }
    }
}
