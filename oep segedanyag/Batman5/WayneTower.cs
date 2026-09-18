using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;

namespace Batman5
{
    public class WayneTower
    {
        private Batman wayne;
        private List<Simulation> simulations = new List<Simulation>();

        public WayneTower(Batman batman)
        {
            this.wayne = batman;
        }

        public Simulation HardestSimulation()
        {
            if (simulations.Count == 0) throw new ArgumentException();

            return simulations.OrderByDescending(s => s.FullPower()).First();
        }

        public void MakeSimulation(List<Enemy> enemies, int id, int hp, int damage)
        {
            wayne.Hp = hp;
            wayne.Damage = damage;
            Simulation s = new Simulation(enemies, id);

            simulations.Add(s);
            wayne.Simulation = s;
            while (!s.IsOver)
            {
                s.StartRound();
            }
        }
        public int LostSimulationCount()
        {
            return simulations.Count(s => s.BatmanLost);
        }

        public (bool l, int min, Simulation? elem) ShortestLostSimulation()
        {
            var lostSims = simulations.Where(s => s.BatmanLost).ToList();
            if (!lostSims.Any()) return (false, 0, null);

            var shortest = lostSims.OrderBy(s => s.Rounds).First();
            return (true, shortest.Rounds, shortest);
        }

        public (bool l, Simulation? s) HardestTitanSimulation()
        {
            var titanSims = simulations.Where(s => s.TitanPower() > 50).ToList();
            if (!titanSims.Any()) return (false, null);

            var hardest = titanSims.OrderByDescending(s => s.FullPower()).First();
            return (true, hardest);


        }
    }
}
