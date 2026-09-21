package station.core;
import station.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MasterSystemTest{
    @Test
    public void testInitialization(){
        MasterSystem ms = new MasterSystem("NAV-MAIN", SystemType.NAVIGATION);

        assertEquals("NAV-MAIN",ms.getSystemCode(),"HIBA");
        assertEquals(SystemType.NAVIGATION,ms.getSystemType(),"HIBA");
        assertEquals(false,ms.getIsOnline(),"HIBA");
        assertEquals(0,ms.getSubSystem().size(),"HIBA");
    }

    @Test 
    public void testIntegrate(){
        MasterSystem ms = new MasterSystem("NAV-MAIN", SystemType.NAVIGATION);
        BaseSystem bs1= new BaseSystem("CORE-01", SystemType.REACTOR);
        BaseSystem bs2= new BaseSystem("CORE-02", SystemType.REACTOR);
        BaseSystem[] list={bs1,bs2};
        ms.integrate(list);
        assertEquals(2,ms.getSubSystem().size(),"HIBA");
        
       

    }

    @Test
    public void testText(){
         MasterSystem ms = new MasterSystem("DEF-01", SystemType.SHIELD);
         ms.setPowerLevel("MAX");
         ms.bootUp();
        String str= ms.toString();
        assertEquals("System: MasterSystem, Type: SHIELD, Code: DEF-01, Online: true, Power: MAX",str,"HIBA");
    }
}