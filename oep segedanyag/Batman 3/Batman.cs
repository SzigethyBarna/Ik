using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Batman3
{
    public sealed class Batman
    {
        private int hp;
        private int damage;
        private Simulation? simulation;

        private Allies allies = new Allies();

        public int Hp
        {
            get { return hp; }
            set { hp = value; }
        }

        public int Damage
        {
            get { return damage; }
            set { damage = value; }
        }

        public Simulation? Simulation
        {
            get { return simulation; }
            set { simulation = value; }
        }
        // Privát konstruktor a Singleton miatt
        private Batman()
        {
        }

        // Egyetlen, globális példány (Instance)
        public static Batman Instance { get; } = new Batman();

        public void Attack(Enemy target)
        {
            if (target is Villain)
            {
                target.TakeDamage(Damage * 2);
            }
            else
            {
                target.TakeDamage(Damage);
            }
        }

        public void TakeDamage(int amount)
        {
            Hp -= amount;
            if (Hp <= 0)
            {
                if (Simulation != null)
                {
                    Simulation.BatmanLost = true;
                    Simulation.IsOver = true;
                }
            }
        }

        public void CallForHelp(Enemy target)
        {
            if (target is Thug)
            {
                allies.Lucius();
            }
            else
            {
                allies.Robin(target);
            }
        }
    }
}
