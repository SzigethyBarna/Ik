package rental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CarRental{
    @ParameterizedTest
    @CsvSource(textBlock="""
    Volvo,  JSD 856, 500.0
    BMW,    ABC 123, 40.0
    Alfa Romeo, DEF 234, 9.0
    """)
    public void testMakeValidCars(String brand, String licensePlate, double price){
        Car car = Car.make(brand,licensePlate,price);
        assertNotNull(car,"Nem lehet nulla");
        assertEquals(price, car.getPrice(), "Az arnak meg kell egyeznie a megadott arral");
    }
    @ParameterizedTest
    @CsvSource(textBlock = """
        V,          JSD 856, 500.0
        Volvo123,   JSD 856, 500.0
        BMW,        jsd 856, 40.0
        BMW,        JSD8567, 40.0
        Alfa Romeo, DEF 234, -5.0
        Alfa Romeo, DEF 234, 501.0
    """)
    public void testMakeInvalidCars(String brand, String licensePlate, double price){
        Car car = Car.make(brand,licensePlate,price);
        assertNull(car, "hibas adatok eseten null-t kell visszaadni");
    }
    @ParameterizedTest
    @CsvSource(textBlock = """
        Volvo,      JSD 856, 500.0, 'Volvo (JSD 856) 500,0 EUR'
        BMW,        ABC 123, 40.0,  'BMW (ABC 123)  40,0 EUR'
    """)
    public void testToString(String brand, String licensePlate, double price, String expectedText){
        Car car = Car.make(brand,licensePlate,price);
        assertEquals(expectedText, car.toString().replace(".",","));
    }

    @Test
    public void testDecreasePrice(){
        Car normalCar = Car.make("Ford", "AAA 111", 50.0);
        normalCar.decreasePrice();
        assertEquals(40.0,normalCar.getPrice());

        Car maxPriceCar = Car.make("Mustang", "BBB 111", 500.0);
        maxPriceCar.decreasePrice();
        assertEquals(500.0,maxPriceCar.getPrice());

        Car cheapCar = Car.make("Lada", "CCC 111", 9.0);
        cheapCar.decreasePrice();
        assertEquals(9.0,cheapCar.getPrice());
    }

    @Test
    public void testIsCheaperThan(){
        Car cheapCar = Car.make("Lada","AAA 111",50.0);
        Car expCar = Car.make("Mstng","BBB 111",100.0);

        assertTrue(cheapCar.isCheaperThan(expCar));
        assertFalse(expCar.isCheaperThan(cheapCar));
        assertFalse(cheapCar.isCheaperThan(null));
    }
}