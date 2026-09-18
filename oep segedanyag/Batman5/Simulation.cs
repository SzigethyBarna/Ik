using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Net.Mime.MediaTypeNames;

namespace Batman5
{
    public class Simulation
    {
        private List<Enemy> enemies;
        private bool isOver;
        private int rounds;
        private bool batmanLost;
        private int id;

        
        public bool IsOver
        {
            get { return isOver; }
            set { isOver = value; }
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
        public int Rounds
        {
            get { return rounds; }
            private set { rounds = value; }
        }
        public List<Enemy> Enemies
        {
            get { return enemies; }
            private set { enemies = value; }
        }
        public Simulation(IEnumerable<Enemy> enemies,int id)
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
            Rounds++;
            
        }
        public int FullPower()
        {
            int sum = 0;
            foreach (var e in enemies) {
                sum += e.Damage;

            }
            return sum;
        }
        public int TitanPower()
        {
            return enemies.OfType<Titan>().Sum(t => t.Damage);
        }
        private (bool l, Villain? v) StrongestVillain()
        {
            Villain? strngV=null;
            int maxdmg = 0;
                foreach(var e in enemies){
                    if(e is Villain v&& maxdmg<e.Damage)
                {
                    maxdmg = v.Damage;
                    strngV = v;
                }
            }
                if(strngV!=null) return (true,strngV);
            return (false, null); 
        }
    }
}
