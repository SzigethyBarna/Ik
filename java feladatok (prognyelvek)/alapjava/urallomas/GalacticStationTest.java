package station;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import station.util.*;
import station.core.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class GalacticStationTest{
    @Test
    public void testMissingFile(){
        BaseSystem[] bss ={};
        GalacticStation gs = new GalacticStation(bss);
        assertEquals("File not found.",gs.runDiagnostics("error.txt"),"HIBA");
    }
    @Test
    public void testDefault(){
        MasterSystem ms = new MasterSystem("NAV-MAIN", SystemType.NAVIGATION);
        BaseSystem[] bss ={ms};
        GalacticStation gs = new GalacticStation(bss);
        ms.bootUp();
        assertTrue(true,ms.isOnline(),"HIBA");
        ms.setPowerLevel("HIGH");
        assertEquals("HIGH",ms.getPowerLevel(),"HIBA");

    }
}