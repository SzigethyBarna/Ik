using System.Reflection;

namespace Batman5
{
    public class Program
    {
        private sealed class FrameworkException : Exception
        {
            public FrameworkException(string message) : base(message) { }
        }
        
        static void Main(string[] args)
        {
            string inputPath = args.Length == 0 ? "input.txt" : args[0];

            using IEnumerator<string> lines = File.ReadLines(inputPath).GetEnumerator();

            if (!lines.MoveNext()) throw new FrameworkException("Empty input.");

            switch (lines.Current)
            {
                case "tower":
                    TestTower(lines);
                    break;
                case "simulation":
                    TestSimulation(lines);
                    break;
                case "batman":
                    TestBatman(lines);
                    break;
                case "enemy":
                    TestEnemies(lines);
                    break;
                default:
                    throw new FrameworkException("Unknown keyword [" + lines.Current + "]");
            }
        }

        private static void TestTower(IEnumerator<string> lines)
        {
            WayneTower tower = new WayneTower(Batman.Instance);
            int simId = 0;
            while (lines.MoveNext())
            {
                string[] data = CurrentLine(lines);
                try
                {
                    switch (data[0])
                    {
                        case "new": // also runs the simulation
                            int vc = int.Parse(data[3]);
                            List<Enemy> enemies = new List<Enemy>();
                            while (vc --> 0) // :)
                            {
                                enemies.Add(ParseEnemy(lines));
                            }
                            
                            tower.MakeSimulation(enemies, simId++, int.Parse(data[1]),
                                int.Parse(data[2]));
                            Console.WriteLine($"New simulation (HP: {data[1]}, DMG: {data[2]}) with remaining enemies:\n" +
                                              string.Join('\n', enemies.Select(EnemyToString)));
                            break;
                        case "hardest":
                            Simulation hardest = tower.HardestSimulation();
                            Console.WriteLine(
                                $"Hardest simulation is {hardest.Id} ({hardest.FullPower()} of remaining enemy power)");
                            break;
                        case "lost":
                            Console.WriteLine("Lost simulations: " + tower.LostSimulationCount());
                            break;
                        case "shortest":
                            var shortest = CallShortestLostSimulation(tower);
                            if (shortest.Success)
                                Console.WriteLine(
                                    $"Shortest lost simulation: {shortest.Simulation!.Id} in {shortest.Min} rounds");
                            else Console.WriteLine("No lost simulations.");
                            break;
                        case "titanp":
                            var titanp50 = CallTitanPowerOverFifty(tower);
                            if (titanp50.Success)
                                Console.WriteLine($"Titan power over 50: simulation {titanp50.Simulation!.Id}");
                            else Console.WriteLine("No titan power over 50.");
                            break;
                        default:
                            throw new FrameworkException("Unknown keyword [" + data[0] + "]");
                    } 
                }
                catch (NullReferenceException)
                {
                    throw;
                }
                catch (IndexOutOfRangeException)
                {
                    throw;
                }
                catch (FrameworkException)
                {
                    throw;
                }
                catch
                {
                    Console.WriteLine("An expected or unexpected error occured.");
                }
            }
        }

        private static void TestSimulation(IEnumerator<string> lines)
        {
            Simulation? simulation = null;
            while (lines.MoveNext())
            {
                string[] data = CurrentLine(lines);
                switch (data[0])
                {
                    case "new":
                        Batman.Instance.Hp = int.Parse(data[1]);
                        Batman.Instance.Damage = int.Parse(data[2]);
                        int vc = int.Parse(data[3]);
                        List<Enemy> enemies = new List<Enemy>();
                        while (vc --> 0) // :)
                        {
                            enemies.Add(ParseEnemy(lines));
                        }
                        simulation = new Simulation(enemies, 0);
                        Batman.Instance.Simulation = simulation;
                        Console.WriteLine();
                        Console.WriteLine("New simulation with enemies:\n" + string.Join('\n', enemies.Select(EnemyToString)));
                        break;
                    case "status":
                        if (simulation == null) throw new FrameworkException("Simulation is unset.");
                        Console.WriteLine("Status:");
                        Console.WriteLine($"Active: {!simulation.IsOver}, Batman lost: {simulation.BatmanLost}, " +
                                          $"rounds: {simulation.Rounds}, total enemy power: {simulation.FullPower()}");
                        Console.WriteLine("Enemies:\n" + string.Join('\n', simulation.enemies.Select(EnemyToString)));
                        Console.WriteLine($"Batman: HP: {Batman.Instance.Hp}, DMG: {Batman.Instance.Damage}");
                        break;
                    case "round":
                        if (simulation == null) throw new FrameworkException("Simulation is unset.");
                        simulation.StartRound();
                        Console.WriteLine("New simulation round started.");
                        break;
                    case "titanp":
                        Console.WriteLine($"Titan power is {simulation.TitanPower()}.");
                        break;
                    default:
                        throw new FrameworkException("Unknown keyword [" + data[0] + "]");
                }
            }
        }

