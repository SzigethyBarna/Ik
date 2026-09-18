using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Batman3
{
    public class Villain : Enemy
    {
        private List<Thug> thugs;

        public Villain(int hp, int damage, IEnumerable<Thug> thugs) : base(hp, damage)
        {
            this.thugs = thugs.ToList();
        }

        public (bool Success, List<Thug>? ThugsCopy) CallMoreThugs(int amount)
        {
            if (thugs.Count < amount)
            {
                return (false, null);
            }
            else
            {
                var thugsCopy = new List<Thug>();
                for (int i = 0; i < amount; i++)
                {
                    thugs[i].Simulation = this.Simulation;
                    thugsCopy.Add(thugs[i]);
                }
                thugs.RemoveRange(0, amount);
                return (true, thugsCopy);
            }
        }

        public override void TakeDamage(int amount)
        {
            if (thugs.Count > 0)
            {
                // Megkeressük a legtöbb életerővel rendelkező Thug-ot
                var elem = thugs.OrderByDescending(t => t.Hp).First();
                elem.TakeDamage(amount);

                if (elem.Hp <= 0)
                {
                    thugs.Remove(elem);
                }
            }
            else
            {
                base.TakeDamage(amount);
            }
        }

        public override void Attack()
        {
            Batman.Instance.TakeDamage(Damage + 3);
        }
    }
}
