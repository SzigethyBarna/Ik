package academy.util;

import academy.magic.SpellType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BaseSpellTest{
    @Test
    public void constructorTest(){
        assertThrows(IllegalArgumentException.class,()->{new BaseSpell(null,SpellType.COMBAT);});
    }

    @Test
    public void toStringTest(){
        BaseSpell bs = new BaseSpell("Fireball",SpellType.COMBAT);
        bs.cast();
        String st=bs.toString();
        assertEquals(st,"Spell: BaseSpell, Type: COMBAT, Name: Fireball, Active: true","HIBA");
    }
}