        private static void TestBatman(IEnumerator<string> lines)
        {
            if (!object.ReferenceEquals(Batman.Instance, Batman.Instance))
                throw new FrameworkException("Too many Batmen");
            if (Batman.Instance.GetType().GetConstructors().Length > 0)
                throw new FrameworkException("Batman has a public constructor");
            while (lines.MoveNext())
            {
                string[] data = CurrentLine(lines);
                switch (data[0])
                {
                    case "attack":
                        Enemy enemy = ParseEnemy(lines);
                        Simulation simulation = new Simulation(new List<Enemy>{enemy}, 0);
                        Batman.Instance.Simulation = simulation;
                        Batman.Instance.Hp = int.Parse(data[1]);
                        Batman.Instance.Damage = int.Parse(data[2]);
                        Console.WriteLine($"Batman (HP: {Batman.Instance.Hp}, DMG: {Batman.Instance.Damage})");
                        Console.WriteLine("Enemy: " + EnemyToString(enemy));
                        Batman.Instance.Attack(enemy);
                        Console.WriteLine($"Batman (HP: {Batman.Instance.Hp}, DMG: {Batman.Instance.Damage})");
                        Console.WriteLine("Enemy: " + EnemyToString(enemy));
                        Console.WriteLine("There are " + simulation.enemies.Count + " surviving enemies.");
                        break;
                    case "take":
                        simulation = new Simulation(new List<Enemy>(), 0);
                        Batman.Instance.Simulation = simulation;
                        Batman.Instance.Hp = int.Parse(data[1]);
                        Batman.Instance.Damage = int.Parse(data[2]);
                        Batman.Instance.TakeDamage(int.Parse(data[3]));
                        Console.WriteLine($"Batman took damage, results: HP: {Batman.Instance.Hp}, DMG: {Batman.Instance.Damage}");
                        Console.WriteLine($"Batman lost: {simulation.BatmanLost.ToString().ToLower()}");
                        break;
                    case "help":
                        enemy = ParseEnemy(lines);
                        simulation = new Simulation(new List<Enemy>{enemy}, 0);
                        Batman.Instance.Simulation = simulation;
                        Batman.Instance.Hp = int.Parse(data[1]);
                        Batman.Instance.Damage = int.Parse(data[2]);
                        Console.WriteLine("Batman asks for help against " + EnemyToString(enemy));
                        Batman.Instance.CallForHelp(enemy);
                        Console.WriteLine($"Batman (HP: {Batman.Instance.Hp}, DMG: {Batman.Instance.Damage})");
                        Console.WriteLine("Enemy: " + EnemyToString(enemy));
                        Console.WriteLine("There are " + simulation.enemies.Count + " surviving enemies.");
                        break;
                    default:
                        throw new FrameworkException("Unknown keyword [" + data[0] + "]");
                }
            }
        }
        
