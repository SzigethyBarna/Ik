using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Batman5
{
    public class Villain : Enemy
    {
        private List<Thug> thugs;
        public Villain(int hp, int damage, IEnumerable<Thug> thugs) : base(hp, damage) { this.thugs = thugs.ToList(); }

        public override void TakeDamage(int amount)
        {
            if (thugs.Count > 0)
            {
                var elem = thugs.OrderByDescending(t => t.Hp).First();
                elem.TakeDamage(amount);
                if (elem.Hp <= 0)
                {
                    thugs.Remove(elem);
                   
                }
                else {
                    base.TakeDamage(amount);
                }
            }
            
        




        }
        public override void Attack()
        {
            Batman.Instance.TakeDamage(Damage + 3);
        }
    }
}
