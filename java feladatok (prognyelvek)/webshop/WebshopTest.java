package webshop;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.Assertions.assertEquals;

public class WebshopTest{
    @ParameterizedTest
    @CsvSource({
        "Alma, FOOD, 200, 200",
        "Telefon, ELECTRONICS, 1000, 950",
        "Polo,CLOTHING,10,8"
    })
    public void getFinalPriceTest(String name,Category category, int basePrice, int expected){
        Product product=new Product(name,category,basePrice);
        int ar=product.getFinalPrice();
        assertEquals(ar,expected,"HIBA");
    }
}

public class PerishableProductTest{
    @ParameterizedTest
    @CsvSource({
        "Alma, FOOD, 1000, 2, 300.0"
    })
    public void calculateDiscountTest(String name,Category category, int basePrice,int daysToExpire, double expected){
        PerishableProduct product=new PerishableProduzct(name,category,baseprice,daysToExpire);
        double kedv= product.calculateDiscount();
        assertEquals(expected,kedv,"HIBA");
    }

        ///Sima @Test-el
    @Test
    public void calculateDiscountTest2() {
        // Előkészítés: Név, FOOD kategória, 1000 Ft alapár, 2 nap a lejáratig
        PerishableProduct product = new PerishableProduct("Alma", Category.FOOD, 1000, 2);
        
        // Végrehajtás
        double kedvezmeny = product.calculateDiscount();
        
        // Ellenőrzés: Mivel 3 napon belül lejár, 30% kedvezményt (300.0) kell adnia
        assertEquals(300.0, kedvezmeny, "Hibás extra kedvezmény számítás!");
    }
}