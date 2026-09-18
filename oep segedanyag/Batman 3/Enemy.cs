using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Batman3
{
    public abstract class Enemy
    {
        public int Hp { get; protected set; }
        public int Damage { get; protected set; }
        public Simulation? Simulation { get; set; }

        protected Enemy(int hp, int damage)
        {
            Hp = hp;
            Damage = damage;
        }

        public virtual void TakeDamage(int amount)
        {
            Hp -= amount;
            if (Hp <= 0)
            {
                if (Simulation != null)
                {
                    Simulation.DeleteEnemy(this);
                }
            }
        }

        public abstract void Attack();
    }
}
