using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Admiralis3
{
    public class Csata
    {
        private readonly Frakció támadó;
        public Frakció Támadó { get { return támadó; } }
        public Bolygó Hely { get; private set; }

        private HashSet<Hajó> támadók = new HashSet<Hajó>();
        private HashSet<Hajó> védekezők = new HashSet<Hajó>();

        public Csata(Frakció támadó, Bolygó hely, Hajó hT, Hajó hV)
        {
            this.támadó = támadó;
            Hely = hely;
            támadók.Add(hT);
            védekezők.Add(hV);
        }

        public void Erősítés(Frakció kitől, Hajó hajó)
        {
            if (kitől == Támadó)
            {
                if (támadók.Count < Hely.MaxFérőhely())
                {
                    támadók.Add(hajó);
                }
                else
                {
                    throw new InvalidOperationException();
                }
            }
            else
            {
                if (védekezők.Count < Hely.MaxFérőhely())
                {
                    védekezők.Add(hajó);
                }
                else
                {
                    throw new InvalidOperationException();
                }
            }
        }

        private (bool VanCélpont, Hajó? Célpont) CélpontotKeres(IEnumerable<Hajó> ellenfél)
        {
            var célpont = ellenfél.FirstOrDefault(h => h.Páncél > 0);
            return (célpont != null, célpont);
        }

        public void Harc()
        {
            foreach (var h in támadók)
            {
                if (h.Páncél > 0)
                {
                    var (talált, hajó) = CélpontotKeres(védekezők);
                    if (talált && hajó != null)
                    {
                        h.Támad(hajó);
                    }
                }
            }

            foreach (var h in védekezők)
            {
                if (h.Páncél > 0)
                {
                    var (talált, hajó) = CélpontotKeres(támadók);
                    if (talált && hajó != null)
                    {
                        h.Támad(hajó);
                    }
                }
            }
        }

        public (bool Győzött, int Túlélők) Eredmény(Frakció oldal)
        {
            int maradT = támadók.Count(h => h.Páncél > 0);
            int maradV = védekezők.Count(h => h.Páncél > 0);

            if (oldal == Támadó)
            {
                return (maradT > 0 && maradV == 0, maradT);
            }
            else
            {
                return (maradV > 0 && maradT == 0, maradV);
            }
        }

        public int MegsemmisültHajók()
        {
            return támadók.Count(h => h.Páncél <= 0) + védekezők.Count(h => h.Páncél <= 0);
        }
    }
}

