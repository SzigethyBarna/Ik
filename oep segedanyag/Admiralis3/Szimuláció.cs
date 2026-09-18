using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Admiralis3
{
    public class Szimuláció
    {
        private HashSet<Csata> csaták = new HashSet<Csata>();

        public void Futtat(int n)
        {
            for (int i = 1; i <= n; i++)
            {
                foreach (var cs in csaták)
                {
                    cs.Harc();
                }
            }
        }

        public void ÚjCsata(Frakció oldal, Bolygó hely, Hajó hT, Hajó hV)
        {
            bool letezik = csaták.Any(cs => cs.Hely == hely);
            if (letezik)
            {
                throw new InvalidOperationException("Ezen a bolygón már zajlik egy csata.");
            }

            var újCsata = new Csata(oldal, hely, hT, hV);
            csaták.Add(újCsata);
        }

        public void Erősítés(Frakció oldal, Bolygó hely, IEnumerable<Hajó> hajók)
        {
            var csata = csaták.FirstOrDefault(cs => cs.Hely == hely);
            if (csata != null)
            {
                foreach (var h in hajók)
                {
                    csata.Erősítés(oldal, h);
                }
            }
            else
            {
                throw new InvalidOperationException("Nincs folyamatban lévő csata ezen a helyen.");
            }
        }

        public Bolygó? ElsöprőGyőzelem(Frakció oldal)
        {
            Csata? legjobbCsata = null;
            int maxTúlélő = -1;

            foreach (var csata in csaták)
            {
                var (győzött, túlélők) = csata.Eredmény(oldal);
                if (győzött && túlélők > maxTúlélő)
                {
                    maxTúlélő = túlélők;
                    legjobbCsata = csata;
                }
            }

            return legjobbCsata?.Hely;
        }

        public int MegsemmisültHajók(string hely)
        {
            return csaták.Where(cs => cs.Hely.Név == hely)
                         .Sum(cs => cs.MegsemmisültHajók());
        }
    }
}
