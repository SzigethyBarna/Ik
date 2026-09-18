using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Batman5
{
    public sealed class Batman
    {
        private int hp;
        private int damage;
        private Allies allies=new Allies();
        private Simulation? simulation;

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
        private Batman() {}
        public static Batman Instance { get; } = new Batman();
        public void Attack(Enemy target)
        {
            if(target is Titan || target is Villain)
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
            hp-=amount;
            if(hp < 0 && Simulation!=null)
            {
                Simulation.BatmanLost = true;
                Simulation.IsOver = true;
            }
        }

        public void CallForHelp(Enemy target) { 
            if(target is Thug)
            {
                allies.Lucius();
            }
            else if (target is Villain) {
                allies.Robin(target);
            }
            else
            {
                allies.CatWoman(target);
            }
        
        }
    }
}
