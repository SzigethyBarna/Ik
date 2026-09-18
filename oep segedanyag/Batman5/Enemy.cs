using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Batman5
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
            if (Hp < 0)
            {
                Simulation?.DeleteEnemy(this);
            }
        }
        public virtual void Attack()
        {

        }
        ///virtualnal kell test, abstractnal nem
    }
}
