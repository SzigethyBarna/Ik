package rental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOExpcetion;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;


public class CarRentalTest1{
    private void createTestFile(String filename, String content) throws IOException{
        Files.writeString(Path.of(filename), content);
    }

    @Test
    public void testFileReadingAndNumberOfCars() throws IOException{
        String fileContent="""
            Volvo:JSD 856,500.0
            Hibassor
            BMW:ABC 123,ar
            Alfa Romeo:DEF 234,9.0
         """;

         String filename="test_cars1.txt";
         createTestFile(filename, fileContent);

         CarRental rental = new CarRental(filename);
         assertEquals(2,rental.numberOfCars());


         Files.deleteIfExists(Path.of(filename));
    }
    @Test
    public void testFileNotFound(){
        assertDoesNotThrow(() -> {
            CarRental rental = new CarRental("nem_letezik.txt");
            assertEquals(0,rental.numberOfCars()); }, "Nem dobhat kivetelt.");
    }
}
