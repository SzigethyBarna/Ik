package rental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class CarRentalTest3{
    private void createTestFile(String filename, String content) throws IOException{
        Files.writeString(Path.of(filename), content);
    }

    @Test
    public void rentCheapest() throws IOException{
        String fileContent="""
        Porsche:AAA 111,500.0
        Lada:BBB 222,50.0
         """;
         String filename ="test_file3.txt"
         
         createTestFile(filename,fileContent);

         CarRental rental = new CarRental(filename);

         Car rentedCar = rental.rentCheapest();
         assertNotNull(rentedCar);
         assertEquals(50.0,rentedCar.getPrice());
         assertEquals(1,rental.numbersOfCars());
         rental.rentCheapest();
         assertNull(rental.rentCheapest());
         Files.deleteIfExists(Path.of(filename));

    }

    @Test
    public void testSale(){
        String fileContent="""
        BMW:AAA 111,50.0
        Audi:BBB 222,60.0
         """;

        String filename="test_sale.txt";
        createTestFile(filename,fileContent);
        CarRental rental = new CarRental(filename);
        List<Car> saleCars = rental.sale();
        assertNotNull(saleCars);
        assertEquals(2,saleCars.size());
        Files.deleteIfExists(Path.of(filename));
    }
}