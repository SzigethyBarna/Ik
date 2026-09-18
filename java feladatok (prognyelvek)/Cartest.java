import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest{
    @ParameterizedTest
    @CsvSource({
        "SEDAN, false",
        "SUV, false",
        "VAN, false",
        "TRUCK, false"
    })
    public void testIsBigCar(CarType car, boolean expected){
        Car car= new Car(type);
        boolean actual=car.isBig();
        assertEquals(expected,actual,"Hiba");
    }
}