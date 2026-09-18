// Interfész definíciója
public interface ITámadható
{
    // Itt csak a metódus "szerződése" van, törzse nincs!
    void Sebződik(int mennyiség);
}

// Implementálás az osztályban
public class Ellenség : ITámadható
{
    public void Sebződik(int mennyiség) 
    {
        // Itt már megírod a konkrét logikát
        Console.WriteLine($"Az ellenség {mennyiség} sebzést kapott.");
    }
}


///exception
/// 
// Saját Exception osztály, ami örököl az Exception-ből
public class NincsLőszerException : Exception
{
    // Konstruktor, ami átadja az üzenetet az ősosztálynak
    public NincsLőszerException(string uzenet) : base(uzenet) 
    {
    }
}

// Használat a kódban
public void Tüzel(int lőszer)
{
    if (lőszer <= 0)
    {
        // Saját hiba dobása
        throw new NincsLőszerException("Hiba: Üres a tár!");
    }
}