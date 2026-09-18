package pizza;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PizzaTest{
    @ParameterizedTest
    @CsvSource({
        "LARGE,2,2900",
        "MEDIUM,0,2000",
        "SMALL,5,2500"
    })
    public void getPriceTest(PizzaSize size, int toppingsCount, int expected){
        Pizza pizza=new Pizza(size,toppingsCount);
        int price = pizza.getPrice();
        assertEquals(expected,price,"Hiba");
    }
}