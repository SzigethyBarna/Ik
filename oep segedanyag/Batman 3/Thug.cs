using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Batman3
{
    public class Thug : Enemy
    {
        public Thug(int hp, int damage) : base(hp, damage)
        {
        }

        public override void Attack()
        {
            Batman.Instance.TakeDamage(Damage);
        }
    }
}
