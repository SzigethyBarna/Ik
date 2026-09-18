using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Admiralis5
{
    public class Bolygó
    {
        public string Név { get; }
        private int méret;

        public Bolygó(string név, int méret)
        {
            Név = név;
            this.méret = méret;
        }

        public int MaxFérőhely()
        {
            return méret * 2;
        }
    }
}
