using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Admiralis5
{
    public abstract class Csatahajó : Hajó
    {
        protected int reaktorErő;
        protected int maxPáncél;
        protected int maxPajzs;

        protected Csatahajó(int reaktor, int páncél, int pajzs, int sebzés)
            : base(páncél, pajzs, sebzés)
        {
            this.reaktorErő = reaktor;
            this.maxPáncél = páncél;
            this.maxPajzs = pajzs;
        }

        public virtual void Javít()
        {
            if (Páncél > 0)
            {
                Páncél = Math.Min(maxPáncél, Páncél + Páncél / 5);
                Pajzs = Math.Min(maxPajzs, Pajzs + Pajzs / 3);
            }
        }
    }
}
