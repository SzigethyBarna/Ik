// Feltételezzük, hogy van egy List<Enemy> enemies listánk.

// ---------------------------------------------------------
// 1. .Where() -> SZŰRÉS (Több elemet ad vissza)
// Mikor használd? Ha az UML "SEARCH" vagy "IN" szavakat használ, 
// és több olyan elem kell, ami megfelel egy feltételnek.
// ---------------------------------------------------------
var eloEllenfelek = enemies.Where(e => e.Hp > 0).ToList();


// ---------------------------------------------------------
// 2. .FirstOrDefault() -> KERESÉS (Egyetlen elemet ad vissza)
// Mikor használd? Ha pontosan EGY elemet keresel (pl. célpontot).
// Ha nem talál ilyet, 'null'-t ad vissza, így nem omlik össze a program!
// ---------------------------------------------------------
var celpont = enemies.FirstOrDefault(e => e.Hp > 0);
if (celpont != null) { /* Támadás */ }


// ---------------------------------------------------------
// 3. .OrderByDescending().First() -> MAXIMUM / MINIMUM KERESÉS
// Mikor használd? A pszeudokódokban gyakori a "MAX" vagy "MIN".
// Először csökkenő sorrendbe rakjuk, és kivesszük a legelsőt.
// (Minimumhoz használd simán az .OrderBy() -t).
// ---------------------------------------------------------
var legerosebb = enemies.OrderByDescending(e => e.Damage).First();


// ---------------------------------------------------------
// 4. .Sum() -> ÖSSZEGZÉS
// Mikor használd? Ha a pszeudokód "SUM"-ot kér (pl. összes sebzés, összhaderő).
// ---------------------------------------------------------
int osszSebzes = enemies.Sum(e => e.Damage);


// ---------------------------------------------------------
// 5. .Count() -> SZÁMLÁLÁS FELTÉTELLEL
// Mikor használd? Ha azt kell megszámolni, HÁNY darab elem felel meg 
// egy bizonyos dolognak (pl. hány halott van). 
// Jobb, mint a .Where(...).Count!
// ---------------------------------------------------------
int halottakSzama = enemies.Count(e => e.Hp <= 0);


// ---------------------------------------------------------
// 6. .OfType<T>() -> TÍPUS SZERINTI SZŰRÉS
// Mikor használd? Amikor egy ősosztályokat tartalmazó listából 
// (pl. Enemy listából) csak a leszármazottakra vagy kíváncsi (pl. csak a Titánok).
// ---------------------------------------------------------
var csakTitanok = enemies.OfType<Titan>().ToList();


// ---------------------------------------------------------
// 7. .Any() -> LÉTEZÉS VIZSGÁLATA (Igaz/Hamis)
// Mikor használd? Ha csak annyit akarsz tudni, hogy van-e LEGALADÁBB EGY 
// a feltételnek megfelelő elem (pl. vége-e a csatának, van-e még élő).
// ---------------------------------------------------------
bool vanMegElo = enemies.Any(e => e.Hp > 0);

// Csinál egy listát, amiben csak a túlélő hajók ID-jai / Nevei vannak:
var tuleloNevek = enemies.Where(e => e.Hp > 0)
                         .Select(e => e.Nev)
                         .ToList();

// Igazat ad vissza, ha az ÖSSZES ellenfél életereje 0 vagy az alatti (vége a csatának):
bool mindenkiMeghalt = enemies.All(e => e.Hp <= 0);

// Sorbarakja őket sebzés alapján csökkenőbe, majd kiveszi az ELSŐ HÁRMAT:
var haromLegerosebb = enemies.OrderByDescending(e => e.Damage)
                             .Take(3)
                             .ToList();

// Visszaadja azt a Titán objektumot, amelyiknek a legtöbb a HP-ja:
var legszivosabbTitan = enemies.OfType<Titan>().MaxBy(t => t.Hp);


// Egyetlen sorban kitörli a listából az összes olyan ellenfelet, akinek a Hp-ja <= 0.
// Nem kell hozzá semmilyen ciklus!
enemies.RemoveAll(e => e.Hp <= 0);

// Kilistázza a csatában lévő egyedi frakciókat:
var resztvevoFrakciok = hajoLista.Select(h => h.Frakcio)
                                 .Distinct()
                                 .ToList();

