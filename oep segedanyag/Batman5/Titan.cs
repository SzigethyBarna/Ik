using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Net.Mime.MediaTypeNames;

namespace Batman5
{
    public class Titan : Enemy
    {
        public Titan(int hp, int damage) : base(hp, damage) { }
        public override void Attack()
        {
            Batman.Instance.TakeDamage(Damage);
        }
        public void Charging(int chance)
        {
            if (chance % 3 == 0)
            {
                base.TakeDamage(2);
            }
            else
            {
                Batman.Instance.TakeDamage(3);
            }
        }
    }
}
