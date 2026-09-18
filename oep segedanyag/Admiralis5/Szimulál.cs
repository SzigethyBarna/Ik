using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Admiralis5
{
    public class Szimuláció : ISzimulálható
    {
        // Singleton pattern: publikus statikus Instance
        public static Szimuláció Instance { get; } = new Szimuláció();

        private HashSet<Csata> csaták = new HashSet<Csata>();

        // Singleton pattern: privát konstruktor
        private Szimuláció()
        {
        }

        // A korábbi Futtat(n) eltűnt, helyette a Szimulál() fut le
        public void Szimulál()
        {
            foreach (var cs in csaták)
            {
                cs.Szimulál();
            }
        }

        public void ÚjCsata(Frakció oldal, Bolygó hely, Hajó hT, Hajó hV)
        {
            bool letezik = csaták.Any(cs => cs.Hely == hely);
            if (letezik)
            {
                throw new InvalidOperationException();
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
                throw new InvalidOperationException();
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