        private static void TestEnemies(IEnumerator<string> lines)
        {
            while (lines.MoveNext())
            {
                string[] data = CurrentLine(lines);
                Enemy enemy = ParseEnemy(lines);
                Console.WriteLine("Enemy: " + EnemyToString(enemy));
                Simulation simulation = new Simulation(new List<Enemy>{enemy}, 0);
                Batman.Instance.Simulation = simulation;
                
                switch (data[0])
                {
                    case "attack":
                        Batman.Instance.Hp = int.Parse(data[1]);
                        enemy.Attack();
                        Console.WriteLine("After an attack by " + EnemyToString(enemy) + ", Batman has " +
                                          Batman.Instance.Hp + " HP.");
                        break;
                    case "take":
                        enemy.TakeDamage(int.Parse(data[1]));
                        Console.WriteLine("Took damage, now has " + enemy.Hp + " HP.");
                        Console.WriteLine("Surviving enemies: " + simulation.enemies.Count);
                        break;
                    case "more!":
                        if (enemy is Villain villain)
                        {
                            var succThugs = CallCallMoreThugs(villain, int.Parse(data[1]));
                            Console.WriteLine($"Calling {data[1]} thugs: {(succThugs.Success ? "success" : "failed")}");
                            if (succThugs.Success)
                                Console.WriteLine("New thugs:\n" +
                                                  string.Join('\n', succThugs.Thugs!.Select(EnemyToString)));
                        }
                        else throw new FrameworkException("A thug may not call more thugs.");
                        break;
                    case "BIGMAN":
                        if (enemy is Villain villain2)
                        {
                            var succTit = CallMakeTitan(villain2);
                            Console.WriteLine($"Titan creation " +
                                              $"{(succTit.Success ? $"successful: {EnemyToString(succTit.Titan!)}" : "failed")}");
                        }
                        else throw new FrameworkException("A thug may not create titans.");
                        break;
                    case "charge":
                        if (enemy is Titan titan)
                        {
                            Console.WriteLine("Charging titan....");
                            Batman.Instance.Hp = int.Parse(data[2]);
                            Batman.Instance.Damage = int.Parse(data[3]);
                            titan.Charging(int.Parse(data[1]));
                            Console.WriteLine($"Titan charging: {EnemyToString(titan)}, " +
                                              $"Batman HP: {Batman.Instance.Hp}, DMG: {Batman.Instance.Damage}");
                        }
                        else throw new FrameworkException("Only a titan can charge.");
                        break;
                    case "scharge":
                        if (enemy is Titan titan2)
                        {
                            Console.WriteLine("Charging titan....");
                            Batman.Instance.Hp = int.Parse(data[2]);
                            Batman.Instance.Damage = int.Parse(data[3]);
                            titan2.Simulation = simulation;
                            titan2.Charging(int.Parse(data[1]));
                            Console.WriteLine($"Titan charging: {EnemyToString(titan2)}, " +
                                              $"Batman HP: {Batman.Instance.Hp}, DMG: {Batman.Instance.Damage}");
                            Console.WriteLine($"Surviving enemies: {simulation.enemies.Count}");
                        }
                        else throw new FrameworkException("Only a titan can charge.");
                        break;
                    default:
                        throw new FrameworkException("Unknown keyword [" + data[0] + "]");
                }
            }
        }

        private static Enemy ParseEnemy(IEnumerator<string> lines)
        {
            if (!lines.MoveNext())
                throw new FrameworkException("No enemy given (or there's a poor villain missing his thugs).");
            try
            {
                string[] data = CurrentLine(lines);
                switch (data[0])
                {
                    case "thug":
                        return new Thug(int.Parse(data[1]), int.Parse(data[2]));
                    case "villain":
                        List<Thug> thugs = new List<Thug>();
                        int tc = int.Parse(data[3]);
                        while (tc-- > 0) // :)
                        {
                            thugs.Add(ParseEnemy(lines) as Thug ?? throw new FrameworkException("That's no thug."));
                        }
                        return new Villain(int.Parse(data[1]), int.Parse(data[2]), thugs);
                    case "titan":
                        return new Titan(int.Parse(data[1]), int.Parse(data[2]));
                }
            }
            catch
            {
                throw new FrameworkException("Could not parse enemy.");
            }

            throw new FrameworkException("Could not parse enemy.");
        }

        private static string[] CurrentLine(IEnumerator<string> lines) => lines.Current.Split(new char[] { ' ', '\t' },
            StringSplitOptions.RemoveEmptyEntries | StringSplitOptions.TrimEntries);

        private static string EnemyToString(Enemy enemy) =>
            $"{enemy.GetType().Name} (HP: {enemy.Hp}, DMG: {enemy.Damage})";

        private static (bool Success, List<Thug>? Thugs) CallCallMoreThugs(Villain villain, int count)
        {
            // magic
            const string methodName = nameof(Villain.CallMoreThugs);

            MethodInfo? method = villain.GetType().GetMethod(methodName, BindingFlags.Instance | BindingFlags.Public,
                new Type[] { typeof(int), typeof(List<Thug>).MakeByRefType() });
            if (method != null && method.ReturnType == typeof(bool))
            {
                object?[] param = new object?[] { count, null };
                bool result = (bool)method.Invoke(villain, param)!;
                return (result, (List<Thug>?)param[0]);
            }

            method = villain.GetType()
                .GetMethod(methodName, BindingFlags.Instance | BindingFlags.Public, new Type[] { typeof(int) });
            if (method != null && method.ReturnType == typeof(ValueTuple<bool, List<Thug>>))
            {
                object? result = method.Invoke(villain, new object?[] { count });
                return ((bool, List<Thug>?))result!;
            }
            if (method != null && method.ReturnType == typeof(Tuple<bool, List<Thug>>))
            {
                Tuple<bool, List<Thug>?> result = (Tuple<bool, List<Thug>?>)method.Invoke(villain, new object?[] { count })!;
                return (result.Item1, result.Item2);
            }

            throw new FrameworkException("No method called " + methodName + " in " + nameof(Villain));
        }

