import org.junit.jupietr.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import smarthaven.device.InteractiveDevice;

public class InteractiveDeviceTest{
    @Test
    public void constructorTest(){
        InteractiveDevice dev= new InteractiveDevice("id",Category.LIGHT);
        assertEquals("d",dev.getIdentifier(),"NEM");
        assertEquals(Category.LIGHT,dev.getDeviceType(),"NEM");
        assertEquals(0,dev.getConnectedDevices().size(),"NEM");
        
    }

    @Test
    public void linkTest(){
        InteractiveDevice d1=new InteractiveDevice("d1", Category.LIGHT);
        d1.setLightColor("red");
        InteractiveDevice d2=new InteractiveDevice("d2", Category.LED_STRIP);
        d2.setLightColor("blue");
        InteractiveDevice d3=new InteractiveDevice("d3", Category.LIGHT);
        d3.setLightColor("yellow");

        BaseModel d4 = new BaseModel("d4",Category.TV);

        BaseModel[] arr = {d1,d2,d3};
        d1.link(arr);
        d1.setLightColor("green");

        assertEquals("blue",d2.getLightColor());
        assertEquals("yellow",d3.getLightColor());

        d1.syncLights();

        assertEquals("green",d2.getLightColor());
        assertEquals("green",d3.getLightColor());
    }

    @Test
    public void toStringTest(){
        InteractiveDevice dev = new InteractiveDevice("led2-IDx", Category.LED_STRIP);
        dev.setLightColor("red");

        String exp= "Device: InteractiveDevice, Type: LED_STRIP, Identifier: led2-IDx, PowerStatus: false, Color: red";
        String str=dev.toString();
        assertEquals(str,exp,"NAH");

    }
}