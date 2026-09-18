using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OepBeadandokod
{
    public sealed class Tundra
    {
        private static Tundra? instance = null;
        private int korszamlalo = 0;
        private int kezdetiRagadozoSzam = 0;

        private List<ZsakmanyKolonia> zsakmanyok = new List<ZsakmanyKolonia> ();
        private List<RagadozoKolonia> ragadozok = new List<RagadozoKolonia>();

        public static Tundra GetInstance()
        {
            if (instance == null)
            {
                instance = new Tundra();
            }
            return instance;
        }

        public List<ZsakmanyKolonia> Zsakmanyok => zsakmanyok;
        public List<RagadozoKolonia> Ragadozok => ragadozok;

        public void SetKezdetiRagadozoSzam(int szam)
        {
            kezdetiRagadozoSzam = szam;
        }

        public void KorFuttatasa()
        {
            korszamlalo += 1;

            foreach (var z in zsakmanyok)
            {
                z.Eletciklus(korszamlalo);
            }

            foreach (var r in ragadozok)
            {
                r.Eletciklus(korszamlalo);
            }

            foreach (var r in ragadozok)
            {
                
                if (zsakmanyok.Count > 0)
                {
                    var z = ZsakmanyKolonia.RandomVal(zsakmanyok);
                    r.Tamad(z);
                }
            }
        }

        public bool KihaltFajVizsgalata()
        {
            int bagolyDb = 0, rokaDb = 0, medveDb = 0;
            int lemmingDb = 0, nyulDb = 0, szarvasDb = 0;

            foreach (var r in ragadozok)
            {
                if (r is HobagolyKolonia) bagolyDb += r.Letszam;
                else if (r is SarkiRokaKolonia) rokaDb += r.Letszam;
                else if (r is JegesmedveKolonia) medveDb += r.Letszam;
            }

            foreach (var z in zsakmanyok)
            {
                if (z is LemmingKolonia) lemmingDb += z.Letszam;
                else if (z is SarkiNyulKolonia) nyulDb += z.Letszam;
                else if (z is JavorszarvasKolonia) szarvasDb += z.Letszam;
            }

            if (bagolyDb == 0 || rokaDb == 0 || medveDb == 0 ||
                lemmingDb == 0 || nyulDb == 0 || szarvasDb == 0)
            {
                return false;
            }
            return true;
        }

        public bool VegenVanE()
        {
            int osszesRagadozo = 0;
            bool mindenKicsi = true;

            foreach (var r in ragadozok)
            {
                osszesRagadozo += r.Letszam;
                if (r.Letszam >= 4)
                {
                    mindenKicsi = false;
                }
            }

            if (mindenKicsi || osszesRagadozo >= 2 * kezdetiRagadozoSzam)
            {
                return true;
            }

            return false;
        }
    }
}
