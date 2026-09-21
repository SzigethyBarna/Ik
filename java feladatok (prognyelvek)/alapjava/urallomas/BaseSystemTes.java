package station.util;

import station.core.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class BaseSystemTest{
    @Test
    public void constructorTest(){
        

        assertThrows(IllegalArgumentException.class,()->{new BaseSystem(null,SystemType.REACTOR);});
    }

    @Test
    public void toStringTest(){
        BaseSystem bs= new BaseSystem("CORE-01", SystemType.REACTOR);
        bs.bootUp();
        String str=bs.toString();
        assertEquals("System: BaseSystem, Type: REACTOR, Code: CORE-01, Online: true",str,"HIBA");
    }
}