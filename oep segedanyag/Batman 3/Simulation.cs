using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Batman3
{
    public class Simulation
    {
        private bool isOver;
        private int rounds;
        private bool batmanLost;
        private int id;

        private List<Enemy> enemies;
        public bool IsOver
        {
            get { return isOver; }
            set { isOver = value; }
        }

        public int Rounds
        {
            get { return rounds; }
            private set { rounds = value; }
        }

        public bool BatmanLost
        {
            get { return batmanLost; }
            set { batmanLost = value; }
        }

        public int Id
        {
            get { return id; }
            private set { id = value; }
        }

        public List<Enemy> Enemies
        {
            get { return enemies; }
            private set { enemies = value; }
        }
        public Simulation(IEnumerable<Enemy> enemies, int id)
        {
            IsOver = false;
            Rounds = 0;
            BatmanLost = false;
            Id = id;
            this.enemies = enemies.ToList();

            foreach (var e in Enemies)
            {
                e.Simulation = this;
            }
        }

        public void DeleteEnemy(Enemy enemy)
        {
            Enemies.Remove(enemy);
        }

        public void StartRound()
        {
            if (Enemies.Count > 0)
            {
                Rounds++;
                if (Rounds % 3 == 0)
                {
                    var (found, strongest) = StrongestVillain();
                    if (found && strongest != null)
                    {
                        Batman.Instance.CallForHelp(strongest);
                    }
                    else
                    {
                        Batman.Instance.CallForHelp(Enemies[0]);
                    }
                }
                else
                {
                    Batman.Instance.Attack(Enemies[0]);
                }

                if (Enemies.Count == 0)
                {
                    IsOver = true;
                    return;
                }

                Enemies[0].Attack();

                if (Enemies[0] is Villain v)
                {
                    if (Rounds % 4 == 0)
                    {
                        var (success, extraThugs) = v.CallMoreThugs(5);
                        if (success && extraThugs != null)
                        {
                            // "enemies @ enemies[0].CallMoreThugs(5)[1]"
                            Enemies.AddRange(extraThugs);
                        }
                    }
                }
            }
            else
            {
                IsOver = true;
            }
        }

        public int FullPower()
        {
            return Enemies.Sum(e => e.Damage);
        }

        public (bool Found, Villain? Strongest) StrongestVillain()
        {
            var villains = Enemies.OfType<Villain>().ToList();
            if (!villains.Any())
            {
                return (false, null);
            }

            var strongest = villains.OrderByDescending(v => v.Damage).First();
            return (true, strongest);
        }
    }
}
