using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Batman5
{
   public class Allies
    {
        public Allies() { }
        public void Lucius()
        {
            Batman.Instance.Damage += 5;
        }
        public void Robin(Enemy target)
        {
            target.TakeDamage(Batman.Instance.Damage + 15);
        }
        public void CatWoman(Enemy target)
        {
            Batman.Instance.Simulation?.DeleteEnemy(target);
        }
    }
}
