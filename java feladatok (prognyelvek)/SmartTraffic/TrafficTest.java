package traffic;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class TrafficTest{
    @Test
    public void testElectricVehicleState(){
        ElectricVehicle vh=new ElectricVehicle("ZD-001",VehicleType.CAR,100,50);
        assertEquals(100,vh.calculateSpeed(),"HIBA");
        vh.drainBattery(40);
        assertEquals(50,vh.calculateSpeed(),"HIBA");
    }
    @Test
    public void testTrafficJam(){
        RoadSection sect=new RoadSection(2);
         ElectricVehicle vh=new ElectricVehicle("ZD-001",VehicleType.CAR,100,50);
         ElectricVehicle vh2=new ElectricVehicle("ZD-002",VehicleType.BUS,100,50);
         Vehicle vh3=new Vehicle("ZD-003",VehicleType.EMERGENCY,100);
        sect.enterRoad(vh);
        sect.enterRoad(vh2);
        assertThrows(TrafficJamException.class,()->{sect.enterRoad(vh3);});
    }

    @ParameterizedTest
    @CsvSource({
        "ZD-001,CAR,50,50",
        "ZD-002,BUS,50,40",
        "ZD-003,EMERGENCY,50,80"
    })
    public void testBaseSpeedCalculation(String plate,VehicleType type, int bs,int exp){
        Vehicle vh=new Vehicle(plate,type,bs);
        int szam=vh.calculateSpeed();
        assertEquals(exp,szam,"HIBA");
    }
}