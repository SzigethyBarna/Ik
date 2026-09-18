package academy;

import academy.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MagicAcademyTest{
    @Test
    public void testMissingFile(){
        BaseSpell[] arr=new BaseSpell[0];
        MagicAcademy ma=new MagicAcademy(arr);
        assertEquals(ma.runFile("kamu.txt"),"File not found.","HIBA");
    }

    @Test
    public void testDefault(){
        ChainSpell cs=new ChainSpell("Lumos",SpellType.UTILITY);
        BaseSpell[] arr={cs};
        MagicAcademy ma=new MagicAcademy(arr);
        cs.cast();
        cs.setAuraColor("green");
        assertTrue(cs.getIsActive(),"HIBA");
        assertEquals(cs.getAuraColor(),"green","HIBA");

    }
}