        private static (bool Success, int Min, Simulation? Simulation) CallShortestLostSimulation(WayneTower tower)
        {
            // magic
            const string methodName = nameof(WayneTower.ShortestLostSimulation);

            MethodInfo? method = tower.GetType().GetMethod(methodName, BindingFlags.Public | BindingFlags.Instance,
                new Type[] { });
            if (method != null && method.ReturnType == typeof(ValueTuple<bool, int, Simulation>))
            {
                return (ValueTuple<bool, int, Simulation>)method.Invoke(tower, new object?[] { })!;
            }
            if (method != null && method.ReturnType == typeof(Tuple<bool, int, Simulation>))
            {
                return ((Tuple<bool, int, Simulation>)method.Invoke(tower, new object?[] { })!).ToValueTuple();
            }
            
            method = tower.GetType().GetMethod(methodName, BindingFlags.Public | BindingFlags.Instance,
                new Type[] { typeof(int).MakeByRefType(), typeof(Simulation).MakeByRefType() });
            if (method != null && method.ReturnType == typeof(bool))
            {
                object?[] param = new object?[] { -1, null };
                bool result = (bool)method.Invoke(tower, param)!;
                return (result, (int)param[0]!, (Simulation?)param[1]);
            }

            throw new FrameworkException("No method called " + methodName + " in " + nameof(WayneTower));
        }

        private static (bool Success, Titan? Titan) CallMakeTitan(Villain villain)
        {
            // magic
            const string methodName = nameof(Villain.MakeTitan);

            MethodInfo? method = villain.GetType().GetMethod(methodName, BindingFlags.Public | BindingFlags.Instance,
                new Type[] { });
            if (method != null && method.ReturnType == typeof(ValueTuple<bool, Titan>))
            {
                return (ValueTuple<bool, Titan>)method.Invoke(villain, new object?[] { })!;
            }
            if (method != null && method.ReturnType == typeof(Tuple<bool, Titan>))
            {
                return ((Tuple<bool, Titan>)method.Invoke(villain, new object?[] { })!).ToValueTuple();
            }
            if (method != null && method.ReturnType == typeof(ValueTuple<Titan, bool>))
            {
                var ret = (ValueTuple<Titan, bool>)method.Invoke(villain, new object?[] { })!;
                return (ret.Item2, ret.Item1);
            }
            if (method != null && method.ReturnType == typeof(Tuple<Titan, bool>))
            {
                var ret = ((Tuple<Titan, bool>)method.Invoke(villain, new object?[] { })!);
                return (ret.Item2, ret.Item1);
            }

            method = villain.GetType().GetMethod(methodName, BindingFlags.Public | BindingFlags.Instance,
                new Type[] { typeof(Titan).MakeByRefType() });
            if (method != null && method.ReturnType == typeof(bool))
            {
                object?[] param = new object?[] { null };
                bool ret = (bool)method.Invoke(villain, param)!;
                return (ret, param[0] as Titan);
            }

            throw new FrameworkException("No method named " + methodName + " found in " + nameof(Villain));
        }

        private static (bool Success, Simulation? Simulation) CallTitanPowerOverFifty(WayneTower tower)
        {
            const string methodName = nameof(WayneTower.TitanPowerOverFifty);

            MethodInfo? method = tower.GetType().GetMethod(methodName, BindingFlags.Instance | BindingFlags.Public,
                new Type[] { });
            if (method != null && method.ReturnType == typeof(ValueTuple<bool, Simulation>))
            {
                return ((bool, Simulation?))method.Invoke(tower, new object?[] { })!;
            }
            if (method != null && method.ReturnType == typeof(Tuple<bool, Simulation>))
            {
                return ((Tuple<bool, Simulation?>)method.Invoke(tower, new object?[] { })!).ToValueTuple();
            }

            method = tower.GetType().GetMethod(methodName, BindingFlags.Instance | BindingFlags.Public,
                new Type[] { typeof(Simulation).MakeByRefType() });
            if (method != null && method.ReturnType == typeof(bool))
            {
                object?[] param = new object?[] { null };
                bool ret = (bool)method.Invoke(tower, param)!;
                return (ret, param[0] as Simulation);
            }
            
            throw new FrameworkException("No method named " + methodName + " found in " + nameof(WayneTower));
        }
    }
}