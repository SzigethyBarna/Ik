package smarthaven;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import smarthaven.util.BaseModel;
import smarthaven.device.Category;
import smarthaven.device.InteractiveDevice;

public class SmartHeavenTest{
    @Test
    public void missingFileTest(){
        BaseModel[] emptyArray = new BaseModel[0];
        SmartHaven haven = new SmartHaven(emptyArray);

        String result=haven.runFile("nemf.txt");
        assertEquals("File not found",result,"Nem jo fajl nem jo kod");
    }

    @Test
    public void testDefault(){
        InteractiveDevice lamp = new InteractiveDevice("nappali-lampa",Category.LIGHT);
        BaseModel[] devices={lamp};
        SmartHaven=new SmartHaven(devices);

        lamp.turnOn();
        assertTrue(lamp.isPowerStatus());
        lamp.setLightColor("blue");
        assertEquals("blue",lamp.getLightColor());
    }
}