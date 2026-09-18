package flotta;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions.assertThrows;

public class FleetTest{
    @ParameterizedTest
    @CsvSource({
        "nev, FIGHTER, 5, 10",
        "nev, CRUISER, 5, 25",
        "nev, CARRIER, 5, 5"
    })
    public void fireWeapons(String name, ShipClass class, int energyLevel, int exp){
        SpaceShip ship=new SpaceShip(name,class,energyLevel);
        int szam=ship.fireWeapons();
        assertEquals(szam,exp,"HIBA");
    }

    public void boostEnergyTest(){
    FlagShip ship= new FlagShip("nev",ShipClass.FIGHTER,90);
    assertThrows(CoreOverLoadException.class,()->{ship.boostEnergy(20);});}
}