package academy.magic;

import academy.util.BaseSpell;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ChainSpellTest{
    @Test
    public void TestInitialization(){
        ChainSpell bs=new ChainSpell("Lumos",SpellType.UTILITY);
        assertEquals(0,bs.getLinkedSpells().size(),"HIBA");
    }

    @Test
    public void testLink(){
         ChainSpell cs=new ChainSpell("Lumos",SpellType.UTILITY);
         ChainSpell bs1=new ChainSpell("Numos",SpellType.UTILITY);
         ChainSpell bs2=new ChainSpell("Cumos",SpellType.UTILITY);
        BaseSpell[] arr={bs1,bs2};
         cs.link(arr);
         assertEquals(2,cs.getLinkedSpells().size(),"BURGUMIRGUM");
        BaseSpell[] arr2={bs1};
        cs.link(arr2);
         assertEquals(2,cs.getLinkedSpells().size(),"BURGIR");
    }

    @Test
    public void testText(){
        ChainSpell cs = new ChainSpell("Shield", SpellType.UTILITY);
        cs.cast();
        cs.setAuraColor("silver");
        assertEquals(cs.toString(),"Spell: ChainSpell, Type: UTILITY, Name: Shield, Active: true, Aura: silver","HIBA");
    }

}